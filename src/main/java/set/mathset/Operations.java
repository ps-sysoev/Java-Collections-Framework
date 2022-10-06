package set.mathset;

import java.util.HashSet;
import java.util.Set;

/**
 * Выполняет операции с множествами
 */
public class Operations {

    /**
     * Пересечение множеств
     *
     * @param firstSet  первое множество
     * @param secondSet второе множество
     * @return Если пересечение выполнено успешно - возвращается новое множество, иначе - пустое
     */
    public Set<Integer> intersection(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resultOfIntersection = new HashSet<>(firstSet);

        if (resultOfIntersection.retainAll(secondSet)) {
            return resultOfIntersection;
        }

        return Set.of();
    }

    /**
     * Симметричная разность множеств
     *
     * @param firstSet первое множество (уменьшаемое)
     * @param secondSet второе множество (вычитаемое)
     * @return Если разность (симметричная) выполнена успешно - возвращается новое множество, иначе - пустое
     */
    public Set<Integer> symmetricalDifference(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resulOftUnion = union(firstSet, secondSet);

        Set<Integer> resultOfIntersection = intersection(firstSet, secondSet);

        Set<Integer> resultOfSymmetricalDifference = subtract(resulOftUnion, resultOfIntersection);

        if (resultOfSymmetricalDifference.isEmpty()) {
            return Set.of();
        }

        return resultOfSymmetricalDifference;
    }

    /**
     * Объединение множеств
     *
     * @param firstSet  первое множество
     * @param secondSet второе множество
     * @return Если объединение выполнено успешно - возвращается новое множество, иначе - пустое
     */
    public Set<Integer> union(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resultOfUnion = new HashSet<>(firstSet);

        if (resultOfUnion.addAll(secondSet)) {
            return resultOfUnion;
        }

        return Set.of();
    }

    /**
     * Разность множеств
     *
     * @param firstSet  первое множество (уменьшаемое)
     * @param secondSet второе множество (вычитаемое)
     * @return Если разность выполнена успешно - возвращается новое множество, иначе - пустое
     */
    public Set<Integer> subtract(Set<Integer> firstSet, Set<Integer> secondSet) {
        Set<Integer> resultOfSubtract = new HashSet<>(firstSet);

        if (resultOfSubtract.removeAll(secondSet)) {
            return resultOfSubtract;
        }

        return Set.of();
    }
}