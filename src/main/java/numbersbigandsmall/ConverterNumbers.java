package numbersbigandsmall;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterNumbers {
    public List<SizeNumber> getTypeNumbersByNumbers(List<Integer> numbers) {
        List<SizeNumber> result = new ArrayList<>();

        for (Integer item : numbers) {
            if (item >= 1000) {
                result.add(SizeNumber.BIG);
            } else if (item == 0) {
                result.add(SizeNumber.ZERO);
            } else {
                result.add(SizeNumber.SMALL);
            }
        }

        return result;
    }

    public List<SizeNumber> getTypeNumbersByNumbersByStream(List<Integer> numbers) {
        return numbers.stream()
                .map(integer -> {
                    if (integer >= 1000) {
                        return SizeNumber.BIG;
                    }

                    if (integer == 0) {
                        return SizeNumber.ZERO;
                    }

                    return SizeNumber.SMALL;
                })
                .collect(Collectors.toList());
    }
}