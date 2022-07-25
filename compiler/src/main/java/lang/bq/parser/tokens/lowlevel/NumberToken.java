package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class NumberToken implements Token {
    private static final TokenType type = TokenType.NUMBER;
    public final Number value;

    public NumberToken(Number value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return type;
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
