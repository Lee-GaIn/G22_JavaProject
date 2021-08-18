package main;

import data.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Summary {
    private String[] timeRange;
    private int value;

    // Constructor
    Summary(String[] timeRange, int value){
        this.timeRange = timeRange;
        this.value = value;
    }

    // Getter and Setter
    public String[] getTimeRange() {
        return timeRange;
    }

    public int getValue() {
        return value;
    }

    // Method
    public static ArrayList<Summary> createSummaryObj(Date userDateObj) throws Exception {
        // part 1
         String groupingConditionMenu = "[STEP 2]" +
                "\n************************************************************\n " +
                "Grouping condition\n" +
                "\t[1] No grouping\n" +
                "\t[2] Number of groups\n" +
                "\t[3] Number of days\n" +
                "************************************************************\n" +
                "Please choose your grouping condition(1/2/3)>> ";
        UserInterface.displayMenu(groupingConditionMenu);
        int groupingCondition = UserInterface.getIntUserInput();

        LocalDate[] userTimeRange = userDateObj.getTimeRange();
        DataGroup baseDayGroup = Date.ListOfDates(userTimeRange);
        ArrayList<DataGroup> groupedDayList = new ArrayList<>();

        switch (groupingCondition){
            case 1:
                groupedDayList = Date.noGrouping(baseDayGroup);
                break;
            case 2:
                UserInterface.displayMenu("Please enter the number of groups you want to create. (Integer value)>> ");
                int numOfGroups = UserInterface.getIntUserInput();

                groupedDayList = Date.groupByGroupNum(baseDayGroup, numOfGroups);
                break;
            case 3:
                UserInterface.displayMenu("Please enter the number of days in a group. (Integer value)>> ");
                int numOfDays = UserInterface.getIntUserInput();

                groupedDayList = Date.groupByDayNum(baseDayGroup, numOfDays);
                break;
            default:
                // write some code after studying exceptions on the lecture.
        }

        ArrayList<DataGroup> analyzedData = Data.getData(userDateObj, groupedDayList);

        // FIXME: 2021-08-10 Lee Gain
        System.out.println("Analyzed result+++++++++++++++++++++++++++++++++++++++++++");
        for (DataGroup dg : analyzedData) {
            dg.displayDG();
            System.out.println("=================================");
        }


        // part 3
        String metricMenu = "\n************************************************************\n " +
                "Available metric\n" +
                "\t[1] Positive cases\n" +
                "\t[2] Deaths\n" +
                "\t[3] People vaccinated\n" +
                "************************************************************\n" +
                "Please choose your metric(1/2/3)>> ";
        UserInterface.displayMenu(metricMenu);
        int metric = UserInterface.getIntUserInput();

        String resultTypesMenu = "\n************************************************************\n " +
                "Available result types\n" +
                "\t[1] New Total\n" +
                "\t[2] Up To\n" +
                "************************************************************\n" +
                "Please choose your result types(1/2)>> ";
        UserInterface.displayMenu(resultTypesMenu);
        int resultType = UserInterface.getIntUserInput();

        ArrayList<Summary> summaryList = new ArrayList<>();
        for(DataGroup dg : analyzedData) {
            ArrayList<Data> dtArr = dg.getGroupedData();
            String firstDate = dtArr.get(0).dateToString();
            String lastDate = dtArr.get(dtArr.toArray().length - 1).dateToString();
            String[] timeRange = new String[]{firstDate, lastDate};
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
        for(Summary s: summaryList){
            System.out.println(s);
        }

        return summaryList;
    }

    private String timeRangeToString(){
        return timeRange[0] + "~" + timeRange[1];
    }

    public String toString(){ return String.format("Time range: %s\nValue: %d\n",timeRangeToString(), getValue());
    }

    private static int getNewTotal(ArrayList<Data> db, int metric){
        int value = 0;
        for(Data dt : db){
            switch (metric){
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

    private static int getUpTo(ArrayList<Data> db, int metric){
        int value = 0;
        int dbLength = db.toArray().length;
        Data lastDateData = db.get(dbLength - 1);
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