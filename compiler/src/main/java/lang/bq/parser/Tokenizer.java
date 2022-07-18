package lang.bq.parser;

import lang.bq.exceptions.TokenizeException;
import lang.bq.parser.tokens.IToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.parser.tokens.lowlevel.StringToken;
import lang.bq.parser.tokens.TokenType;

import java.util.Arrays;
import java.util.List;

public class Tokenizer{
    private final static String whitespaces = " \n\t\r";
    private final static String digits = "0123456789";
    private final static String stringOperators = "\"'";
    private final static String punctuation = ".,:;(){}[]";
    private final static String operators = "+-*/%=&|<>!:";
    private final static String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static List<String> mathOperators = Arrays.asList(
            "+", "-", "*", "/", "^", "**", "%", "=", "--", "++", ">", "<", "<<", ">>", "->", "<-",
            "+=", "-=", "*=", "/=", ":=", "%=","!=", ">=", "==", "<=", "&&", "||");
    private static final short MAX_OPERATOR_LENGTH = 2;

    private final static List<String> keywords = Arrays.asList(
            "true", "false", "new", "node", "send", "feedback", "public", "private"
    );
    private final static List<String> types = Arrays.asList(
            "void","int", "char", "short", "float", "double", "long", "bool"
    );
    private final CharStream stream;
    private IToken current;

    public Tokenizer(CharStream stream) {
        this.stream = stream;
    }

    private static boolean isDigit(char ch) { return Tokenizer.digits.indexOf(ch) > -1; }
    private static boolean isNameStart(char ch) { return Tokenizer.letters.indexOf(ch) > -1; }
    private static boolean isOperator(char ch) { return Tokenizer.operators.indexOf(ch) > -1; }
    private static boolean isWhitespace(char ch) { return Tokenizer.whitespaces.indexOf(ch) > -1; }
    private static boolean isString(char ch) { return Tokenizer.stringOperators.indexOf(ch) > -1; }
    private static boolean isPunctuation(char ch) { return Tokenizer.punctuation.indexOf(ch) > -1; }
    private static boolean isName(char ch) { return Tokenizer.isNameStart(ch) || (Tokenizer.digits + "_").indexOf(ch) > -1; }

    private String readWhile(ReadingCondition predicate) {
        StringBuilder text = new StringBuilder();

        while (!this.stream.eof() && predicate.check(this.stream.peek())) {
            text.append(this.stream.next());
        }

        return text.toString();
    }

    private void skipWhitespaces(){
        while(!this.stream.eof() && whitespaces.indexOf(this.stream.peek()) > -1){
            this.stream.next();
        }
    }

    private boolean skipComments(){
        if(!this.stream.eof() && this.stream.peek() == '/'){
            if (this.stream.isNext("//")) {   // skip single-line comment
                while (!this.stream.eof() && this.stream.next() != '\n');

                this.stream.next(); // skip '\n'
            } else if(this.stream.isNext("/*")) {   /* skip multi-line comment */
                while (!this.stream.eof()){
                    if(this.stream.isNext("*/"))
                        break;
                }
                this.stream.next();  // skip '*'
                this.stream.next();  // skip '/'
            }

            return true;
        }

        return false;
    }

    private void skip(){
        this.skipWhitespaces();
        while(this.skipComments())
            this.skipWhitespaces();
    }

    private IToken readOperator() {
        for (int i = MAX_OPERATOR_LENGTH ; i > 0; i--) {
            String operator = this.stream.peek(i);

            if (Tokenizer.mathOperators.contains(operator)) {
                return new StringToken(TokenType.OPERATOR, this.stream.next(i));
            }
        }

        this.throwException("Can't define operator " + this.stream.peek(2));
        return null;
    }

    private IToken readIdentification() {
        String name = this.readWhile(Tokenizer::isName);

        if(Tokenizer.keywords.contains(name))
            return new StringToken(TokenType.KEYWORD, name);
        else if (Tokenizer.types.contains(name))
            return new StringToken(TokenType.TYPE, name);
        else
            return new StringToken(TokenType.IDENTIFIER, name);
    }

    private IToken readNumber() {
        String number = readWhile(x -> Tokenizer.isDigit(x) || x == '.');
        long dotsCount = number.chars().filter(ch -> ch == '.').count();

        if (dotsCount > 1)
            this.throwException("Invalid number syntax: " + number);
        else if(dotsCount == 1)
            return new NumberToken(Double.parseDouble(number));

        return new NumberToken(Long.parseLong(number));
    }

    private IToken readString() {
        StringBuilder text = new StringBuilder();
        char startChar = this.stream.next();

        // read all chars to next end_char (" or ') and add to text
        while (!(this.stream.eof() || this.stream.peek() == '\n')) {
            char ch = this.stream.next();

            // if end of string, returns it
            if (ch == startChar)
                return new StringToken(TokenType.STRING, text.toString());

            // Example: (\") in code will be (") in string
            if (ch == '\\')
                ch = readEscapeChar();

            text.append(ch);
        }

        this.throwException("Unexpected EOF while reading string");
        return null;
    }

    private char readEscapeChar() {
        char escapedChar = this.stream.next();

        if (escapedChar == 't')
            return '\t';
        else if (escapedChar == 'n')
            return '\n';
        else if (escapedChar == '\\' ||
                escapedChar == '\"' ||
                escapedChar == '\'')
            return escapedChar;
        else
            this.throwException("Unexpected escape char in string");

        return 0;
    }

    public IToken findToken(){
        if (this.stream.eof()) return null;

        char ch = this.stream.peek();

        if (Tokenizer.isPunctuation(ch))
            return new StringToken(TokenType.PUNCTUATION, String.valueOf(this.stream.next()));

        if (Tokenizer.isOperator(ch))
            return readOperator();

        if (Tokenizer.isString(ch))
            return readString();

        if (Tokenizer.isDigit(ch))
            return this.readNumber();

        if (Tokenizer.isNameStart(ch))
            return this.readIdentification();

        this.throwException("Invalid Syntax");
        return null;
    }

    public IToken next() {
        this.skip();
        this.current = findToken();

        return this.current;
    }

    public IToken peek() {
        return current;
    }

    public boolean eof() {
        return stream.eof();
    }

    private void throwException(String message){
        throw new TokenizeException(message,
                this.stream.getLine(),
                this.stream.getColumn()
        );
    }

    private interface ReadingCondition {
        boolean check(char current);
    }
}
