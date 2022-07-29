package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class ObjectInitializationToken implements Token {
    public final String type;
    public final String name;
    public final Token value;

    public ObjectInitializationToken(String type, String name, Token value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "INITIALIZATION(" + type + "," + value + ")";
    }

    @Override
    public TokenType type() {
        return TokenType.OBJECT_INITIALIZATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectInitializationToken that = (ObjectInitializationToken) o;
        return Objects.equals(type, that.type) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
