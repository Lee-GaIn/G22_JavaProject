package display;

import main.Summary;
import util.ExceptionManager;
import util.UserInputManager;

import java.util.ArrayList;

public class DisplayData {
    private ArrayList<Summary> data;

    // Constructor
    protected DisplayData(ArrayList<Summary> data) {
        // This constructor accepts an array list of Summary instances as a parameter
        // and construct a new Displaydata instance.

        this.data = data;
    }

    // Getter and setter
    protected ArrayList<Summary> getData() {
        // This getter method returns data of the DisplayData instance.

        return data;
    }

    // Method
    public static DisplayData createDisplayDataObj(ArrayList<Summary> summaryList) {
        // This method accepts an array list of Summary instances as a parameter
        // and returns a new DisplayData instance.
        // It throws an exception if the user input is invalid.


        // Choose the way to display data.

        String displayMenu = """
                            [STEP 3]
                            ************************************************************
                            Available data display options
                            \t[1] Tabular display
                            \t[2] Chart display
                            ************************************************************
                            Please choose the way to display data.(1/2)>>\s""";
        UserInputManager.displayMenu(displayMenu);
        int display = UserInputManager.getIntUserInput();

        DisplayData dd = new DisplayData(summaryList);
        switch (display) {
            case 1 -> dd = new TabularDisplay(summaryList);
            case 2 -> dd = new ChartDisplay(summaryList);
            default -> ExceptionManager.throwInvalidOption();
        }
        
        return dd;
    }

    public void display() {
        // This method displays the data of the instance.

        for(Summary s : data) {
            System.out.println(s);
        }
    }
}
