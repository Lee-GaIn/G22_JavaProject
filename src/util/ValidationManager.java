package util;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationManager {
    static boolean isValidGeographicInput(String input) {
        // This method accepts input from user as a string.
        // and returns true if geographic string does not include number.
        // Otherwise, returns false.
        String regexp = "^[^0-9]+$";
        return Pattern.matches(regexp, input);
    }

    static boolean isTimeRangeNotNull(LocalDate[] timeRange) {
        // This method accepts time range as an array of LocalDate
        // and returns true if both the start date and end date is not null.
        // Otherwise returns false.

        return (timeRange[0] != null && timeRange[1] != null);
    }

    static boolean isValidTimeRange(LocalDate[] timeRange) {
        // This method accepts timeRange as an array of LocalDate
        // and returns true if the end date is after the start date.
        // Otherwise, returns false.

        return timeRange[1].compareTo(timeRange[0]) > 0;
    }

    static boolean isValidYear(LocalDate[] timeRange) {
        // This method accepts timeRange as an array of LocalDate
        // and returns true if both start date and end date is from 2020 to 2021.
        // Otherwise, returns false.

        for(LocalDate ld : timeRange) {
            int year = ld.getYear();
            if(!(year == 2020 || year == 2021)){
                return false;
            }
        }

        return true;
    }

    static boolean isValidGroupNum(int size, int numOfDays) {
        // This method accepts size and numOfDays as an integer.
        // and returns false if size is not divided into numOfDays.
        // Otherwise, returns true.

        return size % numOfDays == 0;
    }

    static boolean isInt (String input) {
        // This method accepts input as a string.
        // and returns false if the input is not an instance of Integer.
        // Otherwise, returns true.

        try {
            int res = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    static boolean isValidChartSize(int size) {
        // This method accepts size as an int.
        // and returns true if the size is less than 79 or equal to 79 which is the size of the maximum column in the requirement.
        // Otherwise, returns false.

        return size <= 79;

    }
}