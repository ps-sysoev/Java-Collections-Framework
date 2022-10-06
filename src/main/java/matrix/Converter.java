package matrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public List<Integer> convertToList(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toList());
    }
}