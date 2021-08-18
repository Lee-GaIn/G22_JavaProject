package display;

import main.Summary;
import main.UserInterface;

import java.util.ArrayList;

public class DisplayData {
    ArrayList<Summary> data;
    ArrayList<String> col;
    ArrayList<String> row;

    // Constructor
    protected DisplayData(ArrayList<Summary> data){
        this.data = data;
    }

    // Getter and setter

    // Method
    public String toString(){
        return "test";
    }
    public static DisplayData createDisplayDataObj(ArrayList<Summary> summaryList){
        String displayMenu = """
                            [STEP 3]
                            ************************************************************
                            Available data display options
                            \t[1] Tabular display
                            \t[2] Chart display
                            ************************************************************
                            Please choose your grouping condition(1/2)>>\s""";
        UserInterface.displayMenu(displayMenu);
        int display = UserInterface.getIntUserInput();

        DisplayData dd = new DisplayData(summaryList);
        switch (display){
            case 1:
                dd = new TabularDisplay(summaryList);
                break;
            case 2:
                dd = new ChartDisplay(summaryList);
                break;
        }

        return dd;
    }

    public void display(){}


}
