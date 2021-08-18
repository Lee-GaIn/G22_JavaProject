package main;

import display.DisplayData;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws  Exception{
        Scanner sc = new Scanner(System.in);

        displayMenu("""
                   Welcome! This is an analysis machine for COVID-19 data from 2020 to 2021.
                   Do you want to analyze the data? (Y/N)>>\s""");
        String ans = sc.nextLine();

        while (ans.equalsIgnoreCase("Y")){
            // Data part
            Data userData = Data.createDataObj();
            System.out.println(userData);

            // Summary part
            ArrayList<Summary> summaryList = Summary.createSummaryObj(userData);
            for(Summary s: summaryList){
                System.out.println(s);
            }

            // Display part
            DisplayData displayData = DisplayData.createDisplayDataObj(summaryList);
            displayData.display();

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
        int res = Integer.parseInt(sc.nextLine().trim());
        System.out.printf("\n");

        // FIXME: 2021-08-18
        // if user do not put available input like "abc"

        return res;
    }
}
