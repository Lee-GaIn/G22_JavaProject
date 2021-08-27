package display;

import main.Summary;
import util.ExceptionManager;
import util.UserInputManager;

import java.util.ArrayList;

public class DisplayData {
    private ArrayList<Summary> data;

    // Constructor
    protected DisplayData(ArrayList<Summary> data){
        //This constructor accepts an array list of summary instances as a parameter
        //and construct a new displaydata instance.

        this.data = data;
    }

    // Getter and setter
    protected ArrayList<Summary> getData() {
        //This getter method returns data of the display data instance.

        return data;
    }

    // Method
    public static DisplayData createDisplayDataObj(ArrayList<Summary> summaryList) throws Exception {
        //This method accepts an array list of summary instances as a parameter
        //and returns a new display data instance.
        //It throws an exception if the user input is invalid.


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
            case 1:
                dd = new TabularDisplay(summaryList);
                break;

            case 2:
                dd = new ChartDisplay(summaryList);
                break;

            default:
                ExceptionManager.throwInvalidOption();

        }
        return dd;
    }

    public void display(){
        // FIXME: 2021-08-27 
    }
}
