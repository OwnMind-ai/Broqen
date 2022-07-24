package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

public class ExpressionToken implements Token {
    public final static TokenType type = TokenType.EXPRESSION;
    public final Token left;
    public final Token right;
    public final String operator;

    public ExpressionToken(String operator, Token left, Token right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "EXPRESSION: {" +
                right.toString() + " " +
                this.operator +
                " " + this.left.toString() + "}";
    }

    @Override
    public TokenType type() {
        return type;
    }
}