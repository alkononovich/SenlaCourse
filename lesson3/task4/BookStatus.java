package task4;

public enum BookStatus {
    FREE {
        @Override
        public String getMessage() {
            return "Book free";
        }
    },
    BUSY {
        @Override
        public String getMessage() {
            return "Book busy";
        }
    },
    NOT_FOUND {
        @Override
        public String getMessage() {
            return "The reader don't have this book";
        }
    };

    public abstract String getMessage();
}
