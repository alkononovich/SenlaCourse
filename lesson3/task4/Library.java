package task4;

public class Library {
    private Book[] books;
    private Reader[] readers;

    public Library() {

    }

    public Library(Book[] books, Reader[] readers) {
        this.books = books;
        this.readers = readers;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Reader[] getReaders() {
        return readers;
    }

    public void setReaders(Reader[] readers) {
        this.readers = readers;
    }

    
}
