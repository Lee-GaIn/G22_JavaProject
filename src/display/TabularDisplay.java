package display;

import main.Summary;

import java.time.LocalDate;
import java.util.ArrayList;

public class TabularDisplay extends DisplayData{
    protected TabularDisplay(ArrayList<Summary> data){
        super(data);
    }

    public void display(){
        String header = "Range\t\t\t\t\t\s\sValue\n";
        StringBuffer displayData = new StringBuffer(header);

        for(Summary s : data){
            LocalDate[] timeRange = s.getTimeRange();
            int value = s.getValue();
            int isOneDate = (timeRange[0].equals(timeRange[1]))? 1: -1;

            switch (isOneDate) {
                case 1 -> displayData.append(String.format("%s\t\t\t\t\s\s%d\n", timeRange[0], value));
                case -1 -> displayData.append(String.format("%s - %s\s\s%d\n", timeRange[0], timeRange[1], value));
            }
        }
        System.out.printf(displayData.toString());
    }
}
