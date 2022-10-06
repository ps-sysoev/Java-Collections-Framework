package list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibraryTest extends BookTestHelper {
    private final StoreBook library = new Library();

    /**
     * Добавить в библиотеку книги перед выполнением теста.
     */
    @BeforeEach
    public void addAllBooksInLibrary() {
        for (Book book : getBooks()) {
            library.add(book);
        }
    }

    @Test
    public void findAllByNamed() {
        List<Book> result = library.findAllByNamed("harry potter");
        List<Book> expected = List.of(
                new Fantasy("Roling J.A", "Harry Potter. Part 7"),
                new Fantasy("Roling J.A", "Harry Potter. Part 5")
        );

        Assertions.assertEquals(2, result.size());
        Assertions.assertIterableEquals(expected, result);
    }

    @Test
    public void findAllByNamedTwo() {
        List<Book> result = library.findAllByNamed("Anna karenina");
        List<Book> expected = List.of(
                new Novels("Tolstoy L.N", "Anna Karenina")
        );

        Assertions.assertEquals(1, result.size());
        Assertions.assertIterableEquals(expected, result);
    }
}