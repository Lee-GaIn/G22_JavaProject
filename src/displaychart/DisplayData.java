package displaychart;

import main.Summary;
import util.DisplayManager;
import util.ExceptionManager;
import util.UserInputManager;

import java.util.ArrayList;

/**
 * The DisplayData class was created for ushering the user to create a new DisplayData instance
 * and to display the data in a specific form.
 * The DisplayData is a parent class of both ChartDisplay and TabularDisplay.
 */

public abstract class DisplayData {
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
        DisplayManager.displayMenu(displayMenu);
        int display = UserInputManager.getIntUserInput();

        DisplayData dd = new TabularDisplay(summaryList);
        switch (display) {
            case 1:
                break;

            case 2:
                dd = new ChartDisplay(summaryList);
                break;

            default:
                ExceptionManager.throwInvalidOption();

        }
        
        return dd;
    }

    public abstract void display();
}
