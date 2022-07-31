package lang.bq.parser.modules;

import lang.bq.syntax.Keywords;

public interface ParameterizedModule extends ParserModule{
    void setParameters(Keywords[] parameters);
}
