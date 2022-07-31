package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.lowlevel.BooleanToken;
import lang.bq.parser.tokens.lowlevel.KeywordToken;
import lang.bq.syntax.Keywords;

public class BooleanModule implements ParserModule{
    @Override
    public boolean isNext(Token token, Context context) {
        if(context != Context.EXPRESSION || token.type() != TokenType.KEYWORD) return false;
        Keywords keyword = ((KeywordToken) token).keyword;

        return keyword == Keywords.TRUE || keyword == Keywords.FALSE;
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        Keywords keyword = ((KeywordToken) tokenizer.next()).keyword;

        switch (keyword){
            case TRUE: return new BooleanToken(true);
            case FALSE: return new BooleanToken(false);
            default: return null;
        }
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
