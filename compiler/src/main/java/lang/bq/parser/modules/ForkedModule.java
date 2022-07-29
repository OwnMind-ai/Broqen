package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.tokens.Token;

public interface ForkedModule extends ParserModule{
    void setPrevious(Token token);
    boolean isNextForked(Token token, Context context);
}
