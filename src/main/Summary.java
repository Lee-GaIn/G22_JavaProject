package main;

import data.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Summary {

    public static void createSummaryObj(Date userDateObj) throws Exception {
        Scanner sc = new Scanner(System.in);
        // part 1

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


        // FIXME: for Anh Minh Chu
        // and returns analyzedData as ArrayList<DataGroup>.
        // Each data Group has a group.
        // Each group has several data
        // Each data has Localdate and the number of (positive case OR new death OR people vaccinated).
        // If user choose only searching "NEW" positive cases, both new death and people vaccinated fields is 0 except for "NEW" positive cases fields
        // If user ~~                    new deaths, both positive cases and people vaccinated fields is 0 except for new deaths fields
        // If user ~~                    people vaccinated, both "NEW" positive case and new deaths fields is 0 except for people vaccinated fields
        // Please read Data class and DataGroup class that I made below

        // FIXME: 2021-08-09 Lee Gain
        LocalDate l1 = LocalDate.of(2021, 5, 20);
        LocalDate l2 = LocalDate.of(2021, 5, 21);
        LocalDate l3 = LocalDate.of(2021, 5, 22);

        LocalDate l4 = LocalDate.of(2021, 5, 23);
        LocalDate l5 = LocalDate.of(2021, 5, 24);
        LocalDate l6 = LocalDate.of(2021, 5, 25);

        LocalDate l7 = LocalDate.of(2021, 5, 26);
        LocalDate l8 = LocalDate.of(2021, 5, 27);
        LocalDate l9 = LocalDate.of(2021, 5, 28);


        Data d1 = new Data(l1);
        Data d2 = new Data(l2);
        Data d3 = new Data(l3);

        Data d4 = new Data(l4);
        Data d5 = new Data(l5);
        Data d6 = new Data(l6);

        Data d7 = new Data(l7);
        Data d8 = new Data(l8);
        Data d9 = new Data(l9);

        DataGroup dg1 = new DataGroup();
        dg1.addData(d1);
        dg1.addData(d2);
        dg1.addData(d3);

        ArrayList<Data> dataArr = new ArrayList<>() {{
            add(d4);
            add(d5);
            add(d6);
        }};
        DataGroup dg2 = new DataGroup(dataArr);

        DataGroup dg3 = new DataGroup();
        dg3.addData(d7);
        dg3.addData(d8);
        dg3.addData(d9);

        ArrayList<DataGroup> groupForTesting = new ArrayList<>() {{
            add(dg1);
            add(dg2);
            add(dg3);
        }};

        ArrayList<DataGroup> analyzedData = getData(userDateObj, groupForTesting);

        // FIXME: 2021-08-10 Lee Gain
        for (DataGroup dg : analyzedData) {
            dg.displayDG();
            System.out.println("=================================");
        }


        // part 3
        String MetricMenu = "\n************************************************************\n " +
                "Available metric\n" +
                "\t[1] Positive case\n" +
                "\t[2] Deaths\n" +
                "\t[3] People vaccinated\n" +
                "************************************************************\n" +
                "Please choose your metric(1/2/3)>> ";
        System.out.printf(MetricMenu);
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

        switch (resultType){
            case 1:
                getNewTotal(analyzedData, metric);
                break;
            case 2:
                getUpTo(analyzedData, metric);
                break;
            default:
                //error control
                // FIXME: 2021-08-14
        }


    }
    // part 1


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
    private static void getNewTotal(ArrayList<DataGroup> db, int metric){
        switch (metric){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                //err
                // FIXME: 2021-08-14
        }
    }

    private static void getUpTo(ArrayList<DataGroup> db, int metric){
        switch (metric){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                //err
                // FIXME: 2021-08-14
        }
    }

}