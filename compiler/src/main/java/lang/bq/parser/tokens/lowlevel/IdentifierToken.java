package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class IdentifierToken implements Token {
    public final String value;

    public IdentifierToken(String value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return TokenType.IDENTIFIER;
    }

    @Override
    public boolean is(TokenType type, Object value) {
        return type == this.type() && this.value.equals(value);
    }

    @Override
    public String toString() {
        return "IDENTIFIER(" + value + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifierToken that = (IdentifierToken) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
