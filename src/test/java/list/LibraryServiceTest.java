package list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibraryServiceTest extends BookTestHelper {
    private final SortedLibraryService libraryService = new SortedLibraryService();

    @Test
    public void getNewBooks() {
        List<Book> result = libraryService.getBooksByType(getBooks(), TypeBook.NEW);
        List<Book> expected = List.of(
                new Fantasy("Roling J.A", "Harry Potter. Part 7"),
                new Fantasy("Roling J.A", "Harry Potter. Part 5"),
                new Novels("Tolstoy L.N", "Anna Karenina")
        );

        Assertions.assertEquals(3, result.size());
        Assertions.assertIterableEquals(expected, result);
    }
}