
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
        // My part accepts grouped date array list as ArrayList<ArrayList<LocalDate>>.
        // (ex)ArrayList{ArrayList{Localdate1, Localdate2, Localdate3}, ArrayList{Localdate4, Localdate5, Localdate6}, ...}
        // like nested array (ex String[][])

        // and returns analyzedData as ArrayList<DataGroup>.
        // Each data Group has a group.
        // Each group has several data
        // Each data has Localdate and the number of (positive case OR new death OR people vaccinated).
        // If user choose only searching positive case, both new death and people vaccinated fields is 0 except for positive case fields
        // If user ~~                    new death, both positive case and people vaccinated fields is 0 except for new death fields
        // If user ~~                    people vaccinated, both positive case and new death fields is 0 except for people vaccinated fields
        // Please read Data class and DataGroup class that I made below

        // FIXME: 2021-08-09 Lee Gain
        LocalDate l1 = LocalDate.of(2021, 5, 20);
        LocalDate l2 = LocalDate.of(2021, 5, 24);
        LocalDate l3 = LocalDate.of(2021, 5, 26);

        LocalDate l4 = LocalDate.of(2021, 4, 3);
        LocalDate l5 = LocalDate.of(2021, 5, 3);
        LocalDate l6 = LocalDate.of(2021, 6, 27 );

        LocalDate l7 = LocalDate.of(2021, 5, 27);
        LocalDate l8 = LocalDate.of(2021, 5, 30);
        LocalDate l9 = LocalDate.of(2021, 6, 2);

        ArrayList<LocalDate> a1 = new ArrayList<>(){{add(l1); add(l2); add(l3);}};
        ArrayList<LocalDate> a2 = new ArrayList<>(){{add(l4); add(l5); add(l6);}};
        ArrayList<LocalDate> a3 = new ArrayList<>(){{add(l7); add(l8); add(l9);}};
        ArrayList<ArrayList<LocalDate>> groupForTesting = new ArrayList<>(){{add(a1); add(a2); add(a3);}};

        System.out.printf("\n************************************************************\n +" +
                        "Available metric\n" +
                            "\t[1] Positive case\n" +
                            "\t[2] Deaths\n" +
                            "\t[3] People vaccinated\n" +
                        "************************************************************\n" +
                        "Please choose your metric(1/2/3)>> ");
        int metric = Integer.parseInt(sc.nextLine());
        System.out.println();
        ArrayList<DataGroup> analyzedData = getData(userDateObj, metric, groupForTesting);

        // FIXME: 2021-08-10 Lee Gain
        for(DataGroup dg : analyzedData){
            for(Data d : dg.getGroupedData()){
                d.displayData();
            }
        }
        // part 3


    }
    // part 1


    // part 2
    private static ArrayList<DataGroup> getData(Date dtObj, int metric, ArrayList<ArrayList<LocalDate>> groupedDate) throws Exception{
        ArrayList<DataGroup> dataGroups = new ArrayList<>();
        String geographicArea = dtObj.getGeographicArea();
        ArrayList<String[]> dbOfGeographicArea = getDatabase(geographicArea);

        for(ArrayList<LocalDate> dateList: groupedDate){
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
            if(tempRow[1].equals(geographicArea) ||
                tempRow[2].equals(geographicArea)){
                for(int i = 0; i < tempRow.length; i++){
                    row[i] = tempRow[i];
                }
                db.add(row);
            }
            line = fileContainer.readLine();
        }
        return db;
    }

    private static DataGroup getDataByDateGroup(ArrayList<String[]> dbOfGeographicArea, ArrayList<LocalDate> dateList, int metric){
        // it returns database(particular country - date) for each group
        DataGroup dg =  new DataGroup();
        for(LocalDate date : dateList){
            for(String[] row : dbOfGeographicArea){
                String[] dateInCsv = row[3].split("/");
                int year = Integer.parseInt(dateInCsv[2]);
                int month = Integer.parseInt(dateInCsv[0]);
                int day = Integer.parseInt(dateInCsv[1]);
                LocalDate tempDate = LocalDate.of(year, month, day);

                if(tempDate.isEqual(date)){
                    Data data = new Data(date);
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
                    dg.addData(data);
                }
            }
        }
        return  dg;
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
    private ArrayList<Data> groupedData = new ArrayList<Data>();

    //getter
    public ArrayList<Data> getGroupedData() {
        return groupedData;
    }

    public void addData(Data dt){
        groupedData.add(dt);
    }
}