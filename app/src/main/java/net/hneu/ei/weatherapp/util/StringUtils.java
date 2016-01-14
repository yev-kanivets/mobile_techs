package net.hneu.ei.weatherapp.util;

/**
 * Util class for string common operation
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
public class StringUtils {
    @SuppressWarnings("SimplifiableIfStatement")
    public static boolean equals(String first, String second) {
        if (first == null && second == null) return true;
        if (first == null) return false;

        return first.equals(second);
    }
}