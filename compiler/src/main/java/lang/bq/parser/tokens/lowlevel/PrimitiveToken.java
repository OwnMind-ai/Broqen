package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.syntax.Primitives;

import java.util.Objects;

public class PrimitiveToken implements Token {
    public final Primitives primitive;

    public PrimitiveToken(Primitives primitive) {
        this.primitive = primitive;
    }

    @Override
    public String toString() {
        return "TYPE " + primitive.representation();
    }

    @Override
    public TokenType type() {
        return TokenType.PRIMITIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimitiveToken that = (PrimitiveToken) o;
        return primitive == that.primitive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(primitive);
    }
}
