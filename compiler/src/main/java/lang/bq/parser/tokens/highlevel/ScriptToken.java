package lang.bq.parser.tokens.highlevel;

import lang.bq.parser.tokens.IToken;
import lang.bq.parser.tokens.TokenType;

import java.util.Arrays;

public class ScriptToken implements IToken {
    private static final TokenType type = TokenType.SCRIPT;

    public final IToken[] instructions;

    public ScriptToken(IToken[] instructions) {
        this.instructions = instructions;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "ScriptToken{" +
                "instructions=" + Arrays.toString(instructions) +
                '}';
    }
}
