package task4;

public interface Reader {

    Book[] getBooks();

    void setBooks(Book[] books);

    void addBook(Book book);

    void deleteBook(Book book);

    String getName();

    void setName(String name);
    
    String lookBooks();
}
