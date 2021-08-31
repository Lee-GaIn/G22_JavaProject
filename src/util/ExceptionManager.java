package util;

import java.time.LocalDate;
import java.util.InputMismatchException;

/**
 * The ExceptionManager was created to manage exceptions for the analysis machine.
 * This class checks user input and throws appropriated exception
 * so that users can exit the programme when they want
 * as well as the programme runs smoothly.
 */

public class ExceptionManager {
    // Methods
    public static void throwInvalidOption() throws InputMismatchException {
        // This method throws InputMismatchException
        // if the user chooses the unsupported option.

        throw new InputMismatchException("""
                                        The unsupported option was chosen.
                                        Please check your option input again.""");
    }

    public static void checkIntInput(String input) throws NumberFormatException {
        // This method throws NumberFormatException
        // if the user does not put an integer value.

        if (!(ValidationManager.isInt(input))) {
            throw new NumberFormatException("""
                                            The valid value is a number.
                                            Please check your input again.""");
        }
    }

    public static void checkGeographicException(String res) throws InputMismatchException {
        // This method throws InputMismatchException
        // if the user puts invalid geographic value.

        if (!(ValidationManager.isValidGeographicInput(res))) {
            throw new InputMismatchException("Please check your geographic input again.");
        }
    }

    public static void checkDateFormException(String date) throws InputMismatchException {
        // This method throws InputMismatchException
        // if the user puts invalid date form.

        if (!(ValidationManager.isValidDateForm(date))) {
            throw new InputMismatchException("Please check your date input again.");
        }
    }

    public static void checkTimeRangeFormException(String timeRange) throws InputMismatchException {
        // This method throws InputMismatchException
        // if the user puts invalid time range form.

        if (!(ValidationManager.isValidDayWeekForm(timeRange))) {
            throw new InputMismatchException("Please check your time range input again.");
        }
    }

    public static void checkTimeRangeException(LocalDate[] timeRange) throws IllegalArgumentException {
        // This method throws IllegalArgumentException
        // if the user puts invalid time range value.

        if (!(ValidationManager.isTimeRangeNotNull(timeRange))) {
            throw new NullPointerException("Please check your time range input again.");
        }

        if (!(ValidationManager.isValidTimeRange(timeRange))) {
            throw new IllegalArgumentException("""
                                                The end date is before the start date.
                                                Please check your time range input again.""");
        }

        if (!(ValidationManager.isValidYear(timeRange))) {
            throw new IllegalArgumentException("""
                                                The start date and end date is not from 2020 to 2021.
                                                Please check your time range input again.""");
        }

    }

    public static void checkNumOfDays(int size, int numOfDays) throws IllegalArgumentException {
        // This method throws IllegalArgumentException
        // if the user puts invalid the number of groups.

        if (ValidationManager.isNumOfDaysZero(numOfDays)) {
            throw new IllegalArgumentException("""
                                                The number of groups cannot be zero.
                                                Please check the number of groups input again.
                                                 """);
        }

        if (!(ValidationManager.isValidNumOfDays(size, numOfDays))) {
            throw new IllegalArgumentException("""
                                            The dates cannot be divided into equal groups
                                            Please check the number of groups input again.
                                            """);
        }
    }

    public static void checkNumOfGroups(int size, int numGroups) throws IllegalArgumentException {
        // This method throws IllegalArgumentException
        // if the user puts invalid the number of groups.

        if (ValidationManager.isNumOfGroupZero(numGroups)) {
            throw new IllegalArgumentException("""
                                                The number of groups cannot be zero.
                                                Please check the number of groups input again.
                                                """);
        }

        if (!(ValidationManager.isValidNumOfGroups(size, numGroups))) {
            throw new IllegalArgumentException("""
                                                The number of groups is bigger than the number of days input
                                                Please check the number of groups input again.
                                                """);
        }
    }

    public static void checkChartSize(int size) throws ArithmeticException {
        // This method throws ArithmeticException
        // if the system detected that the number of groups is inappropriate for the requirement.

        if (!(ValidationManager.isValidChartSize(size))) {
            throw new ArithmeticException ("""
                                            Sorry for the inconvenience.
                                            The number of groups is more than the allowed number(79).
                                            """);
        }
    }

    public static void checkDataSize(int dataSize) throws ArithmeticException {
        // This method throws ArithmeticException
        // if the system detected that there is no data to display.

        if (ValidationManager.isDataZero(dataSize)) {
            throw new ArithmeticException ("""
                                            Sorry for the inconvenience.
                                            There is no data to display.
                                            """);
        }
    }
}
