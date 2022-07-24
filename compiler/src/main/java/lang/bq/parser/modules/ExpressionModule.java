package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.lowlevel.StringToken;

public class ExpressionModule implements ParserModule{
    private ModuleAccessor accessor;
    private Tokenizer tokenizer;

    @Override
    public boolean isNext(Token token, Context context) {
        return (context == Context.FUNCTION || context == Context.EXPRESSION)
                && token.type() == TokenType.OPERATOR && ((StringToken) token).value.equals("(");
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        this.accessor = accessor;
        this.tokenizer = tokenizer;
        return this.buildTree(accessor.parse(), 0);
    }

    private Token buildTree(Token previous, int priority){
        Token token = tokenizer.peek();
        if(token != null && token.type() == TokenType.OPERATOR){
            assert token instanceof StringToken;
            StringToken operator = (StringToken) token;

            int nextPriority = ExpressionModule.getPriority(operator.value);
            if(priority < nextPriority){
                this.tokenizer.next();

                return this.buildTree(
                        new ExpressionToken(
                                operator.value,
                                previous,
                                this.buildTree(accessor.parse(), nextPriority)
                        ),
                        priority
                );
            }
        }

        return token;
    }

    public static int getPriority(String operator) {
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

    @Override
    public Context nextContext() {
        return Context.FUNCTION;
    }
}
