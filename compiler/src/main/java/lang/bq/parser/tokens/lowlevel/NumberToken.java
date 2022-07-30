package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class NumberToken implements Token {
    public final Number value;

    public NumberToken(Number value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return TokenType.NUMBER;
    }

    @Override
    public boolean is(TokenType type, Object value) {
        return type == this.type() && value instanceof Number &&
                (((Number) value).doubleValue() == this.value.doubleValue() &&
                 ((Number) value).longValue() == this.value.longValue());
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberToken that = (NumberToken) o;
        return this.value.longValue() == that.value.longValue() &&
                this.value.doubleValue() == this.value.doubleValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
