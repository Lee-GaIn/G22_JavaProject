package util;

import java.time.LocalDate;
import java.util.InputMismatchException;

public class ExceptionManager {
    // If the user does not provide the proper type of input or input is out of range,
    // then InputMismatchException happens.

    // if(marks < 0 || marks > 100) // IllegalArgumentException

    // parse //NumberFormatException
    public static void throwInvalidOption() throws InputMismatchException {
        throw new InputMismatchException("""
                                        The unsupported option was chosen.
                                        Please check your option input again.""");
    }

    public static void checkGeographicException(String res) throws InputMismatchException {
        if(!(ValidationManager.isValidGeographicInput(res))) {
            throw new InputMismatchException("Please check your geographic input again.");
        }
    }

    public static void checkTimeRangeException(LocalDate[] timeRange) throws IllegalArgumentException {
        if(!(ValidationManager.isTimeRangeNotNull(timeRange))) {
            throw new NullPointerException("Please check your time range input again.");
        }

        if(!(ValidationManager.isValidTimeRange(timeRange))) {
            throw new IllegalArgumentException("""
                                                The end date is before the start date. 
                                                Please check your time range input again.""");
        }

        if(!(ValidationManager.isValidYear(timeRange))) {
            throw new IllegalArgumentException("""
                                                The start date and end date is not from 2020 to 2021.
                                                Please check your time range input again.""");
        }

    }

    public static void checkIntInput(String input) throws NumberFormatException {
        if (!(ValidationManager.isInt(input))) {
            throw new NumberFormatException("The valid value is a number.");
        }
    }

    public static void checkGroupNum(int size, int numOfDays) throws ArithmeticException {
        if (!(ValidationManager.isValidGroupNum(size, numOfDays))) {
            throw new ArithmeticException("""
                                            The dates cannot be divided into equal groups!
                                            Please check your time range input again.
                                            """);
        }
    }

    public  static void checkChartSize(int size) {
        if(!(ValidationManager.isValidChartSize(size))) {
            throw new ArithmeticException ("""
                                            Sorry for the inconvenience.
                                            Our system detected that the number of groups is more than the allowed number(79).
                                            The system will return back to the [STEP 0] automatically.""");
        }
    }

}
