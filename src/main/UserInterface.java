package main;

import display.DisplayData;
import util.ExceptionManager;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        displayMenu("""
                   Welcome! This is an analysis machine for COVID-19 data from 2020 to 2021.
                   Do you want to analyze the data? (Y/N)>>\s""");
        String ans = sc.nextLine();

        while (ans.equalsIgnoreCase("Y")){
            try{
                // Data part
                Data userData = Data.createDataObj();
                userData.showData();

                // Summary part
                ArrayList<Summary> summaryList = Summary.createSummaryObj(userData);
                Summary.showSummaryList(summaryList);

                // Display part
                DisplayData displayData = DisplayData.createDisplayDataObj(summaryList);
                displayData.display();

            } catch (Exception e){
                String errMsg = "\n" + e.getMessage();
                System.out.print(errMsg);
            }

            displayMenu("\nDo you want to analyze the data again? (Y/N)>> ");
            ans = sc.nextLine();
        }

        displayMenu("Thank you for using our service.\n");
    }

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
}
