package main;

import processeddata.DataGroup;
import util.UserInputManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class Summary {
    private LocalDate[] timeRange;
    private int value;

    // Constructor
    Summary(LocalDate[] timeRange, int value) {
        this.timeRange = timeRange;
        this.value = value;
    }

    // Getter and Setter
    public LocalDate[] getTimeRange() {
        return timeRange;
    }

    public int getValue() {
        return value;
    }

    // Method
    public String toString() { return String.format("Time range: %s\nValue: %d\n",timeRangeToString(), getValue());
    }

    public static ArrayList<Summary> createSummaryObj(Data userDataObj) throws Exception {
        // part 1
         String groupingConditionMenu = """
                                        [STEP 2]
                                        ************************************************************
                                        Grouping condition
                                        \t[1] No grouping
                                        \t[2] Number of groups
                                        \t[3] Number of days
                                        ************************************************************
                                        Please choose your grouping condition(1/2/3)>>\s""";
        UserInputManager.displayMenu(groupingConditionMenu);
        int groupingCondition = UserInputManager.getIntUserInput();

        LocalDate[] userTimeRange = userDataObj.getTimeRange();
        DataGroup baseDayGroup = Data.setDateList(userTimeRange);
        ArrayList<DataGroup> groupedDayList = new ArrayList<>();

        switch (groupingCondition) {
            case 1:
                groupedDayList = Data.noGrouping(baseDayGroup);
                break;
            case 2:
                UserInputManager.displayMenu("Please enter the number of groups you want to create. (Integer value)>> ");
                int numOfGroups = UserInputManager.getIntUserInput();

                groupedDayList = Data.groupByGroupNum(baseDayGroup, numOfGroups);
                break;
            case 3:
                UserInputManager.displayMenu("Please enter the number of days in a group. (Integer value)>> ");
                int numOfDays = UserInputManager.getIntUserInput();

                groupedDayList = Data.groupByDayNum(baseDayGroup, numOfDays);
                break;
            default:
                // write some code after studying exceptions on the lecture.
        }

        ArrayList<DataGroup> analyzedData = processeddata.Data.getData(userDataObj, groupedDayList);

        // FIXME: 2021-08-10 Lee Gain
        System.out.println("Analyzed result+++++++++++++++++++++++++++++++++++++++++++");
        for (DataGroup dg : analyzedData) {
            dg.displayDG();
            System.out.println("=================================");
        }


        // part 3
        String metricMenu = """
                            ************************************************************
                            Available metric
                            \t[1] Positive cases
                            \t[2] Deaths
                            \t[3] People vaccinated
                            ************************************************************
                            Please choose your metric(1/2/3)>>\s""";
        UserInputManager.displayMenu(metricMenu);
        int metric = UserInputManager.getIntUserInput();

        String resultTypesMenu = """
                                ************************************************************
                                Available result types
                                \t[1] New Total
                                \t[2] Up To
                                ************************************************************
                                Please choose your result types(1/2)>>\s""";
        UserInputManager.displayMenu(resultTypesMenu);
        int resultType = UserInputManager.getIntUserInput();

        ArrayList<Summary> summaryList = new ArrayList<>();
        for(DataGroup dg : analyzedData) {
            ArrayList<processeddata.Data> dtArr = dg.getGroupedData();
            LocalDate firstDate = dtArr.get(0).getDate();
            LocalDate lastDate = dtArr.get(dtArr.toArray().length - 1).getDate();
            LocalDate[] timeRange = new LocalDate[]{firstDate, lastDate};
            int value = 0;

            switch (resultType) {
                case 1:
                    value = getNewTotal(dtArr, metric);
                    break;
                case 2:
                    value = getUpTo(dtArr, metric);
                    break;
                default:
                    //error control
                    // FIXME: 2021-08-14
            }

            Summary s = new Summary(timeRange, value);
            summaryList.add(s);
        }

        // FIXME: 2021-08-14
        System.out.println("Final result+++++++++++++++++++++++++++++++++++++++++++");

        return summaryList;
    }

    public String timeRangeToString(){
        return timeRange[0] + "," + timeRange[1];
    }

    public static void showSummaryList(ArrayList<Summary> summaryList) {
        for(Summary s: summaryList) {
            System.out.println(s);
        }
    }

    private static int getNewTotal(ArrayList<processeddata.Data> db, int metric){
        int value = 0;
        for(processeddata.Data dt : db) {
            switch (metric) {
                case 1:
                    value += dt.getNewCases();
                    break;
                case 2:
                    value += dt.getNewDeaths();
                    break;
                case 3:
                    value += dt.getNewPeopleVaccinated();
                    break;
                default:
                    //err
                    // FIXME: 2021-08-14
            }
        }
        return value;
    }

    private static int getUpTo(ArrayList<processeddata.Data> db, int metric){
        int value = 0;
        int dbLength = db.toArray().length;
        processeddata.Data lastDateData = db.get(dbLength - 1);
        switch (metric) {
            case 1:
                value = lastDateData.getTotalCases();
                break;
            case 2:
                value = lastDateData.getTotalDeaths();
                break;
            case 3:
                value = lastDateData.getPeopleVaccinated();
                break;
            default:
                //err
                // FIXME: 2021-08-14
        }

        return value;
    }


}