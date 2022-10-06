package numbersbigandsmall;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConverterNumbersTest {

    @Test
    public void getTypeNumbers() {
        ConverterNumbers converterNumbers = new ConverterNumbers();

        List<SizeNumber> result = converterNumbers.getTypeNumbersByNumbers(List.of(1015, -2, 0, 10543, 25644, 25, 53));

        List<SizeNumber> expected = List.of(
                SizeNumber.BIG, SizeNumber.SMALL, SizeNumber.ZERO,
                SizeNumber.BIG, SizeNumber.BIG, SizeNumber.SMALL, SizeNumber.SMALL);

        Assertions.assertIterableEquals(expected, result);
    }

    @Test
    public void getTypeNumbersByStream() {
        ConverterNumbers converterNumbers = new ConverterNumbers();

        List<SizeNumber> result = converterNumbers.getTypeNumbersByNumbersByStream(List.of(1015, -2, 0, 10543, 25644, 25, 53));

        List<SizeNumber> expected = List.of(
                SizeNumber.BIG, SizeNumber.SMALL, SizeNumber.ZERO,
                SizeNumber.BIG, SizeNumber.BIG, SizeNumber.SMALL, SizeNumber.SMALL);

        Assertions.assertIterableEquals(expected, result);
    }
}