package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;

import java.util.Arrays;

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
}
