package lang.bq.parser.modules;

import lang.bq.parser.Context;
import lang.bq.parser.ModuleAccessor;
import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.TokenType;
import lang.bq.parser.tokens.highlevel.ExpressionToken;
import lang.bq.parser.tokens.lowlevel.OperatorToken;
import lang.bq.parser.tokens.lowlevel.PunctuationToken;
import lang.bq.syntax.Punctuations;

public class ExpressionModule implements ParserModule{
    private ModuleAccessor accessor;
    private Tokenizer tokenizer;

    @Override
    public boolean isNext(Token token, Context context) {
        return (context == Context.FUNCTION || context == Context.EXPRESSION)
                && token.is(TokenType.PUNCTUATION, Punctuations.PARENTHESES_START);
    }

    @Override
    public Token parse(Tokenizer tokenizer, ModuleAccessor accessor) {
        this.accessor = accessor;
        this.tokenizer = tokenizer;

        tokenizer.skip(new PunctuationToken(Punctuations.PARENTHESES_START));
        Token result = this.buildTree(accessor.parse(Context.EXPRESSION), Integer.MAX_VALUE);
        tokenizer.skip(new PunctuationToken(Punctuations.PARENTHESES_END));

        return result;
    }

    private Token buildTree(Token previous, int priority) {
        Token token = tokenizer.peek();
        if (token != null && token.type() == TokenType.OPERATOR) {
            assert token instanceof OperatorToken;
            OperatorToken operator = (OperatorToken) token;

            if (priority > operator.value.priority()) {
                tokenizer.next();

                return this.buildTree(
                        new ExpressionToken(
                                operator.value,
                                previous,
                                this.buildTree(accessor.parse(Context.EXPRESSION), operator.value.priority())
                        ),
                        priority
                );
            }
        }

        return previous;
    }

    @Override
    public Context nextContext() {
        return Context.FUNCTION;
    }
}
