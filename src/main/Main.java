package main;

import display.DisplayData;
import util.UserInputManager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserInputManager.displayMenu("""
                                       Welcome! This is an analysis machine for COVID-19 data from 2020 to 2021.
                                       
                                       [STEP 0]
                                       Do you want to analyze the data? (Y/N)>>\s""");
        String ans = UserInputManager.getStrUserInput();

        while (ans.equalsIgnoreCase("Y")) {
            try{
                // Data part
                Data userData = Data.createDataObj();
                userData.displayData();

                // Summary part
                ArrayList<Summary> summaryList = Summary.createSummaryObj(userData);
                Summary.showSummaryList(summaryList);

                // Display part
                DisplayData displayData = DisplayData.createDisplayDataObj(summaryList);
                displayData.display();

            } catch (Exception e) {
                String errMsg = "\nThe system detected error.\n" + e.getMessage()
                                + "The system will return back to the [STEP 0] automatically.\n\n";
                UserInputManager.displayMenu(errMsg);
            }

            UserInputManager.displayMenu("""
                                        [STEP 0]
                                        Do you want to analyze the data again? (Y/N)>>\s""");
            ans = UserInputManager.getStrUserInput();
        }

        UserInputManager.displayMenu("Thank you for using our service.\n");
    }
}
