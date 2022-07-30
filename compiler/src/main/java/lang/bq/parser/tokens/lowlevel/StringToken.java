package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class StringToken implements Token {
    private final TokenType type;
    public final String value;

    public StringToken(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public boolean is(TokenType type, Object value) {
        return type == this.type && this.value.equals(value);
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringToken that = (StringToken) o;
        return type == that.type && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
