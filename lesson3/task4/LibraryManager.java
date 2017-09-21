package task4;

public class LibraryManager implements Manager{

    private MemoryManager<Book> bookMemoryManager;
    private MemoryManager<Reader> readerMemoryManager;

    private Library library;

    public LibraryManager() {
        this.bookMemoryManager = new MemoryManager<>();
        this.readerMemoryManager = new MemoryManager<>();
    }

    public LibraryManager(Library library) {
        this.library = library;
        this.bookMemoryManager = new MemoryManager<>();
        this.readerMemoryManager = new MemoryManager<>();
    }

    public void addBook(Book book){
        for (int i = 0; i < library.getBooks().length; i++) {

            if(bookMemoryManager.isFull(library.getBooks())){
                this.library.setBooks(bookMemoryManager.extendArray(library.getBooks()));
            }

            if(library.getBooks()[i] == null) {
                library.getBooks()[i] = book;
                return;
            }
        }
    }

    public void addReader(Reader reader){
        for (int i = 0; i < library.getReaders().length; i++) {

            if(readerMemoryManager.isFull(library.getReaders())){
                this.library.setReaders(readerMemoryManager.extendArray(library.getReaders()));
            }

            if(library.getReaders()[i] == null) {
                library.getReaders()[i] = reader;
                return;
            }
        }
    }

    public void subscribeOnBook(Reader reader, Book book) {
        if(book.getStatus() == BookStatus.BUSY) {
            System.out.println(BookStatus.BUSY.getMessage());
        }
        reader.addBook(book);
        book.setReader(reader);
        book.setStatus(BookStatus.BUSY);
    }

    public void unsubscribeOnBook(Reader reader, Book book){
        if(book.getStatus() == BookStatus.FREE) {
            System.out.println(BookStatus.FREE.getMessage());
        }
        reader.deleteBook(book);
        book.setReader(null);
        book.setStatus(BookStatus.FREE);

    }

    public Reader whereIsBook(Book book) {
        for (Reader reader: this.library.getReaders()) {
            for(Book b: reader.getBooks()) {
                if(book.equals(b)) {
                    return reader;
                }
            }
        }
        return null;
    }
    
    public void whereIsAllBooks () {
    	for (int i = 0; i < library.getBooks().length; i++) {
    		if (library.getBooks()[i].getReader() == null)
    			System.out.println(library.getBooks()[i] + " in library");
    		else System.out.println(library.getBooks()[i] + " at " + library.getBooks()[i].getReader());
    	}
    }
    
    public void printAllReaders () {
    	for (int i = 0; i < library.getReaders().length; i++) {
    		System.out.println(library.getReaders()[i]);
    	}
    }
}
