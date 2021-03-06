package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class InitializationToken implements Token {
    public final TypeToken variableType;
    public final String name;
    public final Token value;

    public InitializationToken(TypeToken type, String name, Token value) {
        this.variableType = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "INITIALIZATION(" + variableType + "," + value + ")";
    }

    @Override
    public TokenType type() {
        return TokenType.INITIALIZATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InitializationToken that = (InitializationToken) o;
        return Objects.equals(variableType, that.variableType) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableType, value);
    }
}
