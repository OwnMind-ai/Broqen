package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.lowlevel.EmptyToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.parser.tokens.lowlevel.OperatorToken;
import lang.bq.parser.tokens.lowlevel.StringToken;

public class SignModule implements ParserModule {
    @Override
    public boolean isNext(Token token, Context context) {
        if(context != Context.FUNCTION && context != Context.EXPRESSION &&
        token.type() != TokenType.OPERATOR) return false;


        OperatorToken operator = (OperatorToken) token;
        return operator.value.equals("-") || operator.value.equals("+") || operator.value.equals("!");
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        OperatorToken operator = (OperatorToken) tokenizer.next();
        return new ExpressionToken(
                operator.value,
                operator.value.equals("!") ? new EmptyToken() : new NumberToken(0),
                accessor.parse(Context.EXPRESSION)
        );
    }

    @Override
    public Context nextContext() {
        return null;
    }
}
