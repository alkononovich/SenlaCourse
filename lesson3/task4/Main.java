package task4;

public class Main {
    public static void main(String[] args) {
        Book[] books = new Book[]{
                new BookImpl("Vedmak"),
                new BookImpl("LordOfTheRings"),
                new BookImpl("SongOdIce&Flame"),
                new BookImpl("LegendOfDino"),
                new BookImpl("TomSoyer"),
                new BookImpl("It"),
                new BookImpl("WizardFromOz"),
                new BookImpl("ThreeFromForest"),
                new BookImpl("Master&Margarita"),
                new BookImpl("WishItWontItDoIt"),
                new BookImpl("Alchemist"),
                new BookImpl("Brida"),
                new BookImpl("NightGuard"),
        };

        Reader[] readers = new Reader[]{
                new ReaderImpl("Dmitry"),
                new ReaderImpl("Olga"),
                new ReaderImpl("Alexander")
        };

        Library library = new Library();
        library.setBooks(books);
        library.setReaders(readers);
        LibraryManager libraryManager = new LibraryManager(library);

        
        libraryManager.subscribeOnBook(readers[0], books[0]);
        libraryManager.subscribeOnBook(readers[0], books[1]);

        libraryManager.subscribeOnBook(readers[1], books[4]);

        libraryManager.subscribeOnBook(readers[2], books[2]);
        libraryManager.unsubscribeOnBook(readers[2], books[3]);

       
           
        

        System.out.println(books[4] + " at " + libraryManager.whereIsBook(books[4]));
        System.out.println(readers[0] + " have a " + readers[0].lookBooks());
        libraryManager.whereIsAllBooks();
        libraryManager.printAllReaders();
    }
}
