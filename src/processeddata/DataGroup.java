package processeddata;

import java.util.ArrayList;

public class DataGroup {
    private ArrayList<Data> groupedData = new ArrayList<>();

    //Getter and Setter
    public ArrayList<Data> getGroupedData() {
        //This getter method returns a getGroupedData attribute.

        return groupedData;
    }

    //Method
    public void addData(Data dt) {
        //This method add Data instance into groupedData.

        groupedData.add(dt);
    }

    public void displayDG() {
        //This method display the detail of a DataGroup.

        for (Data d : groupedData) {
            System.out.printf("%s\n", d);
        }
    }

    public Data getData(int idx) {
        //This method accepts integer "idx"
        //and returns a Data instance of the particular index.

        return groupedData.get(idx);
    }

    public int getSize() {
        // This method returns the size of groupedData as an integer.

        return groupedData.size();
    }
}