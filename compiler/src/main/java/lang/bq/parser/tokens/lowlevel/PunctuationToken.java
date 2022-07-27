package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.syntax.Punctuations;

import java.util.Objects;

public class PunctuationToken implements Token {
    private static final TokenType type = TokenType.PUNCTUATION;

    public final Punctuations value;

    public PunctuationToken(Punctuations value) {
        this.value = value;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "PunctuationToken{" +
                "value=" + value.representation() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PunctuationToken that = (PunctuationToken) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
