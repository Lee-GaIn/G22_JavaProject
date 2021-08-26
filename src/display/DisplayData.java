package display;

import main.Summary;
import util.UserInputManager;

import java.util.ArrayList;

public class DisplayData {
    private ArrayList<Summary> data = new ArrayList<>();

    // Constructor
    protected DisplayData(ArrayList<Summary> data){
        this.data = data;
    }

    // Getter and setter
    protected ArrayList<Summary> getData() {
        return data;
    }

    // Method
    public static DisplayData createDisplayDataObj(ArrayList<Summary> summaryList) {
        String displayMenu = """
                            [STEP 3]
                            ************************************************************
                            Available data display options
                            \t[1] Tabular display
                            \t[2] Chart display
                            ************************************************************
                            Please choose your grouping condition(1/2)>>\s""";
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
                // raise error
        }
        return dd;
    }

    public void display(){

    }
}
