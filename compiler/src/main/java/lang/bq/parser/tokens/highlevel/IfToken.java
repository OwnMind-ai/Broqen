package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class IfToken implements Token {
    public final ExpressionToken condition;
    public final ScriptToken ifBody;
    public final ScriptToken elseBody;

    public IfToken(ExpressionToken condition, ScriptToken ifBody, ScriptToken elseBody) {
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    @Override
    public String toString() {
        return "IfToken{" +
                "condition=" + condition +
                ", ifBody=" + ifBody +
                ", elseBody=" + elseBody +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfToken ifToken = (IfToken) o;
        return Objects.equals(condition, ifToken.condition) && Objects.equals(ifBody, ifToken.ifBody) && Objects.equals(elseBody, ifToken.elseBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, ifBody, elseBody);
    }

    @Override
    public TokenType type() {
        return TokenType.IF;
    }
}
