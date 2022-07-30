package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Objects;

public class ArgumentToken implements Token {
    public final Token type;
    public final String name;

    public ArgumentToken(Token type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Argument(" + type + ", " + name + ')';
    }

    @Override
    public TokenType type() {
        return TokenType.ARGUMENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArgumentToken that = (ArgumentToken) o;
        return Objects.equals(type, that.type) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}
