package main;

import display.DisplayData;
import util.UserInputManager;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        UserInputManager.displayMenu("""
                   Welcome! This is an analysis machine for COVID-19 data from 2020 to 2021.
                   Do you want to analyze the data? (Y/N)>>\s""");
        String ans = UserInputManager.getStrUserInput();

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

            UserInputManager.displayMenu("\nDo you want to analyze the data again? (Y/N)>> ");
            ans = sc.nextLine();
        }
        UserInputManager.displayMenu("Thank you for using our service.\n");
    }
}
