package lang.bq.parser.tokens;

import java.util.Arrays;

public interface Token {
    TokenType type();

    //TODO: add equals method for raw values

    default boolean anyType(TokenType... types){
        return Arrays.stream(types).anyMatch((t) -> this.type() == t);
    }
}
