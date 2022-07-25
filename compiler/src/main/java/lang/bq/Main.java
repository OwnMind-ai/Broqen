package lang.bq;

import lang.bq.parser.CharStream;
import lang.bq.parser.tokenizer.Tokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("../snippets/first.bq");
        FileInputStream stream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        stream.read(data);
        stream.close();

        String code = new String(data, StandardCharsets.UTF_8);
        Tokenizer t = new Tokenizer(new CharStream(code));

        while(!t.eof())
            System.out.println(t.next());
    }
}