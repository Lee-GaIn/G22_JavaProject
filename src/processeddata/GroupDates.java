package processeddata;

import util.ExceptionManager;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The GroupDates class was created to get grouped days list.
 * There are three metrics to get grouped dates list.
 * The first metric is grouping days individually.
 * The second metric is grouping days by the number of a group.
 * The last metric is grouping days by the number of days for a group
 * so that every group has the same number of days.
 */

public class GroupDates {

    // Methods
    public static DataGroup setDateList(LocalDate[] userTimeRange) {
        // This method receives LocalDate "userTimeRange" as an parameter
        // and returns DataGroup.

        DataGroup dg = new DataGroup();
        LocalDate start = userTimeRange[0];
        dg.addData(new processeddata.Data(start));
        LocalDate end = userTimeRange[1];
        int count = 1;
        LocalDate nextDate = start.plusDays(count);
        while (nextDate.isBefore(end)) {
            count++;
            dg.addData(new processeddata.Data(nextDate));
            nextDate = start.plusDays(count);
        }
        dg.addData(new processeddata.Data(end));
        return dg;
    }

    public static ArrayList<DataGroup> noGrouping(DataGroup userTimeRange) {
        // This method receives DataGroup "userTimeRange" as an parameter
        // and returns ArrayList of DataGroup
        // where each group only contains 1 date.

        ArrayList<DataGroup> noGroup = new ArrayList<>();
        int size = userTimeRange.getSize();
        for (int i = 0; i < size; i++) {
            DataGroup dg = new DataGroup();
            dg.addData(userTimeRange.getData(i));
            noGroup.add(dg);
        }
        return noGroup;
    }

    public static ArrayList<DataGroup> groupByGroupNum(DataGroup userTimeRange, int numOfGroups) {
        // This method receives DataGroup "userTimeRange" as an parameter
        // and returns ArrayList of DataGroup.
        // The number of groups is decided by the user input.

        ArrayList<DataGroup> groups = new ArrayList<>();
        int numGroups = numOfGroups;
        int size = userTimeRange.getSize();
        int count = 0;
        ExceptionManager.checkNumOfGroups(size, numGroups);

        for (int i = 0; i < numOfGroups; i++) {
            int numElements = size / numGroups;
            if (size % numGroups != 0) {
                numElements++;
            }
            DataGroup dg = new DataGroup();
            for (int j = 0; j < numElements; j++) {
                dg.addData(userTimeRange.getData(count));
                count++;
            }
            groups.add(dg);
            numGroups--;
            size -= numElements;
        }

        return groups;
    }

    public static ArrayList<DataGroup> groupByDayNum(DataGroup userTimeRange, int numOfDays) {
        // This method receives DataGroup "userTimeRange" as an parameter
        // and returns ArrayList of DataGroup.
        // The number of dates in a group is decided by the user input
        // If the divided groups do not have the same number of dates, it throws exception.

        ArrayList<DataGroup> groups = new ArrayList<>();
        int i = 0;
        int size = userTimeRange.getSize();
        ExceptionManager.checkNumOfGroups(size, numOfDays);
        ExceptionManager.checkNumOfDays(size, numOfDays);
        while (i < size) {
            DataGroup dg = new DataGroup();
            for (int j = 0; j < numOfDays; j++) {
                if (i < size) {
                    dg.addData(userTimeRange.getData(i));
                    i++;
                }
            }
            groups.add(dg);
        }
        return groups;
    }
}
