package main;

import data.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Summary {
    private String[] timeRange = new String[2];
    private int value;

    // Constructor
    Summary(){}

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
        Scanner sc = new Scanner(System.in);
        // part 1
         String groupingConditionMenu = "[STEP 2]" +
                "\n************************************************************\n " +
                "Grouping condition\n" +
                "\t[1] No grouping\n" +
                "\t[2] Number of groups\n" +
                "\t[3] Number of days\n" +
                "************************************************************\n" +
                "Please choose your grouping condition(1/2/3)>> ";
        System.out.printf(groupingConditionMenu);
        int groupingCondition = Integer.parseInt(sc.nextLine());
        System.out.println();

        ArrayList<LocalDate> userTimeRange = userDateObj.getTimeRange();
        // It contains startdate[0] and enddate[1]

        DataGroup baseDayGroup = methodOne(userTimeRange);
        // it accepts userTimeRange
        // and returns DataGroup
        // {startdate, startdate+1, ,startdate+2, ..., enddate}

        ArrayList<DataGroup> groupedDayList = new ArrayList<>();
        // final result

        switch (groupingCondition){
            case 1:
                groupedDayList = methodForNoGrouping(baseDayGroup);
                break;
            case 2:
                System.out.printf("Please enter the number of groups you want to create. (Integer value)>> ");
                int numOfGroups = Integer.parseInt(sc.nextLine());
                System.out.println();

                groupedDayList = methodForNumOfGroups(baseDayGroup, numOfGroups);
                break;
            case 3:
                System.out.printf("Please enter the number of days in a group. (Integer value)>> ");
                int numOfDays = Integer.parseInt(sc.nextLine());
                System.out.println();

                groupedDayList = methodForNumOfDays(baseDayGroup, numOfDays);
                break;
            default:
                // write some code after studying exceptions on the lecture.
        }


        // part 2
        // FIXME: for Khanh Linh and Ngoc Tuan
        // My part accepts grouped ArrayList<DataGroup>.
        // 1. After you finish grouping a number of Localdate instances, please make a new Data instance (I made)
        //    which accept Localdate as one of the fields (check constructor for Data class).
        // 2. After that, make new DataGroup instance(I made) and put Data instances by using the addData method or constructor
        //    if Data instances are in the same group.
        // 3. For more detail, please read the Data class and DataGroup class that I made below

        // (ex) l1 and l2 are in the same group (dg1), l3 and l4 are in the same group (dg2)
//        LocalDate l1 = LocalDate.of(2021, 5, 20);
//        LocalDate l2 = LocalDate.of(2021, 5, 24);
//        LocalDate l3 = LocalDate.of(2021, 5, 26);
//        LocalDate l4 = LocalDate.of(2021, 4, 3);
//
//        // 1.
//        Data d1 = new Data(l1);
//        Data d2 = new Data(l2);
//        Data d3 = new Data(l3);
//        Data d4 = new Data(l4);
//
//        // 2.
//        // You can use either of two ways.
//        // The first way
//        DataGroup dg1 = new DataGroup();
//        dg1.addData(d1);
//        dg1.addData(d2);
//
//        // The second way
//        ArrayList<Data> dataArr = new ArrayList<Data>(){{add(d3); add(d4);}};
//        DataGroup dg2 = new DataGroup(dataArr);
//
//        // 3.
//        // Please return this so that my code can be implemented.
//        ArrayList<DataGroup> result = new ArrayList<DataGroup>(){{add(dg1); add(dg2);}};


        // FIXME: 2021-08-09 Lee Gain
//        LocalDate l1 = LocalDate.of(2021, 5, 18);
//        LocalDate l2 = LocalDate.of(2021, 5, 19);
//        LocalDate l3 = LocalDate.of(2021, 5, 20);
//
//        LocalDate l4 = LocalDate.of(2021, 5, 21);
//        LocalDate l5 = LocalDate.of(2021, 5, 22);
//        LocalDate l6 = LocalDate.of(2021, 5, 23);
//
//        LocalDate l7 = LocalDate.of(2021, 5, 24);
//        LocalDate l8 = LocalDate.of(2021, 5, 25);
//        LocalDate l9 = LocalDate.of(2021, 5, 26);
//
//
//        Data d1 = new Data(l1);
//        Data d2 = new Data(l2);
//        Data d3 = new Data(l3);
//
//        Data d4 = new Data(l4);
//        Data d5 = new Data(l5);
//        Data d6 = new Data(l6);
//
//        Data d7 = new Data(l7);
//        Data d8 = new Data(l8);
//        Data d9 = new Data(l9);
//
//        DataGroup dg1 = new DataGroup();
//        dg1.addData(d1);
//        dg1.addData(d2);
//        dg1.addData(d3);
//
//        ArrayList<Data> dataArr = new ArrayList<>() {{
//            add(d4);
//            add(d5);
//            add(d6);
//        }};
//        DataGroup dg2 = new DataGroup(dataArr);
//
//        DataGroup dg3 = new DataGroup();
//        dg3.addData(d7);
//        dg3.addData(d8);
//        dg3.addData(d9);
//
//        ArrayList<DataGroup> groupForTesting = new ArrayList<>() {{
//            add(dg1);
//            add(dg2);
//            add(dg3);
//        }};

        ArrayList<DataGroup> analyzedData = getData(userDateObj, groupedDayList);

        // FIXME: 2021-08-10 Lee Gain
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
        System.out.printf(metricMenu);
        int metric = Integer.parseInt(sc.nextLine());
        System.out.println();

        String resultTypesMenu = "\n************************************************************\n " +
                "Available result types\n" +
                "\t[1] New Total\n" +
                "\t[2] Up To\n" +
                "************************************************************\n" +
                "Please choose your result types(1/2)>> ";
        System.out.printf(resultTypesMenu);
        int resultType = Integer.parseInt(sc.nextLine());
        System.out.println();

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
            Summary s1 = new Summary(timeRange, value);
            summaryList.add(s1);
        }

        // FIXME: 2021-08-14
        for(Summary s: summaryList){
            s.displaySummary();
        }

        return summaryList;
    }

    private String timeRangeToString(){
        return timeRange[0] + "~" + timeRange[1];
    }

    public void displaySummary(){ System.out.printf("Time range: %s\nValue: %d\n",timeRangeToString(), getValue());
    }

    // part 1
    private static DataGroup methodOne(ArrayList<LocalDate> userTimeRange) {
        DataGroup dg = new DataGroup();
        LocalDate start = userTimeRange.get(0);
        dg.addData(new Data(start));
        LocalDate end = userTimeRange.get(1);

        int count = 1;
        LocalDate nextDate = start.plusDays(count);
        while (nextDate.isBefore(end))
        {
            count++;
            dg.addData(new Data(nextDate));
            nextDate = start.plusDays(count);
        }
        dg.addData(new Data(end));
        return dg;
    }

    private static ArrayList<DataGroup> methodForNoGrouping(DataGroup userTimeRange) {
        ArrayList<DataGroup> noGroup = new ArrayList<>();
        for (int i = 0; i< userTimeRange.getSize(); i++)
        {
            DataGroup dg = new DataGroup();
            dg.addData(userTimeRange.getData(i));
            noGroup.add(dg);
        }
        return noGroup;
    }

    private static ArrayList<DataGroup> methodForNumOfGroups(DataGroup userTimeRange, int numOfGroups) {
        ArrayList<DataGroup> groups = new ArrayList<>();
        int numGroups = numOfGroups;
        int size = userTimeRange.getSize();
        int count = 0;
        for (int i = 0; i< numOfGroups; i++)
        {
            int numElements = size/numGroups;
            if (size%numGroups !=0)
            {
                numElements++;
            }
            DataGroup dg = new DataGroup();
            for (int j = 0; j< numElements; j++)
            {
                dg.addData(userTimeRange.getData(count));
                count++;
            }
            groups.add(dg);
            numGroups--;
            size-=numElements;
        }
        return groups;
    }

    private static ArrayList<DataGroup> methodForNumOfDays(DataGroup userTimeRange, int numOfDays) {
        ArrayList<DataGroup> groups = new ArrayList<DataGroup>();
        int i = 0;
        while (i < userTimeRange.getSize())
        {
            DataGroup dg = new DataGroup();
            for (int j = 0; j< numOfDays; j++)
            {
                if (i < userTimeRange.getSize())
                {
                    dg.addData(userTimeRange.getData(i));
                    i++;
                }
            }
            groups.add(dg);
        }
        return groups;
    }

    // part 2
    private static ArrayList<DataGroup> getData(Date userDate, ArrayList<DataGroup> dgArr) throws Exception {
        ArrayList<DataGroup> updatedDataGroups = new ArrayList<>();
        String geographicArea = userDate.getGeographicArea();
        ArrayList<String[]> dbOfGeographicArea = getDatabase(geographicArea);

        for (DataGroup dg : dgArr) {
            DataGroup dgWithInfo = getDataByDateGroup(dbOfGeographicArea, dg);
            updatedDataGroups.add(dgWithInfo);
        }

        return updatedDataGroups;
    }

    private static ArrayList<String[]> getDatabase(String geographicArea) throws Exception {
        // it returns database for particular country or continent
        ArrayList<String[]> db = new ArrayList<>();
        FileReader csv = new FileReader("src\\..\\lib\\covid-data.csv");
        BufferedReader fileContainer = new BufferedReader(csv);
        String line = fileContainer.readLine();

        while (line != null) {
            String[] tempRow = line.split(",");
            String[] row = new String[]{"","","","","0","0","0","0"};

            if (tempRow[2].equalsIgnoreCase(geographicArea)) {
                for (int i = 0; i < tempRow.length; i++) {
                    if(tempRow[i] != null && !(tempRow[i].isEmpty())){
                        row[i] = tempRow[i];
                    }
                }
                db.add(row);
            }
            line = fileContainer.readLine();
        }
        return db;
    }

    private static DataGroup getDataByDateGroup(ArrayList<String[]> dbOfGeographicArea, DataGroup dg) {
        // it returns database(particular country - date) for each group
        ArrayList<Data> dataArr = dg.getGroupedData();
        int dataArrLength = dataArr.toArray().length;
        int dbArrLength = dbOfGeographicArea.toArray().length;

        for (int i = 0; i < dataArrLength; i++) {
            for (int j = 0; j < dbArrLength; j++) {
                String[] curRow = dbOfGeographicArea.get(j);
                LocalDate tempDate = Date.strToLocalDate(curRow[3]);
                LocalDate userDate = dataArr.get(i).getDate();

                if (tempDate.isEqual(userDate)) {
                    int positiveCases = Integer.parseInt(curRow[4]);
                    int newDeaths = Integer.parseInt(curRow[5]);
                    int peopleVaccinated = Integer.parseInt(curRow[6]);
                    int newPeopleVaccinated = Integer.parseInt(curRow[6]);
                    if(j != 0){
                        String[] prevRow = dbOfGeographicArea.get(j - 1);
                        int prevPV = Integer.parseInt(prevRow[6]);
                        newPeopleVaccinated = newPeopleVaccinated - prevPV;

                        if(peopleVaccinated < 0){
                            peopleVaccinated = prevPV;
                        }
                    }

                    Data data = dataArr.get(i);
                    data.setPositiveCases(positiveCases);
                    data.setNewDeaths(newDeaths);
                    data.setPeopleVaccinated(peopleVaccinated);
                    data.setNewPeopleVaccinated(newPeopleVaccinated);
                }
            }
        }

        return dg;
    }

    //part 3
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
        //        "\t[1] new cases\n" +
//                "\t[2] Deaths\n" +
//                "\t[3] People vaccinated\n" +
        int value = 0;
        int dbLength = db.toArray().length;
        for(int i = 0; i < dbLength; i++) {
            switch (metric) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    Data lastDateData = db.get(dbLength - 1);
                    value = lastDateData.getPeopleVaccinated();
                    break;
                default:
                    //err
                    // FIXME: 2021-08-14
            }
        }
        return value;
    }
}