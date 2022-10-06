package list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements StoreBook {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findAllByNamed(String criteria) {
        return books.stream()
                .filter(book -> book
                        .getNamedBook()
                        .toLowerCase()
                        .contains(criteria.toLowerCase()))
                .collect(Collectors.toList());
    }
}