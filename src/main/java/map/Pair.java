package map;

import java.util.Arrays;

public class Pair {
    private final Cookie cookie;
    private final Flag[] flags;

    private Pair(Cookie cookie, Flag[] flags) {
        this.cookie = cookie;
        this.flags = flags;
    }

    public static Pair of(String name, String value, Flag... flags) {
        return new Pair(new Cookie(name, value), flags);
    }

    public Cookie getCookie() {
        return cookie;
    }

    public Flag[] getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "cookie=" + cookie +
                ", flags=" + Arrays.toString(flags) +
                '}';
    }
}