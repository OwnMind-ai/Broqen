package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.highlevel.ScriptToken;
import lang.bq.parser.tokens.lowlevel.BooleanToken;
import lang.bq.syntax.Operators;
import lang.bq.syntax.Punctuations;

public interface ScriptedModule {
    ExpressionModule expressionModule = new ExpressionModule();
    ScriptModule scriptModule = new ScriptModule();

    default ScriptToken parseBody(Tokenizer tokenizer, ModuleAccessor accessor){
        return tokenizer.peek().is(TokenType.PUNCTUATION, Punctuations.CURLY_START) ?
                (ScriptToken) scriptModule.parse(tokenizer, accessor) :
                new ScriptToken(new Token[]{accessor.parse(Context.FUNCTION)});
    }

    default ExpressionToken parseCondition(Tokenizer tokenizer, ModuleAccessor accessor){
        Token rawCondition = expressionModule.parse(tokenizer, accessor);
        return rawCondition.type() == TokenType.EXPRESSION ?
                (ExpressionToken) rawCondition :
                //TODO: Maybe better way is setting operator to null, depends on compiler implementation
                new ExpressionToken(Operators.EQUAL, new BooleanToken(true), rawCondition);
    }
}
