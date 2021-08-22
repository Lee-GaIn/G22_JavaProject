package display;

import main.Summary;

import java.util.ArrayList;

public class ChartDisplay extends DisplayData {
    //Constructor
    protected ChartDisplay(ArrayList<Summary> data) {
        super(data);
        setRow();
    }

    //Getter and setter
    private void setRow() {
    }

    //Method
    private String[][] chart = new String[24][80];
    private ArrayList<Integer> values = new ArrayList<Integer>();

    public void display() {
        setValue();
        int size = values.size();
        if (size >= 80) {
            System.out.println ("ERROR: The maximum numbers of groups allowed is 79!");
            System.exit(2);
        }
        else {
            for (int i = 0; i < chart.length; i++) {
                for (int j = 0; j < chart[0].length; j++) {
                    chart[i][j] = " ";
                }
            }
            for (int i = 0; i < chart[0].length; i++) {
                chart[chart.length-1][i] = "_";
            }
            for (int i = 0; i < chart.length; i++) {
                chart[i][0] ="|";
            }
            setUp();

            for (int i = 0; i < chart.length; i++) {
                for (int j = 0; j < chart[0].length; j++) {
                    System.out.print (chart[i][j]);
                }
                System.out.println("");
            }
        }
    }

    public void setValue() {
        ArrayList<Summary> sum = getData();
        for (int i = 0; i < sum.size(); i++) {
            values.add( sum.get(i).getValue());
        }
    }

    public void setUp() {
        int max = findMax();
        int time = chart[0].length/values.size();
        int count = 0;
        int col = 1;
        for (int i = 0; i< values.size(); i++) {
            int row = (int) (values.get(count) * 23 / max + 0.5);
            int rowAct = Math.abs(row - 23);
            chart[rowAct][col] = "*";
            count++;
            col += time;
        }
    }

    public int findMax() {
        int max = values.get(0);
        for (int i = 0; i < values.size(); i++) {
            if (max < values.get(i)) {
                max = values.get(i);
            }
        }
        return max;
    }
}
