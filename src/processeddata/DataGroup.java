package processeddata;

import java.util.ArrayList;

public class DataGroup {
    private ArrayList<Data> groupedData = new ArrayList<>();

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
            System.out.printf("%s\n", d);
        }
    }

    public Data getData(int group) {
        return groupedData.get(group);
    }

    public int getSize() {
        return groupedData.size();
    }
}