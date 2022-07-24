package lang.bq.parser.modules;

import lang.bq.parser.tokens.Token;

public interface ParameterizedModule extends ParserModule{
    void setParameters(Token[] parameters);
}
