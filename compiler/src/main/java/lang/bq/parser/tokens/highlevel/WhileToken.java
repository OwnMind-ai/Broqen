package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class WhileToken implements Token {
    public final ExpressionToken condition;
    public final ScriptToken body;

    public WhileToken(ExpressionToken condition, ScriptToken body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString() {
        return "While(" + condition + "){" + body + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WhileToken that = (WhileToken) o;
        return Objects.equals(condition, that.condition) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, body);
    }

    @Override
    public TokenType type() {
        return TokenType.WHILE;
    }
}
