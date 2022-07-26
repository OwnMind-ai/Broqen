package lang.bq.parser;

import lang.bq.parser.tokens.Token;

@FunctionalInterface
public interface ModuleAccessor {
    Token parse(Context context);
}
