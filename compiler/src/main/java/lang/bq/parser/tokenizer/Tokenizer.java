package lang.bq.parser.tokenizer;

import lang.bq.messages.ExceptionMessage;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.lowlevel.*;
import lang.bq.syntax.Keywords;
import lang.bq.syntax.Operators;
import lang.bq.syntax.Primitives;
import lang.bq.syntax.Punctuations;

public class Tokenizer{
    private final static String whitespaces = " \n\t\r";
    private final static String digits = "0123456789";
    private final static String stringOperators = "\"'";
    private final static String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final CharStream stream;
    private Token current;

    public Tokenizer(CharStream stream) {
        this.stream = stream;
    }

    private static boolean isDigit(char ch) { return Tokenizer.digits.indexOf(ch) > -1; }
    private static boolean isIdentificationStart(char ch) { return Tokenizer.letters.indexOf(ch) > -1; }
    private static boolean isOperator(char ch) { return Operators.chars.indexOf(ch) > -1; }
    private static boolean isWhitespace(char ch) { return Tokenizer.whitespaces.indexOf(ch) > -1; }
    private static boolean isString(char ch) { return Tokenizer.stringOperators.indexOf(ch) > -1; }
    private static boolean isPunctuation(char ch) { return Punctuations.chars.indexOf(ch) > -1; }
    private static boolean isIdentification(char ch) { return Tokenizer.isIdentificationStart(ch) || (Tokenizer.digits + "_").indexOf(ch) > -1; }

    private String readWhile(ReadingCondition predicate) {
        StringBuilder text = new StringBuilder();

        while (!this.stream.eof() && predicate.check(this.stream.peek())) {
            text.append(this.stream.skip());
        }

        return text.toString();
    }

    private void skipWhitespaces(){
        while(!this.stream.eof() && isWhitespace(this.stream.peek())){
            this.stream.skip();
        }
    }

    private boolean skipComments(){
        if(!this.stream.eof() && this.stream.peek() == '/'){
            if (this.stream.isNext("//")) {   // skip single-line comment
                while (!this.stream.eof() && this.stream.skip() != '\n');

                if(!this.stream.eof()) this.stream.skip(); // skip '\n'
                return true;
            } else if(this.stream.isNext("/*")) {   /* skip multi-line comment */
                while (!this.stream.eof()){
                    if(this.stream.isNext("*/"))
                        break;
                    this.stream.skip();
                }

                this.stream.skip();  // skip '*'
                this.stream.skip();  // skip '/'
                return true;
            }
        }

        return false;
    }

    private void skip(){
        do {
            this.skipWhitespaces();
        } while(this.skipComments());
    }

    private Token readOperator() {
        for (int i = Operators.MAX_OPERATOR_LENGTH; i > 0; i--) {
            String operator = this.stream.peek(i);

            Operators result = Operators.of(operator);
            if (result != null) {
                this.stream.skip(i);
                return new OperatorToken(result);
            }
        }

        this.throwException("Can't define operator " + this.stream.peek(2));
        return null;
    }

    private Token readIdentification() {
        String name = this.readWhile(Tokenizer::isIdentification);

        Keywords keyword = Keywords.of(name);
        if(keyword != null)
            return new KeywordToken(keyword);
        else {
            Primitives primitive = Primitives.of(name);
            if(primitive != null)
                return new PrimitiveToken(primitive);
            else
                return new IdentifierToken(name);
        }
    }

    private Token readNumber() {
        String number = readWhile(x -> Tokenizer.isDigit(x) || x == '.');
        long dotsCount = number.chars().filter(ch -> ch == '.').count();

        if (dotsCount > 1)
            this.throwException("Invalid number syntax: " + number);
        else if(dotsCount == 1)
            return new NumberToken(Double.parseDouble(number));

        return new NumberToken(Long.parseLong(number));
    }

    private Token readString() {
        StringBuilder text = new StringBuilder();
        char startChar = this.stream.skip();

        // read all chars to next end_char (" or ') and add to text
        while (!(this.stream.eof() || this.stream.peek() == '\n')) {
            char ch = this.stream.skip();

            // if end of string, returns it
            if (ch == startChar)
                return new StringToken(text.toString());

            // Example: (\") in code will be (") in string
            if (ch == '\\')
                ch = readEscapeChar();

            text.append(ch);
        }

        this.throwException("Unexpected EOF while reading string");
        return null;
    }

    private char readEscapeChar() {
        char escapedChar = this.stream.skip();

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

    private Token findToken(){
        if (this.stream.eof()) return null;

        char ch = this.stream.peek();

        if (Tokenizer.isPunctuation(ch))
            return new PunctuationToken(Punctuations.of(String.valueOf(this.stream.skip())));

        if (Tokenizer.isOperator(ch))
            return readOperator();

        if (Tokenizer.isString(ch))
            return readString();

        if (Tokenizer.isDigit(ch))
            return this.readNumber();

        if (Tokenizer.isIdentificationStart(ch))
            return this.readIdentification();

        this.throwException("Invalid Syntax");
        return null;
    }

    public Token next() {
        this.skip();
        Token previous = this.current;
        this.current = findToken();

        // Dangerous code, may cause infinity loops while waiting null output from Tokenizer::next
        // TODO: find another way to fix problem with taking next on eof (something with Tokenizer::findToken:1)
        if(current == null) this.current = previous;

        return previous;
    }

    public Token peek() {
        return current;
    }

    public void skip(Token token){
        if(!unsafeSkip(token)) this.throwException("Failed to find " + token);
    }

    public boolean unsafeSkip(Token token){
        if(this.current != null && this.current.equals(token)) {
            this.next();
            return true;
        }

        return false;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean eof() {
        return stream.eof();
    }

    public void throwException(String message){
        new ExceptionMessage(message,
                this.stream.currentLine(),
                this.stream.getLine(),
                this.stream.getColumn()
        ).throwMessage();
    }

    private interface ReadingCondition {
        boolean check(char current);
    }
}
