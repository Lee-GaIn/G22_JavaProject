package util;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * The ValidationManager was created to manage the validation of user input.
 */

public class ValidationManager {
    // Methods
    static boolean isValidGeographicInput(String input) {
        // This method accepts string "input" as a parameter
        // and returns true if "input" does not include any number.
        // Otherwise, returns false.

        String regexp = "^[^0-9]+$";
        return Pattern.matches(regexp, input);
    }

    static boolean isValidDateForm(String date) {
        // This method accepts string "date"
        // and returns true if "date" satisfies the pattern.
        // Otherwise, returns false.

        String datePattern = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))$";
        return Pattern.matches(datePattern, date);
    }

    static boolean isValidDayWeekForm(String timeRange) {
        // This method accepts string "timeRange"
        // and returns true if "timeRange" satisfies the pattern.
        // Otherwise, returns false.

        String dayWeekPattern = "^[0-9]+\\s?((day|days)|(week|weeks))$";
        return Pattern.matches(dayWeekPattern, timeRange);
    }

    static boolean isTimeRangeNotNull(LocalDate[] timeRange) {
        // This method accepts an array of LocalDate "timeRange" as a parameter
        // and returns true if both the start date and end date is not null.
        // Otherwise, returns false.

        return (timeRange[0] != null && timeRange[1] != null);
    }

    static boolean isValidTimeRange(LocalDate[] timeRange) {
        // This method accepts an array of LocalDate "timeRange" as a parameter
        // and returns true if the end date is after the start date.
        // Otherwise, returns false.

        return timeRange[1].compareTo(timeRange[0]) >= 0;
    }

    static boolean isValidYear(LocalDate[] timeRange) {
        // This method accepts an array of LocalDate "timeRange" as a parameter
        // and returns true if both start date and end date is from 2020 to 2021.
        // Otherwise, returns false.

        for(LocalDate ld : timeRange) {
            int year = ld.getYear();
            if (!(year == 2020 || year == 2021)) {
                return false;
            }
        }

        return true;
    }

    static boolean isValidNumOfDays(int size, int numOfDays) {
        // This method accepts integer "size" and integer "numOfDays" as parameters.
        // and returns false if "size" is not divided into "numOfDays".
        // Otherwise, returns true.

        return size % numOfDays == 0;
    }

    static boolean isNumOfDaysZero(int numOfDays) {
        // This method accepts integer "numOfDays" as a parameter.
        // and returns true if "numOfDays" is zero.
        // Otherwise, returns false.

        return numOfDays == 0;
    }

    static boolean isValidNumOfGroups(int size, int numGroups) {
        // This method accepts integer "size" and integer "numGroups" as parameters.
        // and returns false if "size" is larger than "numGroups".
        // Otherwise, returns true.

        return size > numGroups;
    }

    static boolean isNumOfGroupZero(int numGroups) {
        // This method accepts integer "numGroups" as a parameter.
        // and returns true if "numGroups" is zero.
        // Otherwise, returns false.

        return numGroups == 0;
    }

    static boolean isInt(String input) {
        // This method accepts string "input" as a parameter.
        // and returns false if the "input" is not an instance of Integer.
        // Otherwise, returns true.

        try {
            int res = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    static boolean isValidChartSize(int size) {
        // This method accepts integer "size" as a parameter.
        // and returns true if the "size" is less than 79 or equal to 79
        // which is the size of the maximum column in the requirement.
        // Otherwise, returns false.

        return size <= 79;
    }

    static boolean isDataZero(int dataSize) {
        // This method accepts integer "dataSize" as a parameter.
        // and returns true if "dataSize" is zero.
        // Otherwise, returns false.

        return dataSize == 0;
    }
}
