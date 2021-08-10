
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Summary {
    public static Summary createSummaryObj(Date userDateObj){
        Scanner sc = new Scanner(System.in);
        // part 1

        // part 2
        // FIXME: 2021-08-09 Lee Gain
        ArrayList<ArrayList<LocalDate>> groupForTesting = new ArrayList<ArrayList<LocalDate>>(){};


        System.out.printf("Available metric\n");
        System.out.printf("[1] Positive case\n" +
                        "[2] Deaths\n" +
                        "[3] People vaccinated\n");
        System.out.printf("Please choose your metric(1/2/3)>> ");
        int metric = Integer.parseInt(sc.nextLine());
        ArrayList<DataGroup> analyzedData = getData(userDateObj, metric, groupForTesting);
        // part 3

        return new Summary();
    }
    // part 1


    // part 2
    private static ArrayList<DataGroup> getData(Date dtObj, int metric, ArrayList<ArrayList<LocalDate>> groupedDate){
        ArrayList<DataGroup> dataGroups = new ArrayList<DataGroup>();

        try{
            String geographicArea = dtObj.getGeographicArea();
            ArrayList<String[]> dbOfGeographicArea = getDatabase(geographicArea);

            for(ArrayList<LocalDate> dateList: groupedDate){
                DataGroup dg = getDataByDateGroup(dbOfGeographicArea , dateList, metric);
                dataGroups.add(dg);
            }
        } catch (Exception e) {
            System.out.println("Exception");
        } finally {
            return dataGroups;
        }
    }

    private static ArrayList<String[]> getDatabase(String geographicArea) throws Exception{
        // it returns database for particular country or continent
        ArrayList<String[]> db = new ArrayList<String[]>();
        FileReader csv = new FileReader("..//lib//covid-19.csv");
        BufferedReader fileContainer = new BufferedReader(csv);
        String line = fileContainer.readLine();
        while(line != null){
            String[] row =  line.split(",");
            if(row[1].equals(geographicArea) ||
                row[2].equals(geographicArea)){
                db.add(row);
            }
            line = fileContainer.readLine();
        }
        return db;
    }

    private static DataGroup getDataByDateGroup(ArrayList<String[]> dbOfGeographicArea, ArrayList<LocalDate> dateList, int metric){
        // it returns database for each group
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
                            data.setPositiveCases(Integer.parseInt(row[5]));
                            break;
                        // death
                        case 2:
                            data.setNewDeath(Integer.parseInt(row[6]));
                            break;
                        //vaccine
                        case 3:
                            data.setPeopleVaccinated(Integer.parseInt(row[7]));
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
    LocalDate date;
    int positiveCases;
    int newDeath;
    int peopleVaccinated;

    //Constructor
    Data(LocalDate date){ this.date = date;
    }

    //Setter
    void setPositiveCases(int positiveCases){
        this.positiveCases = positiveCases;
    }

    void setNewDeath(int newDeath){
        this.newDeath = newDeath;
    }

    void setPeopleVaccinated(int peopleVaccinated){
        this.peopleVaccinated = peopleVaccinated;
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