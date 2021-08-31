package displaychart;

import main.Summary;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The TabularDisplay class was created for displaying data in a table.
 * The table has two rows, which are 'Range' and 'Value'.
 * The TabularDisplay is one of the child classes of DisplayData.
 */

public class TabularDisplay extends DisplayData {
    private ArrayList<String> row = new ArrayList<>();
    private static final String HEADER_SPACE = "\t\t\t\t\t\s\s";
    private static final String NO_GROUP_SPACE = "\t\t\t\t\s\s";
    private static final String RANGE_SPACE= "\s\s";

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

        String header = "Range" + HEADER_SPACE + "Value\n";
        ArrayList<String> row = getRow();
        ArrayList<Summary> data = getData();
        row.add(header);

        for (Summary s : data) {
            LocalDate[] timeRange = s.getTimeRange();
            int value = s.getValue();
            int isOneDate = (timeRange[0].equals(timeRange[1])) ? 1 : -1;

            switch (isOneDate) {
                case 1 -> row.add(String.format("%s" + NO_GROUP_SPACE + "%d\n", timeRange[0], value));
                case -1 -> row.add(String.format("%s - %s"+ RANGE_SPACE + "%d\n", timeRange[0], timeRange[1], value));
            }
        }
    }

    protected ArrayList<String> getRow() {
        // This getter method returns row of the TabularDisplay instance.

        return row;
    }

    // Method
    @Override
    public void display() {
        // This method displays a table for data of the instance.
        // There are two columns.
        // The first column shows the time range
        // and the second column shows the value.

        for(String r : row) {
            System.out.print(r);
        }
    }
}
