package processeddata;

import java.util.ArrayList;

/**
 * The DataGroup class was created for grouping Data instances.
 * It allows users to add Data an instance into DataGroup
 *, get Data instance in the DataGroup depends on the index
 *, get the size of the DataGroup instance and display the detail of the DataGroup instance.
 */

public class DataGroup {
    private ArrayList<Data> groupedData = new ArrayList<>();

    // Getter and Setter
    public ArrayList<Data> getGroupedData() {
        // This getter method returns a getGroupedData attribute.

        return groupedData;
    }

    // Method
    public void addData(Data dt) {
        // This method adds Data instance into groupedData.

        groupedData.add(dt);
    }

    public Data getData(int idx) {
        // This method accepts integer "idx"
        // and returns a Data instance of the particular index.

        return groupedData.get(idx);
    }

    public int getSize() {
        //  This method returns the size of groupedData as an integer.

        return groupedData.size();
    }
}