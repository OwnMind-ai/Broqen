package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.lowlevel.StringToken;

public class CallModule implements ParserModule{
    @Override
    public boolean isNext(Token token, Context context) {
        return (context == Context.FUNCTION || context == Context.EXPRESSION)
                && token.equals(new StringToken(TokenType.OPERATOR, "("));
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
