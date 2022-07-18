package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.IToken;
import lang.bq.parser.tokens.TokenType;

import java.util.Arrays;

public class CallToken implements IToken {
    private static final TokenType type = TokenType.CALL;

    public final String name;
    public final IToken[] arguments;

    public CallToken(String name, IToken[] arguments) {
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
