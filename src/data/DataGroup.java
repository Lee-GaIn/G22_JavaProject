package data;

import java.util.ArrayList;

public class DataGroup {
    private ArrayList<Data> groupedData = new ArrayList<>();

    // Constructor
    public DataGroup() {
    }

    public DataGroup(ArrayList<Data> groupedData) {
        this.groupedData = groupedData;
    }

    //Getter and Setter
    public ArrayList<Data> getGroupedData() {
        return groupedData;
    }

    //Method
    public void addData(Data dt) {
        groupedData.add(dt);
    }

    public void displayDG() {
        for (Data d : groupedData) {
            System.out.println(d);;
            System.out.println();
        }
    }
}