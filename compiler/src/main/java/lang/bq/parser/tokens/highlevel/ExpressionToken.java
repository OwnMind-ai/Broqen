package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

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
                left.toString() + " " +
                this.operator +
                " " + this.right.toString() + "}";
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressionToken that = (ExpressionToken) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right) && Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, operator);
    }
}