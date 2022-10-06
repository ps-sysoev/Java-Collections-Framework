package list;

import java.util.Objects;

public abstract class Book {
    private String author;
    private String namedBook;

    public Book(String author, String namedBook) {
        this.author = author;
        this.namedBook = namedBook;
    }

    public abstract TypeBook getTypeBook();

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNamedBook() {
        return namedBook;
    }

    public void setNamedBook(String namedBook) {
        this.namedBook = namedBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        return Objects.equals(author, book.author) && Objects.equals(namedBook, book.namedBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, namedBook);
    }
}