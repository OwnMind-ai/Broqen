package lang.bq.parser.tokens;

import java.util.Arrays;

public interface Token {
    TokenType type();
    default boolean is(TokenType type, Object value) { return false; }

    default boolean anyType(TokenType... types){
        return Arrays.stream(types).anyMatch((t) -> this.type() == t);
    }
}
