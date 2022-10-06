package list;

public class Stories extends Book {
    public Stories(String author, String namedBook) {
        super(author, namedBook);
    }

    @Override
    public TypeBook getTypeBook() {
        return TypeBook.USED;
    }
}