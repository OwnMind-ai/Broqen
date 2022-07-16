package lang.bq.parser;

public class CharStream{
    private final String text;

    private int position, column;
    private int line = 1;

    public CharStream(String text){
        this.text = text;
    }

    public char next() {
        char ch = this.text.charAt(this.position++);
        if (ch == '\n') {
            this.line++;
            this.column = 0;
        } else {
            this.column++;
        }

        return ch;
    }

    public char peek() {
        return this.text.charAt(this.position);
    }

    public boolean isNext(String value){
        for (int i = 0; i < value.length(); i++) {
            if (this.text.length() <= position + i && this.text.charAt(this.position + i) != value.charAt(i))
                return false;
        }

        return true;
    }

    public boolean eof() {
        return this.text.length() <= this.position;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
