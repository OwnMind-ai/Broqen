package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.lowlevel.BooleanToken;
import lang.bq.parser.tokens.lowlevel.StringToken;

public class BooleanModule implements ParserModule{
    @Override
    public boolean isNext(Token token, Context context) {
        if(context != Context.EXPRESSION || token.type() != TokenType.KEYWORD) return false;
        StringToken keyword = (StringToken) token;

        return keyword.value.equals("true") || keyword.value.equals("false");
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        StringToken keyword = (StringToken) tokenizer.next();

        switch (keyword.value){
            case "true": return new BooleanToken(true);
            case "false": return new BooleanToken(false);
            default: return null;
        }
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
