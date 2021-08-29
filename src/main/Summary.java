package main;

import processeddata.DataGroup;
import processeddata.DataTotal;
import processeddata.GroupDates;
import util.DisplayManager;
import util.ExceptionManager;
import util.UserInputManager;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Summary class was created for ushering the user to create a new Summary instance.
 * The user can choose a condition for grouping, metric and result types
 * It also lets the user display the detail of the Summary instance.
 */

public class Summary {
    private LocalDate[] timeRange;
    private int value;

    //  Constructor
    private Summary(LocalDate[] timeRange, int value) {
        // This constructor accepts LocalDate list "timeRange" and integer "value" as parameters
        // and construct a new instance of a Summary.

        this.timeRange = timeRange;
        this.value = value;
    }

    //  Getter and Setter
    public LocalDate[] getTimeRange() {
        // This getter method returns timeRange.

        return timeRange;
    }

    public int getValue() {
        // This getter method returns a value.

        return value;
    }

    //  Method
    @Override
    public String toString() {
        // This method returns the detail of an instance of a Summary as a string.

        return String.format("Time range: %s\nValue: %d\n", timeRangeToString(), getValue());
    }

    public static ArrayList<Summary> createSummaryObj(Data userDataObj) throws Exception {
        // This method ushers users to create an array list of Summary instances.
        // It returns an array list of Summary instances.
        // It throws an exception if the user input is invalid.

        // Choose 'grouping condition'.

         String groupingConditionMenu = """
                                        [STEP 2]
                                        ************************************************************
                                        Grouping condition
                                        \t[1] No grouping
                                        \t[2] Number of groups
                                        \t[3] Number of days
                                        ************************************************************
                                        Please choose your grouping condition(1/2/3)>>\s""";
        DisplayManager.displayMenu(groupingConditionMenu);
        int groupingCondition = UserInputManager.getIntUserInput();

        LocalDate[] userTimeRange = userDataObj.getTimeRange();
        DataGroup baseDayGroup = GroupDates.setDateList(userTimeRange);
        ArrayList<DataGroup> groupedDayList = new ArrayList<>();

        switch (groupingCondition) {
            case 1 -> groupedDayList = GroupDates.noGrouping(baseDayGroup);
            case 2 -> {
                DisplayManager.displayMenu("Please enter the number of groups you want to create. (Integer value)>> ");
                int numOfGroups = UserInputManager.getIntUserInput();
                groupedDayList = GroupDates.groupByGroupNum(baseDayGroup, numOfGroups);
            }
            case 3 -> {
                DisplayManager.displayMenu("Please enter the number of days in a group. (Integer value)>> ");
                int numOfDays = UserInputManager.getIntUserInput();
                groupedDayList = GroupDates.groupByDayNum(baseDayGroup, numOfDays);
            }
            default -> ExceptionManager.throwInvalidOption();
        }

        ArrayList<DataGroup> analyzedData = processeddata.Database.getData(userDataObj, groupedDayList);

        // FIXME: 2021-08-10 Lee Gain
        System.out.println("Analyzed result+++++++++++++++++++++++++++++++++++++++++++");
        for (DataGroup dg : analyzedData) {
            dg.displayDG();
            System.out.println("=================================");
        }

        // Choose metric.

        String metricMenu = """
                            ************************************************************
                            Available metric
                            \t[1] Positive cases
                            \t[2] Deaths
                            \t[3] People vaccinated
                            ************************************************************
                            Please choose your metric(1/2/3)>>\s""";
        DisplayManager.displayMenu(metricMenu);
        int metric = UserInputManager.getIntUserInput();

        // Choose result type.

        String resultTypesMenu = """
                                ************************************************************
                                Available result types
                                \t[1] New Total
                                \t[2] Up To
                                ************************************************************
                                Please choose your result types(1/2)>>\s""";
        DisplayManager.displayMenu(resultTypesMenu);
        int resultType = UserInputManager.getIntUserInput();

        ArrayList<Summary> summaryList = new ArrayList<>();
        for(DataGroup dg : analyzedData) {
            ArrayList<processeddata.Data> dtArr = dg.getGroupedData();
            LocalDate firstDate = dtArr.get(0).getDate();
            LocalDate lastDate = dtArr.get(dtArr.toArray().length - 1).getDate();
            LocalDate[] timeRange = new LocalDate[]{firstDate, lastDate};
            int value = 0;

            switch (resultType) {
                case 1 -> value = DataTotal.getNewTotal(dtArr, metric);
                case 2 -> value = DataTotal.getUpTo(dtArr, metric);
                default -> ExceptionManager.throwInvalidOption();
            }

            Summary s = new Summary(timeRange, value);
            summaryList.add(s);
        }

        //  FIXME: 2021-08-14
        System.out.println("Final result+++++++++++++++++++++++++++++++++++++++++++");

        return summaryList;
    }

    private String timeRangeToString() {
        // This method returns the detail of the time range as a string.

        return timeRange[0] + "," + timeRange[1];
    }

    public static void displaySummaryList(ArrayList<Summary> summaryList) {
        // This method shows the array list of Summary instances.

        for(Summary s : summaryList) {
            System.out.println(s);
        }
    }

}