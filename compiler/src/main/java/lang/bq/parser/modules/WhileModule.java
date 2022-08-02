package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.WhileToken;
import lang.bq.parser.tokens.lowlevel.KeywordToken;
import lang.bq.parser.tokens.lowlevel.PunctuationToken;
import lang.bq.syntax.Keywords;
import lang.bq.syntax.Punctuations;

public class WhileModule implements ParserModule, ScriptedModule{
    @Override
    public boolean isNext(Token token, Context context) {
        return context == Context.FUNCTION && token.is(TokenType.KEYWORD, Keywords.WHILE);
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        tokenizer.skip(new KeywordToken(Keywords.WHILE));
        tokenizer.skip(new PunctuationToken(Punctuations.PARENTHESES_START));

        return new WhileToken(
                this.parseCondition(tokenizer, accessor),
                this.parseBody(tokenizer, accessor)
        );
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
