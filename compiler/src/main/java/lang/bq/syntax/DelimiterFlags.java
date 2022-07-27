package lang.bq.syntax;

import lang.bq.parser.modules.Delimiter;
import lang.bq.parser.tokens.Token;
import lang.bq.parser.tokens.lowlevel.PunctuationToken;

public enum DelimiterFlags {
    CALL(
            new PunctuationToken(Punctuations.PARENTHESES_START),
            new PunctuationToken(Punctuations.COMMA),
            new PunctuationToken(Punctuations.PARENTHESES_END)
    ),
    CODE_BLOCK(
            new PunctuationToken(Punctuations.CURLY_START),
            new PunctuationToken(Punctuations.SEMICOLON),
            new PunctuationToken(Punctuations.CURLY_END)
    ),;
    
    private final Delimiter.Flags flags;
    
    DelimiterFlags(Token start, Token separator, Token stop){
        this.flags = new Delimiter.Flags(start, separator, stop);
    }
    
    public Delimiter.Flags flags(){
        return this.flags;
    }
}
