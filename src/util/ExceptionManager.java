package util;

import java.util.regex.Pattern;

public class ExceptionManager {
    // new IllegalArgumentException
    // new IndexOutOfBoundsException
    // new ArithmeticException

    public static boolean isValidGeographicInput(String input){
        // This method accepts input from user as a string.
        // and returns true if geographic string does not include number
        // otherwise returns false.
        String regexp = "^[^0-9]+$";
        return Pattern.matches(regexp, input);
    }
}
