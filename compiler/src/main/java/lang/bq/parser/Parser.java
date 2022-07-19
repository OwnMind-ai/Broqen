package lang.bq.parser;

import lang.bq.parser.modules.ParserModule;
import lang.bq.parser.tokenizer.Tokenizer;

public class Parser {
    private static final ParserModule[] modules = {
            /* ...modules... */
    };
    private final Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    // TODO: move it to corresponding module
    public static int getPrecedence(String operator) {
        switch (operator){
            case "=": case "+=": case "-=":
            case "*=": case "/=": case "%=":
                return 1;
            case ">>": case "<<":
                return 2;
            case "||":
                return 3;
            case "&&":
                return 4;
            case "^":
                return 5;
            case "<": case ">": case "<=":
            case ">=": case "==": case "!=":
                return 7;
            case "+": case "-":
                return 10;
            case "*": case "/": case "%":
                return 20;
            default:
                return 0;
        }
    }
}