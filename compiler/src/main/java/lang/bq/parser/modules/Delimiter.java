package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;

import java.util.LinkedList;

public interface Delimiter {
    Flags flags();

    default Token[] delimited(Tokenizer tokenizer, ParseMethod parseMethod){
        LinkedList<Token> tokens = new LinkedList<>();

        tokenizer.skip(flags().start);
        while (!tokenizer.eof()){
            if(tokenizer.unsafeSkip(flags().stop))
                break;
            else
                tokens.add(parseMethod.parse());

            tokenizer.unsafeSkip(flags().separator);
        }

        return tokens.toArray(new Token[0]);
    }

    class Flags{
        public final Token start, separator, stop;

        public Flags(Token start, Token separator, Token stop) {
            this.start = start;
            this.separator = separator;
            this.stop = stop;
        }
    }

    interface ParseMethod{
        Token parse();
    }
}
