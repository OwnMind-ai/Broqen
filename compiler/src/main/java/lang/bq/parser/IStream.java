package lang.bq.parser;

public interface IStream <T> {
    T next();
    T peek();
    boolean eof();
}
