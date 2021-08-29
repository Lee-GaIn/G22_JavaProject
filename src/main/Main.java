package main;

import displaychart.DisplayData;
import util.DisplayManager;
import util.UserInputManager;

import java.util.ArrayList;

/**
 * The Main class was created for ushering the user to implements an analysis machine
 * for COVID-19 data from 2020 to 2021.
 * After the user enters 'Y' to analyse the data,
 * it creates a new Data instance, an ArrayList of Summary instance and a DisplayData instance.
 * and shows the result that the user chooses.
 * These steps are repeated until the user enter any String or Character except either 'y' or 'Y'.
 */

public class Main {
    public static void main(String[] args) {
        DisplayManager.displayMenu("""
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
                Summary.displaySummaryList(summaryList);

                // Display part
                DisplayData displayData = DisplayData.createDisplayDataObj(summaryList);
                displayData.display();

            } catch (Exception e) {
                String errMsg = "\nThe system detected error.\n" + e.getMessage()
                                + "The system will return back to the [STEP 0] automatically.\n\n";
                DisplayManager.displayMenu(errMsg);
            }

            DisplayManager.displayMenu("""
                                        [STEP 0]
                                        Do you want to analyze the data again? (Y/N)>>\s""");
            ans = UserInputManager.getStrUserInput();
        }

        DisplayManager.displayMenu("Thank you for using our service.\n");
    }
}
