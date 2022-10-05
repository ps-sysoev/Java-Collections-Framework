package generic.list;

public interface ListContainer<T> extends Iterable<T> {
    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();
}