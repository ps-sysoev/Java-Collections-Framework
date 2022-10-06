package list;

import java.util.List;

public interface StoreBook {
    void add(Book book);

    List<Book> findAllByNamed(String named);
}