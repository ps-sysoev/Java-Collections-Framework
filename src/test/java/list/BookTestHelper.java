package list;

import java.util.List;

public abstract class BookTestHelper {

    public List<Book> getBooks() {
        return List.of(
                new Stories("Bronskiy V.A", "History Russia."),
                new Fantasy("Roling J.A", "Harry Potter. Part 7"),
                new Fantasy("Roling J.A", "Harry Potter. Part 5"),
                new Novels("Tolstoy L.N", "Anna Karenina"),
                new Stories("Rodinov K.T", "Travel in 1912")
        );
    }
}