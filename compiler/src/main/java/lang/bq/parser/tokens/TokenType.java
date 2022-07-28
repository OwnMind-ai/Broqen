package lang.bq.parser.tokens;

// TODO: migrate token type identification to instanceof construction for better casting safety
public enum TokenType {
    EMPTY,
    PUNCTUATION,
    OPERATOR,
    IDENTIFIER,
    KEYWORD,
    NUMBER,
    STRING,
    BOOLEAN,
    PRIMITIVE,

    FUNCTION,
    NODE,
    METHOD,
    IF,
    FOR,
    WHILE,

    CALL,
    EXPRESSION,
    SCRIPT,
    ASSIGN,
    OBJECT_INITIALIZATION,
    PRIMITIVE_INITIALIZATION
}
