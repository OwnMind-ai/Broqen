package lang.bq.parser.modules;

import lang.bq.parser.tokenizer.Tokenizer;
import lang.bq.parser.tokens.Token;

import java.util.LinkedList;
import java.util.List;

public interface Delimiter <T extends Token> {
    Flags flags();

    default List<T> delimited(Tokenizer tokenizer, ParseMethod<T> parseMethod){
        LinkedList<T> tokens = new LinkedList<>();

        tokenizer.skip(flags().start);
        while (!tokenizer.eof()){
            if(tokenizer.unsafeSkip(flags().stop))
                break;
            else
                tokens.add(parseMethod.parse());

            tokenizer.unsafeSkip(flags().separator);
        }

        return tokens;
    }

    class Flags{
        public final Token start, separator, stop;

        public Flags(Token start, Token separator, Token stop) {
            this.start = start;
            this.separator = separator;
            this.stop = stop;
        }
    }

    interface ParseMethod<T>{
        T parse();
    }
}
