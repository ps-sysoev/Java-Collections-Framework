package set.uniquenumbers;

import java.util.*;
import java.util.stream.Collectors;

public class UniqueTerminal {
    public Set<Integer> getUniqueNumbersByDesc(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new))
                .descendingSet();
    }
}