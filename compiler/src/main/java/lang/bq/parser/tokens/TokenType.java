package lang.bq.parser.tokens;

public enum TokenType {
    EMPTY,
    PUNCTUATION,
    OPERATOR,
    IDENTIFIER,
    KEYWORD,
    NUMBER,
    STRING,
    BOOLEAN,
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
    ASSIGN
}
