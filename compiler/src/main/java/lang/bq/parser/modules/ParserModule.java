package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;

public interface ParserModule {
    boolean isNext(Token token, Context context);
    Token parse(Tokenizer tokenizer, ModuleAccessor accessor);

    Context nextContext();
}
