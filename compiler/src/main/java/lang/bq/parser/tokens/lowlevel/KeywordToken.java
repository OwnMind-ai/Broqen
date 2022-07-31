package lang.bq.parser.tokens.lowlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.syntax.Keywords;

import java.util.Objects;

public class KeywordToken implements Token {
    public final Keywords keyword;

    public KeywordToken(Keywords keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "KEYWORD(" + keyword + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeywordToken that = (KeywordToken) o;
        return keyword == that.keyword;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }

    @Override
    public TokenType type() {
        return TokenType.KEYWORD;
    }

    @Override
    public boolean is(TokenType type, Object value) {
        return type == this.type() && this.keyword == value;
    }
}
