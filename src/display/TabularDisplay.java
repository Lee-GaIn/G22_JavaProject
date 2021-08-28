package display;

import main.Summary;

import java.time.LocalDate;
import java.util.ArrayList;

public class TabularDisplay extends DisplayData {
    private ArrayList<String> row = new ArrayList<>();
    private static final String headerSpace = "\t\t\t\t\t\s\s";
    private static final String noGroupSpace = "\t\t\t\t\s\s";
    private static final String rangeSpace = "\s\s";

    // Constructor
    protected TabularDisplay(ArrayList<Summary> data) {
        // This constructor accepts an array list of Summary instances as a parameter
        // and construct a new TabularDisplay instance.

        super(data);
        setRow();
    }

    // Getter and setter
    private void setRow() {
        //  This setter method sets a row attribute for the instance.

        String header = "Range" + headerSpace + "Value\n";
        ArrayList<String> row = getRow();
        ArrayList<Summary> data = getData();
        row.add(header);

        for(Summary s : data) {
            LocalDate[] timeRange = s.getTimeRange();
            int value = s.getValue();
            int isOneDate = (timeRange[0].equals(timeRange[1]))? 1: -1;

            switch (isOneDate) {
                case 1 -> row.add(String.format("%s" + noGroupSpace + "%d\n", timeRange[0], value));
                case -1 -> row.add(String.format("%s - %s"+ rangeSpace + "%d\n", timeRange[0], timeRange[1], value));
            }
        }
    }

    protected ArrayList<String> getRow() {
        // This getter method returns row of the TabularDisplay instance.

        return row;
    }

    // Method
    @Override
    public void display(){
        // This method displays a table for data of the instance.

        for(String r : row) {
            System.out.print(r);
        }
    }
}
