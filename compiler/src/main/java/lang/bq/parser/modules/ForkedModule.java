package lang.bq.parser.modules;

import lang.bq.parser.tokens.Token;

public interface ForkedModule extends ParserModule{
    void setPrevious(Token token);
}
