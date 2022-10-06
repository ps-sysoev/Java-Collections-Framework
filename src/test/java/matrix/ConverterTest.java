package matrix;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConverterTest {
    @Test
    public void firstConverterTest() {
        int[][] matrix = {
                {11, 22, 33},
                {44, 55, 66},
                {77, 88, 99},
        };

        List<Integer> expected = Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99);

        List<Integer> result = new Converter().convertToList(matrix);

        assertIterableEquals(expected, result);
    }

    @Test
    public void secondConverterTest() {
        int[][] matrix = {
                {11, 22, 33},
                {44, 66},
                {77, 88, 99, 101},
                {-50}
        };

        List<Integer> expected = Arrays.asList(11, 22, 33, 44, 66, 77, 88, 99, 101, -50);

        List<Integer> result = new Converter().convertToList(matrix);

        assertIterableEquals(expected, result);
    }
}