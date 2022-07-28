package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Arrays;
import java.util.Objects;

public class CallToken implements Token {
    private static final TokenType type = TokenType.CALL;

    public final String name;
    public final Token[] arguments;

    public CallToken(String name, Token[] arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "CallToken{" +
                "name='" + name + '\'' +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallToken callToken = (CallToken) o;
        return Objects.equals(name, callToken.name) && Arrays.equals(arguments, callToken.arguments);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }
}
