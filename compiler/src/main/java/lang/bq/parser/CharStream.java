package lang.bq.parser;

public class CharStream implements IStream<Character>{
    private final String text;

    private int pos, col;
    private int line = 1;

    public CharStream(String input) {
        this.text = input;
    }

    @Override
    public Character next() {
        char ch = this.text.charAt(this.pos++);
        if (ch == '\n') {
            this.line++;
            this.col = 0;
        } else {
            this.col++;
        }

        return ch;
    }

    @Override
    public Character peek() {
        return this.text.charAt(this.pos);
    }

    @Override
    public boolean eof() {
        return this.text.length() >= pos;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return col;
    }
}
