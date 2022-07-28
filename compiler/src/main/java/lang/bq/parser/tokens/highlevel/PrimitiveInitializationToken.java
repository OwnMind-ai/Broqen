package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.syntax.Primitives;

import java.util.Objects;

public class PrimitiveInitializationToken implements Token {
    public final Primitives type;
    public final Token value;

    public PrimitiveInitializationToken(Primitives type, Token value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "INITIALIZATION(" + type + "," + value + ")";
    }

    @Override
    public TokenType type() {
        return TokenType.PRIMITIVE_INITIALIZATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimitiveInitializationToken that = (PrimitiveInitializationToken) o;
        return type == that.type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
