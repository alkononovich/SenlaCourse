package task4;

public interface Book {

    Reader getReader();

    void setReader(Reader reader);

    String getName();

    void setName(String name);

    BookStatus getStatus();

    void setStatus(BookStatus status);
}
