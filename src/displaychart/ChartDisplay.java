package displaychart;

import main.Summary;
import util.ExceptionManager;

import java.util.ArrayList;

/**
 * The ChartDisplay class was created for displaying data in a chart.
 * The chart is a textual chart.
 * It has 24 rows and 80 columns.
 * This class displays 79 groups at most.
 * If the number for groups is larger than 79, it throws an exception.
 * The ChartDisplay is one of the child classes of DisplayData.
 */

public class ChartDisplay extends DisplayData {
    private String[][] chart = new String[24][80];
    private ArrayList<Integer> values = new ArrayList<>();
    private static final int LINE = 22;
    private static final double ROUND = 0.5;

    // Constructor
    protected ChartDisplay(ArrayList<Summary> data) {
        // This constructor accepts an array list of Summary instances as a parameter
        // and construct a new ChartDisplay instance.
        
        super(data);
        setValue();
    }
    
    // Getter and setter
    private void setValue() {
        // This method gets all values of all groups.

        ArrayList<Summary> sum = getData();
        for (Summary d : sum) {
            values.add(d.getValue());
        }
    }

    //Method
    @Override
    public void display() throws ArithmeticException {
        // This method displays a chart, with 79 being the maximum number of groups allowed
        // If the number of groups exceeds 79 (80 or more), it throws exception.

        int size = values.size();
        int max = findMax();
        int time = chart[0].length / values.size();
        int count = 0;
        int col = 1;
        ExceptionManager.checkChartSize(size);
        ExceptionManager.checkDataSize(max);

        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[0].length; j++) {
                chart[i][j] = " ";
            }
        }

        for (int i = 0; i < chart[0].length; i++) {
            chart[chart.length - 1][i] = "_";
        }

        for (int i = 0; i < chart.length; i++) {
            chart[i][0] ="|";
        }

        for (int i = 0; i < values.size(); i++) {
            int row = (int) (values.get(count) * LINE / max + ROUND);
            int rowAct = Math.abs(row - LINE);
            chart[rowAct][col] = "*";
            count++;
            col += time;
        }

        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[0].length; j++) {
                System.out.print (chart[i][j]);
            }
            System.out.println(" ");
        }
    }

    private int findMax() {
        // This method finds the maximum value of all groups.
        // This is for appointing the group with the highest value
        // as the highest one in the chart.

        int max = values.get(0);
        for (int value : values) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }
 
}
