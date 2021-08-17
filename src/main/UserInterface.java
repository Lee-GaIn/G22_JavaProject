package main;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws  Exception{
        Scanner sc = new Scanner(System.in);
        displayMenu("Welcome! This is an analysis machine for COVID-19 data from 2020 to 2021. \n" +
                        "Do you want to analyze the data? (Y/N)>> ");
        String ans = sc.nextLine();
        while (ans.equalsIgnoreCase("Y")){
            // Data part
            Date d = Date.createDateObj();
            System.out.println(d);

            // Summary part
            ArrayList<Summary> s = Summary.createSummaryObj(d);
//            System.out.println(s);

            // Display part


            // etc
            displayMenu("Do you want to analyze the data again? (Y/N)>> ");
            ans = sc.nextLine();
        }

        displayMenu("Thank you for using our service.\n");
    }

    public static void displayMenu(String menu){
        System.out.printf(menu);
    }

    public static int getIntUserInput(){
        Scanner sc = new Scanner(System.in);
        int res = Integer.parseInt(sc.nextLine());
        System.out.printf("\n");

        return res;
    }
}
