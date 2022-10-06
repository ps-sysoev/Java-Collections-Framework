package map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class CookiesUtilsTest {

    @Test
    public void getCookies() {
        List<String> cookiesText = List.of(
                "session:v343-dsaa-1214-cqc;SESSION;",
                "user_id:153043;SECURE;",
                "authid:sdsdsd-vkkskds-sdssf-883;"
        );

        Map<String, Pair> cookieMap = CookiesUtils.getCookies(cookiesText);

        Pair resultBySession = cookieMap.get("session");
        Pair resultByUserId = cookieMap.get("user_id");
        Pair resultByAuthId = cookieMap.get("authid");

        Assertions.assertNotNull(resultBySession);
        Assertions.assertEquals(resultBySession.getCookie(), new Cookie("session", "v343-dsaa-1214-cqc"));
        Assertions.assertArrayEquals(resultBySession.getFlags(), new Flag[]{Flag.SESSION, Flag.HTTP_ONLY});

        Assertions.assertNotNull(resultByUserId);
        Assertions.assertEquals(resultByUserId.getCookie(), new Cookie("user_id", "153043"));
        Assertions.assertArrayEquals(resultByUserId.getFlags(), new Flag[]{Flag.SECURE, Flag.HTTP_ONLY});

        Assertions.assertNotNull(resultByAuthId);
        Assertions.assertEquals(resultByAuthId.getCookie(), new Cookie("authid", "sdsdsd-vkkskds-sdssf-883"));
        Assertions.assertArrayEquals(resultByAuthId.getFlags(), Flag.values());
    }
}