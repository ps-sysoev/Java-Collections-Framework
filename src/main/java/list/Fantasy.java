package list;

public class Fantasy extends Book {
    public Fantasy(String author, String namedBook) {
        super(author, namedBook);
    }

    @Override
    public TypeBook getTypeBook() {
        return TypeBook.NEW;
    }
}