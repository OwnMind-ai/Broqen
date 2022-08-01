package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.highlevel.IfToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.BooleanToken;
import lang.bq.parser.tokens.lowlevel.KeywordToken;
import lang.bq.parser.tokens.lowlevel.PunctuationToken;
import lang.bq.syntax.Keywords;
import lang.bq.syntax.Operators;
import lang.bq.syntax.Punctuations;

public class IfModule implements ParserModule{
    private static final ExpressionModule expressionModule = new ExpressionModule();
    private static final ScriptModule scriptModule = new ScriptModule();

    @Override
    public boolean isNext(Token token, Context context) {
        return context == Context.FUNCTION && token.is(TokenType.KEYWORD, Keywords.IF);
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        tokenizer.skip(new KeywordToken(Keywords.IF));
        tokenizer.skip(new PunctuationToken(Punctuations.PARENTHESES_START));

        Token rawCondition = expressionModule.parse(tokenizer, accessor);
        ExpressionToken condition = rawCondition.type() == TokenType.EXPRESSION ?
                (ExpressionToken) rawCondition :
                new ExpressionToken(Operators.EQUAL, new BooleanToken(true), rawCondition);
        tokenizer.skip(new PunctuationToken(Punctuations.PARENTHESES_END));

        ScriptToken ifScript = parseBody(tokenizer, accessor);

        if(tokenizer.peek().is(TokenType.KEYWORD, Keywords.ELSE)){
            tokenizer.skip(new KeywordToken(Keywords.ELSE));

            return new IfToken(condition, ifScript, parseBody(tokenizer, accessor));
        }

        return new IfToken(condition, ifScript, null);
    }

    private ScriptToken parseBody(Tokenizer tokenizer, ModuleAccessor accessor){
        return tokenizer.peek().is(TokenType.PUNCTUATION, Punctuations.CURLY_START) ?
                (ScriptToken) scriptModule.parse(tokenizer, accessor) :
                new ScriptToken(new Token[]{accessor.parse(Context.FUNCTION)});
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
