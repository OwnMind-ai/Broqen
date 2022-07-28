package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;

public class InitializationModule implements ForkedModule{
    @Override
    public void setPrevious(Token token) {

    }

    @Override
    public boolean isNext(Token token, Context context) {
        return false;
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        return null;
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
