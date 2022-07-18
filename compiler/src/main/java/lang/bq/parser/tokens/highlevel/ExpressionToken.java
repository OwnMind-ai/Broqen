package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.IToken;
import lang.bq.parser.tokens.TokenType;

public class ExpressionToken implements IToken {
    public final static TokenType type = TokenType.EXPRESSION;
    public final IToken left;
    public final IToken right;
    public final String operator;

    public ExpressionToken(String operator, IToken left, IToken right) {
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