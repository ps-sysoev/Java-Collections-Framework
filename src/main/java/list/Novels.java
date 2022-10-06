package list;

public class Novels extends Book {
    public Novels(String author, String namedBook) {
        super(author, namedBook);
    }

    @Override
    public TypeBook getTypeBook() {
        return TypeBook.NEW;
    }
}