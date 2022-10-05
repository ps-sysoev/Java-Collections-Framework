package generic.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * SimpleList динамический список основанный на массиве.
 * Расширяется в два раза при его заполнении. Реализовано fail-fast поведение.
 * Элементы в списке могут повторяться.
 *
 * @param <T> тип хранимых элементов
 */
public class SimpleList<T> implements ListContainer<T> {
    private T[] array;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор с задаваемой начальной емкостью списка
     *
     * @param initialCapacity начальная емкость списка
     */
    public SimpleList(int initialCapacity) {
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
    public SimpleList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавление нового элемента в список.
     *
     * @param value добавляемый в список элемент
     */
    @Override
    public void add(T value) {
        modCount++;

        if (size == array.length) {
            array = growDoubling();
        }

        array[size] = value;
        size++;
    }

    /**
     * Установка нового элемента списка по индексу
     *
     * @param index    индекс
     * @param newValue новый (замещающий) элемент списка
     * @return предыдущий (замещаемый) элемент списка
     */
    @Override
    public T set(int index, T newValue) {
        if (checkingOccurrenceOfRange(index)) {
            T oldValue = array[index];
            array[index] = newValue;

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
        return "SimpleList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", modCount=" + modCount +
                '}';
    }
}