
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Summary {

    public static void createSummaryObj(Date userDateObj) throws  Exception {
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
        LocalDate l2 = LocalDate.of(2021, 5, 24);
        LocalDate l3 = LocalDate.of(2021, 10, 26);

        LocalDate l4 = LocalDate.of(2021, 4, 3);
        LocalDate l5 = LocalDate.of(2021, 5, 3);
        LocalDate l6 = LocalDate.of(2021, 6, 27 );

        LocalDate l7 = LocalDate.of(2021, 5, 27);
        LocalDate l8 = LocalDate.of(2021, 5, 30);
        LocalDate l9 = LocalDate.of(2021, 6, 2);


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

        ArrayList<Data> dataArr = new ArrayList<>(){{add(d4); add(d5); add(d6);}};
        DataGroup dg2 = new DataGroup(dataArr);

        DataGroup dg3 = new DataGroup();
        dg3.addData(d7);
        dg3.addData(d8);
        dg3.addData(d9);

        ArrayList<DataGroup> groupForTesting = new ArrayList<>(){{add(dg1); add(dg2); add(dg3);}};

        String menu = "\n************************************************************\n " +
                        "Available metric\n" +
                            "\t[1] Positive case\n" +
                            "\t[2] Deaths\n" +
                            "\t[3] People vaccinated\n" +
                        "************************************************************\n" +
                        "Please choose your metric(1/2/3)>> ";
        System.out.printf(menu);
        int metric = Integer.parseInt(sc.nextLine());
        System.out.println();
        ArrayList<DataGroup> analyzedData = getData(userDateObj, metric, groupForTesting);

        // FIXME: 2021-08-10 Lee Gain
        for(DataGroup dg : analyzedData){
            dg.display();
            System.out.println("=================================");
        }
        // part 3


    }
    // part 1


    // part 2
    private static ArrayList<DataGroup> getData(Date dtObj, int metric, ArrayList<DataGroup> dgArr) throws Exception{
        ArrayList<DataGroup> dataGroups = new ArrayList<>();
        String geographicArea = dtObj.getGeographicArea();
        ArrayList<String[]> dbOfGeographicArea = getDatabase(geographicArea);

        for(DataGroup dateList: dgArr){
            DataGroup dg = getDataByDateGroup(dbOfGeographicArea , dateList, metric);
            dataGroups.add(dg);
        }

        return dataGroups;
    }

    private static ArrayList<String[]> getDatabase(String geographicArea) throws Exception{
        // it returns database for particular country or continent
        ArrayList<String[]> db = new ArrayList<>();
        FileReader csv = new FileReader("src\\..\\lib\\covid-data.csv");
        BufferedReader fileContainer = new BufferedReader(csv);
        String line = fileContainer.readLine();

        while(line != null){
            String[] tempRow = line.split(",");
            String[] row = new String[8];
            String continentList = "Africa-Asia-Europe-North America-Oceania-South America";
            boolean isContinent = geographicArea.contains(continentList);

            if(tempRow[2].equalsIgnoreCase(geographicArea)){
                for(int i = 0; i < tempRow.length; i++){
                    row[i] = tempRow[i];
                }
                db.add(row);
            }
            line = fileContainer.readLine();
        }
        return db;
    }

    private static DataGroup getDataByDateGroup(ArrayList<String[]> dbOfGeographicArea, DataGroup dataGroup, int metric){
        // it returns database(particular country - date) for each group
        ArrayList<Data> dataArr = dataGroup.getGroupedData();
        int dataArrLeng = dataArr.toArray().length;

        for(int i = 0; i < dataArrLeng; i++){
            for(String[] row : dbOfGeographicArea){
                LocalDate date = dataArr.get(i).getDate();
                String[] dateInCsv = row[3].split("/");
                int year = Integer.parseInt(dateInCsv[2]);
                int month = Integer.parseInt(dateInCsv[0]);
                int day = Integer.parseInt(dateInCsv[1]);
                LocalDate tempDate = LocalDate.of(year, month, day);

                if(tempDate.isEqual(date)){
                    Data data = dataArr.get(i);

                    switch (metric){
                        //positive
                        case 1:
                            if(row[4] == null || row[4].isEmpty()){
                                continue;
                            }
                            data.setPositiveCases(Integer.parseInt(row[4]));
                            break;
                        // death
                        case 2:
                            if(row[5] == null || row[5].isEmpty()){
                                continue;
                            }
                            data.setNewDeath(Integer.parseInt(row[5]));
                            break;
                        //vaccinated
                        case 3:
                            if(row[6] == null || row[6].isEmpty()){
                                continue;
                            }
                            data.setPeopleVaccinated(Integer.parseInt(row[6]));
                            break;
                        default:
                    }
                }
            }
        }
        return  dataGroup;
    }

    //part 3
}

class Data {
    private LocalDate date;
    private int positiveCases;
    private int newDeath;
    private int peopleVaccinated;

    //Constructor
    Data(LocalDate date){ this.date = date;
    }

    //Getter and Setter
    void setPositiveCases(int positiveCases){
        this.positiveCases = positiveCases;
    }

    void setNewDeath(int newDeath){
        this.newDeath = newDeath;
    }

    void setPeopleVaccinated(int peopleVaccinated){
        this.peopleVaccinated = peopleVaccinated;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNewDeath() {
        return newDeath;
    }

    public int getPositiveCases() {
        return positiveCases;
    }

    public int getPeopleVaccinated() {
        return peopleVaccinated;
    }

    //Method
    public void displayData(){
        System.out.printf("Date: %s\nPositive Case: %d\nNew Death: %d\nPeople Vaccinated: %d\n",
                getDate(), getPositiveCases(), getNewDeath(), getPeopleVaccinated());
    }
}

class DataGroup {
    private ArrayList<Data> groupedData = new ArrayList<>();

    // Constructor
    DataGroup(){}
    DataGroup(ArrayList<Data> groupedData){
        this.groupedData = groupedData;
    }

    //Getter and Setter
    public ArrayList<Data> getGroupedData() {
        return groupedData;
    }

    public void addData(Data dt){
        groupedData.add(dt);
    }

    public void display(){
        for(Data d : groupedData){
            d.displayData();
            System.out.println();
        }
    }
}