package lang.bq.parser;

import lang.bq.parser.tokens.IToken;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Tokenizer{
    private final static String whitespaces = " \n\t\r";
    private final static String digits = "0123456789";
    private final static String stringOperators = "\"'";
    private final static String punctuation = ",;(){}[]";
    private final static String operators = "+-*/%=&|<>!";
    private final static String letters = "abcdefghijklmnopqrstuvwxyz";
    public final static String[] mathOperators = new String[] {
            "+", "-", "*", "/", "%", "=", ">", "<", "<<", ">>", "->", "<-",
            "+=", "-=", "*=", "/=", ":=", "%=","!=", ">=", "==", "<=", "&&", "||"};

    private final static String[] keywords = new String[] {"true", "false"};
    private final CharStream stream;
    private IToken current;
    private final StringBuilder pool = new StringBuilder();

    public Tokenizer(CharStream stream) {
        this.stream = stream;
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


    public IToken findToken(){
        if(this.pool.length() == 0){
            this.pool.append(this.stream.peek());
            return null;
        }

        this.pool.charAt(this.pool.length() - 1);

        return null;
    }

    public IToken next() {
        this.current = null;
        this.skip();
            //if(this.findToken() != null) return this.peek();
        if(!stream.eof())
            System.out.print(this.stream.next());

        return null;
    }

    public IToken peek() {
        return current;
    }

    public boolean eof() {
        return stream.eof();
    }
}
