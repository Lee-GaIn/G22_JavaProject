package util;

import java.util.Scanner;

public class UserInputManager {
    public static void displayMenu(String menu){
        System.out.print(menu);
    }

    public static int getIntUserInput(){
        Scanner sc = new Scanner(System.in);
        int res;

        try {
            res = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e){
            throw new NumberFormatException("The system detected invalid input. The valid value is a number.");
        }
        System.out.print("\n");
        return res;
    }

    public static String getGeographicUserInput() throws Exception{
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine().trim();
        if(!(ExceptionManager.isValidGeographicInput(res))){
            throw new Exception("The system detected invalid input. Please check your geographic input again.");
        }
        System.out.print("\n");
        return res;
    }

    public static String getStrUserInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
}
