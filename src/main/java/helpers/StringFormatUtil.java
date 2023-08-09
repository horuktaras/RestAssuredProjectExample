package helpers;

public class StringFormatUtil {
    private final static String CONCATENATION = "%s%s";

    public static String concatenation(String first, String second) {
        return String.format(CONCATENATION, first, second);
    }
}