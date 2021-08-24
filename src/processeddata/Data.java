package processeddata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Data {
    private LocalDate date;
    private int newCases;
    private int newDeaths;
    private int newPeopleVaccinated;
    private int peopleVaccinated;
    private int totalCases;
    private int totalDeaths;

    //Constructor
    public Data(LocalDate date) {
        this.date = date;
    }

    //Getter and Setter
    private void setNewCases(int newCases) {
        if(newCases < 0){
            return;
        }
            this.newCases = newCases;
    }

    private void setNewDeaths(int newDeaths) {
        if(newDeaths < 0){
            return;
        }this.newDeaths = newDeaths;
    }

    private void setNewPeopleVaccinated(int newPeopleVaccinated) {
        if(newPeopleVaccinated < 0){
            return;
        }
        this.newPeopleVaccinated = newPeopleVaccinated;
    }

    private void setPeopleVaccinated(int peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }

    private void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    private void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getNewCases() {
        return newCases;
    }

    public int getNewPeopleVaccinated() {
        return newPeopleVaccinated;
    }

    public int getPeopleVaccinated() {
        return peopleVaccinated;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    //Method
    public String dateToString(){
        return date.toString();
    }

    public String toString() {
        String formatStr = """
                            Date: %s
                            New Cases: %d
                            New Deaths: %d
                            New total of people vaccinated: %d
                            Total of positive cases: %d
                            Total of deaths: %d
                            People Vaccinated: %d""";
        return String.format(formatStr, getDate(), getNewCases(), getNewDeaths(), getNewPeopleVaccinated(),
                                        getTotalCases(),getTotalDeaths(),getPeopleVaccinated());
    }

    public static ArrayList<DataGroup> getData(main.Data userData, ArrayList<DataGroup> dgArr) throws Exception {
        ArrayList<DataGroup> updatedDataGroups = new ArrayList<>();
        String geographicArea = userData.getGeographicArea();
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
        String prevVaccinatedPpl = "0";

        while (line != null) {
            String[] tempRow = line.split(",");
            String[] row = new String[]{"","","","","0","0","0","0"};

            if (tempRow[2].equalsIgnoreCase(geographicArea)) {
                boolean isEmptyVP = (tempRow[6].equals("") || tempRow[6].isEmpty());

                if(!isEmptyVP){
                    prevVaccinatedPpl = tempRow[6];

                }else{
                    tempRow[6] = prevVaccinatedPpl;
                }

                for (int i = 0; i < tempRow.length; i++) {
                    if(tempRow[i] != null && !(tempRow[i].isEmpty())){
                        row[i] = tempRow[i];
                    }
                }

                if(Integer.parseInt(row[4]) <= 0){
                    row[4] = "0";
                }

                if(Integer.parseInt(row[5]) <= 0){
                    row[5] = "0";
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
        int totalCases = 0;
        int totalDeaths = 0;

        for (int i = 0; i < dataArrLength; i++) {
            for (int j = 0; j < dbArrLength; j++) {
                String[] curRow = dbOfGeographicArea.get(j);
                LocalDate tempDate = main.Data.strToLocalDate(curRow[3]);
                LocalDate userDate = dataArr.get(i).getDate();

                if(curRow[4] != null && !(curRow[4].isEmpty())){
                    totalCases += Integer.parseInt(curRow[4]);
                }
                if(curRow[5] != null && !(curRow[5].isEmpty())){
                    totalDeaths += Integer.parseInt(curRow[5]);
                }

                if (tempDate.isEqual(userDate)) {
                    int newCases = Integer.parseInt(curRow[4]);
                    int newDeaths = Integer.parseInt(curRow[5]);
                    int newPeopleVaccinated = Integer.parseInt(curRow[6]);
                    int peopleVaccinated = Integer.parseInt(curRow[6]);
                    if(j != 0){
                        String[] prevRow = dbOfGeographicArea.get(j - 1);
                        int prevPV = Integer.parseInt(prevRow[6]);

                        if(peopleVaccinated <= 0){
                            peopleVaccinated = prevPV;
                            newPeopleVaccinated = prevPV;
                        }
                        newPeopleVaccinated = newPeopleVaccinated - prevPV;
                    }

                    Data data = dataArr.get(i);
                    data.setNewCases(newCases);
                    data.setNewDeaths(newDeaths);
                    data.setNewPeopleVaccinated(newPeopleVaccinated);
                    data.setTotalCases(totalCases);
                    data.setTotalDeaths(totalDeaths);
                    data.setPeopleVaccinated(peopleVaccinated);

                    totalCases = 0;
                    totalDeaths = 0;
                    break;
                }
            }
        }

        return dg;
    }
}

