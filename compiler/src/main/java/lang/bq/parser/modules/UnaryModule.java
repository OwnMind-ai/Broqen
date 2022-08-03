package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.lowlevel.IdentifierToken;
import lang.bq.parser.tokens.lowlevel.NumberToken;
import lang.bq.parser.tokens.lowlevel.OperatorToken;
import lang.bq.syntax.Operators;

public class UnaryModule implements ForkedModule{
    private IdentifierToken name;

    @Override
    public void setPrevious(Token token) {
        assert token.type() == TokenType.IDENTIFIER;
        this.name = (IdentifierToken) token;
    }

    @Override
    public boolean isNextForked(Token token, Context context) {
        return this.isNext(token, context);
    }

    @Override
    public boolean isNext(Token token, Context context) {
        return (context == Context.FUNCTION || context == Context.EXPRESSION) &&
                (token.is(TokenType.OPERATOR, Operators.INCREMENT) ||
                 token.is(TokenType.OPERATOR, Operators.DECREMENT));
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        OperatorToken operator;

        if(this.name == null) {
            operator = (OperatorToken) tokenizer.next();
            this.name = (IdentifierToken) tokenizer.next();
        } else {
            operator = (OperatorToken) tokenizer.next();
        }

        return new ExpressionToken(
                operator.value == Operators.INCREMENT ? Operators.ADD : Operators.SUBTRACT,
                new NumberToken(1), name
        );
    }


    @Override
    public Context nextContext() {
        return null;
    }
}
