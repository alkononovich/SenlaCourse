package task4;


public class ReaderImpl implements Reader{
    private String name;
    private Book[] books;
    private MemoryManager<Book> bookMemoryManager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReaderImpl() {
        bookMemoryManager = new MemoryManager<>();
    }

    public ReaderImpl(String name, Book[] books) {
        this.name = name;
        this.books = books;
        bookMemoryManager = new MemoryManager<>();
    }

    public ReaderImpl(String name) {
        this.name = name;
        this.books = new Book[10];
        bookMemoryManager = new MemoryManager<>();
    }

    @Override
    public Book[] getBooks() {
        return books;
    }

    @Override
    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public void addBook(Book book) {
        if(this.books == null) {
            this.books[0] = book;
            return;
        }
        for (int i = 0; i < this.books.length; i++) {

            if(bookMemoryManager.isFull(this.books)){
                this.books = bookMemoryManager.extendArray(this.books);
            }

            if(this.books[i] == null) {
                this.books[i] = book;
                return;
            }
        }
    }

    @Override
    public void deleteBook(Book book){
        boolean isExist = false;
        for (int i = 0; i < this.books.length; i++) {
            if(this.books[i] != null && this.books[i].equals(book)) {
                this.books[i] = null;
                isExist = true;
            }
        }
        if(!isExist) {
            System.out.println(BookStatus.NOT_FOUND.getMessage());
        }
    }

    @Override
    public String toString() {
        return name;
    }
    
    public String lookBooks() {
    	String list = "";
    	for (int i = 0; i < books.length; i++) {
    		if (books[i] != null)
    			list = list + books[i] + "; ";
    	}
    	return list;
    }
}