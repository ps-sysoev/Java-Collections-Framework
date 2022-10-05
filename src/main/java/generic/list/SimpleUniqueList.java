package generic.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * SimpleUniqueList уникальный динамический список который не допускает хранения дубликатов.
 * Расширяется в два раза при его заполнении. Реализовано fail-fast поведение.
 *
 * @param <T> тип хранимых элементов
 */
public class SimpleUniqueList<T> implements ListContainer<T> {
    private T[] array;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор с задаваемой начальной емкостью списка
     *
     * @param initialCapacity начальная емкость списка
     */
    public SimpleUniqueList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Incorrect parameter initialCapacity: " + initialCapacity);
        } else if (initialCapacity == 0) {
            array = (T[]) new Object[]{};
        } else {
            array = (T[]) new Object[initialCapacity];
        }
    }

    /**
     * Конструктор с начальной емкостью списка по умолчанию (10 элементов)
     */
    public SimpleUniqueList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавление нового элемента в список.
     * Если элемент уже есть в списке, то добавление не происходит
     *
     * @param value добавляемый в список элемент
     */
    @Override
    public void add(T value) {
        if (isNoDuplicateValue(value)) {
            modCount++;

            if (size == array.length) {
                array = growDoubling();
            }

            array[size] = value;
            size++;
        }
    }

    /**
     * Установка нового элемента списка по индексу
     * Если элемент уже есть в списке, то установка не происходит
     *
     * @param index    индекс
     * @param newValue новый (замещающий) элемент списка
     * @return предыдущий (замещаемый) элемент списка
     */
    @Override
    public T set(int index, T newValue) {
        if (checkingOccurrenceOfRange(index)) {
            T oldValue = array[index];

            if (!oldValue.equals(newValue) && isNoDuplicateValue(newValue)) {
                array[index] = newValue;
            }

            return oldValue;
        }

        throw new IndexOutOfBoundsException("Incorrect index: " + index + " out of range: [0, " + size + "]");
    }

    /**
     * Удаление элемента списка по индексу
     *
     * @param index индекс удаляемоего элемента
     * @return удаляемый элемент
     */
    @Override
    public T remove(int index) {
        if (checkingOccurrenceOfRange(index)) {
            modCount++;

            T oldValue = array[index];
            int newSize = --size;

            System.arraycopy(array, index + 1, array, index, newSize - index);

            array[size] = null;

            return oldValue;
        }

        throw new IndexOutOfBoundsException("Incorrect index: " + index + " out of range: [0, " + size + "]");
    }

    /**
     * Получение элемента списка по индексу
     *
     * @param index индекс
     * @return элемент списка
     */
    @Override
    public T get(int index) {
        if (checkingOccurrenceOfRange(index)) {
            return array[index];
        }

        throw new IndexOutOfBoundsException("Incorrect index: " + index + " out of range: [0, " + size + "]");
    }

    /**
     * Возвращает размер списка
     *
     * @return текущий размер списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка вхождения индекса в диапазон списка
     *
     * @param index индекс
     * @return true - в диапазоне
     */
    private boolean checkingOccurrenceOfRange(int index) {
        return index <= (size - 1) && index >= 0;
    }

    /**
     * Проверка на отсутствие дубликата элемента в списке
     *
     * @param checkedValue проверяемый элемент
     * @return true - дубликата нет
     */
    private boolean isNoDuplicateValue(T checkedValue) {
        int searchResult = Arrays.asList(array).indexOf(checkedValue);

        return searchResult < 0;
    }

    /**
     * При заполнении списка, размер списка (массива) удваивается
     *
     * @return удвоенный массив
     */
    private T[] growDoubling() {
        int oldCapacity = array.length;

        if (oldCapacity == 0) {
            return (T[]) new Object[DEFAULT_CAPACITY];
        }

        return Arrays.copyOf(array, oldCapacity << 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int controlModCount = modCount;
            int currentIndex;
            int lastIndex;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                checkListChanges();

                if (currentIndex >= size) {
                    throw new IndexOutOfBoundsException("next() - incorrect index: " + currentIndex +
                            " out of range: [0, " + size + "]");
                }

                lastIndex = currentIndex;
                currentIndex++;

                return array[lastIndex];
            }

            /**
             * Проверка списка на изменение
             * Throws: ConcurrentModificationException – если счетчик
             */
            void checkListChanges() {
                if (modCount != controlModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public String toString() {
        return "SimpleUniqueList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", modCount=" + modCount +
                '}';
    }
}