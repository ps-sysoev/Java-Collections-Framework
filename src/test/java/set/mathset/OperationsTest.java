package set.mathset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OperationsTest {
    private final Operations operations = new Operations();

    @Test
    public void whenIntersection() {
        Set<Integer> first = new HashSet<>(Arrays.asList(5, 4, 7, 3));
        Set<Integer> second = new HashSet<>(Arrays.asList(10, 12, 7, 4, 2));

        Set<Integer> rsl = operations.intersection(first, second);

        Assertions.assertTrue(rsl.contains(7));
        Assertions.assertTrue(rsl.contains(4));
        Assertions.assertEquals(2, rsl.size());
    }

    @Test
    public void whenSymmetricalDifference() {
        Set<Integer> first = new HashSet<>(Arrays.asList(5, 12, 17, 4));
        Set<Integer> second = new HashSet<>(Arrays.asList(25, 33, 12, 4, 17, 8));

        Set<Integer> rsl = operations.symmetricalDifference(first, second);

        Assertions.assertTrue(rsl.contains(33));
        Assertions.assertTrue(rsl.contains(25));
        Assertions.assertTrue(rsl.contains(5));
        Assertions.assertTrue(rsl.contains(8));
        Assertions.assertEquals(4, rsl.size());
    }

    @Test
    public void whenUnion() {
        Set<Integer> first = new HashSet<>(Arrays.asList(4, 2));
        Set<Integer> second = new HashSet<>(Arrays.asList(5, 6, 4));

        Set<Integer> rsl = operations.union(first, second);

        Assertions.assertTrue(rsl.contains(4));
        Assertions.assertTrue(rsl.contains(2));
        Assertions.assertTrue(rsl.contains(5));
        Assertions.assertTrue(rsl.contains(6));
        Assertions.assertEquals(4, rsl.size());
    }

    @Test
    public void whenSubtractSecondFromFirst() {
        Set<Integer> first;
        Set<Integer> second;

        first = new HashSet<>(Arrays.asList(4, 2, 5));
        second = new HashSet<>(Arrays.asList(2, 6, 4, 1));

        Set<Integer> rslFromSecond = operations.subtract(first, second);
        Assertions.assertTrue(rslFromSecond.contains(5));
        Assertions.assertEquals(1, rslFromSecond.size());

        first = new HashSet<>(Arrays.asList(4, 2, 5));
        second = new HashSet<>(Arrays.asList(2, 6, 4, 1));

        Set<Integer> rslFromFirst = operations.subtract(second, first);
        Assertions.assertTrue(rslFromFirst.contains(6));
        Assertions.assertTrue(rslFromFirst.contains(1));
        Assertions.assertEquals(2, rslFromFirst.size());
    }
}