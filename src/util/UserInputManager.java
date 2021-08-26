package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputManager {
    public static void displayMenu(String menu){
        System.out.print(menu);
    }

    public static int getIntUserInput() throws NumberFormatException {
        String input = getStrUserInput();
        ExceptionManager.checkIntInput(input);
        int res = Integer.parseInt(input);
        System.out.print("\n");
        return res;
    }

    public static String getGeographicUserInput() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine().trim();
        ExceptionManager.checkGeographicException(res);
        System.out.print("\n");
        return res;
    }

    public static String getStrUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
}
