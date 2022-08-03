package lang.bq.parser.tokens;

// TODO: migrate token type identification to instanceof construction for better casting safety
public enum TokenType {
    EMPTY,
    //TODO: DENIED token
    PUNCTUATION,
    OPERATOR,
    IDENTIFIER,
    KEYWORD,
    NUMBER,
    STRING,
    BOOLEAN,
    PRIMITIVE,
    TYPE,

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
    INITIALIZATION,
    ARGUMENT
}
