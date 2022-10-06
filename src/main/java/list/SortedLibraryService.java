package list;

import java.util.List;
import java.util.stream.Collectors;

public class SortedLibraryService {
    /**
     * Метод возвращает все книги которые имеют нужный тип.
     * @param books книги
     * @return книги Б/У или Новые.
     */
    public List<Book> getBooksByType(List<Book> books, TypeBook typeBook) {
        return books.stream()
                .filter(book -> book
                        .getTypeBook()
                        .equals(typeBook))
                .collect(Collectors.toList());
    }
}