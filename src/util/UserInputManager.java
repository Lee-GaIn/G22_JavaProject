package util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The UserInputManager was created to manage user input.
 */

public class UserInputManager {

    public static int getIntUserInput() throws NumberFormatException {
        // This method get integer input from the user
        // It throws NumberFormatException, if the user input is invalid.

        String input = getStrUserInput();
        ExceptionManager.checkIntInput(input);
        int res = Integer.parseInt(input);
        System.out.print("\n");
        return res;
    }

    public static String getGeographicUserInput() throws InputMismatchException {
        // This method get geographic input from the user
        // It throws InputMismatchException, if the user input is invalid.

        String res = getStrUserInput();
        ExceptionManager.checkGeographicException(res);
        System.out.print("\n");
        return res;
    }

    public static String getStrUserInput() {
        // This method get string input from the user.

        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
}
