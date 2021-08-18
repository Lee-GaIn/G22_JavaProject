package display;

import main.Summary;

import java.time.LocalDate;
import java.util.ArrayList;

public class TabularDisplay extends DisplayData{
    //Constructor
    protected TabularDisplay(ArrayList<Summary> data){
        super(data);
        setRow();
    }

    //Getter and setter
    private void setRow(){
        String header = "Range\t\t\t\t\t\s\sValue\n";
        ArrayList<String> row = getRow();
        ArrayList<Summary> data = getData();
        row.add(header);

        for(Summary s : data){
            LocalDate[] timeRange = s.getTimeRange();
            int value = s.getValue();
            int isOneDate = (timeRange[0].equals(timeRange[1]))? 1: -1;

            switch (isOneDate) {
                case 1 -> row.add(String.format("%s\t\t\t\t\s\s%d\n", timeRange[0], value));
                case -1 -> row.add(String.format("%s - %s\s\s%d\n", timeRange[0], timeRange[1], value));
            }
        }
    }
}
