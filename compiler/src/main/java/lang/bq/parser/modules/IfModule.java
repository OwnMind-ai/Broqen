package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.highlevel.IfToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.KeywordToken;
import lang.bq.parser.tokens.lowlevel.PunctuationToken;
import lang.bq.syntax.Keywords;
import lang.bq.syntax.Punctuations;

public class IfModule implements ParserModule, ScriptedModule{
    @Override
    public boolean isNext(Token token, Context context) {
        return context == Context.FUNCTION && token.is(TokenType.KEYWORD, Keywords.IF);
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        tokenizer.skip(new KeywordToken(Keywords.IF));

        ExpressionToken condition = parseCondition(tokenizer, accessor);
        ScriptToken ifScript = parseBody(tokenizer, accessor);

        if(tokenizer.peek().is(TokenType.KEYWORD, Keywords.ELSE)){
            tokenizer.skip(new KeywordToken(Keywords.ELSE));

            return new IfToken(condition, ifScript, parseBody(tokenizer, accessor));
        }

        return new IfToken(condition, ifScript, null);
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
