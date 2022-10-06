package map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookiesUtils {
    private final static String SPEC_SYMBOL = ";";
    private final static String SPEC_SYMBOL_VALUE = ":";

    public static Map<String, Pair> getCookies(List<String> cookiesTestList) {
        Map<String, Pair> stringPairMap = new HashMap<>();

        for (String cookie : cookiesTestList) {
            String[] cookieNameAndValue = cookie.split(SPEC_SYMBOL_VALUE);

            String[] cookieValueAndFlag = cookieNameAndValue[1].split(SPEC_SYMBOL);

            if (cookieValueAndFlag.length == 2) {
                stringPairMap.put(cookieNameAndValue[0],
                        Pair.of(cookieNameAndValue[0],
                                cookieValueAndFlag[0],
                                Flag.valueOf(cookieValueAndFlag[1]),
                                Flag.HTTP_ONLY)
                );
            } else {
                stringPairMap.put(cookieNameAndValue[0],
                        Pair.of(cookieNameAndValue[0],
                                cookieValueAndFlag[0],
                                Flag.values())
                );
            }
        }

        return stringPairMap;
    }
}