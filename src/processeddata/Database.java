package processeddata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    private static final int GEOGRAPHIC_AREA = 2;
    private static final int DATE = 3;
    private static final int NEW_CASES = 4;
    private static final int NEW_DEATHS = 5;
    private static final int PEOPLE_VACCINATE = 6;

    // Methods
    public static ArrayList<DataGroup> getData(main.Data userData, ArrayList<DataGroup> dgArr) throws IOException {
        // This method accepts a Data instance in the main package "userData"
        // and an array list of DataGroup "dgArr"
        // and returns an array list of DataGroup.
        // which include information on particular dates.
        // If getDatabase method fails to find the data file, it throws FileNotFoundException.
        // If getDatabase method fails to read the file, it throws IOException

        ArrayList<DataGroup> updatedDataGroups = new ArrayList<>();
        String geographicArea = userData.getGeographicArea();
        ArrayList<String[]> dbOfGeographicArea = getDatabase(geographicArea);

        for (DataGroup dg : dgArr) {
            DataGroup dgWithInfo = getDataByDateGroup(dbOfGeographicArea, dg);
            updatedDataGroups.add(dgWithInfo);
        }

        return updatedDataGroups;
    }

    private static ArrayList<String[]> getDatabase(String geographicArea) throws IOException {
        // It accepts string "geographicArea" as a parameter
        // and returns array list of string array which is the database for a particular country or continent.
        // If getDatabase method fails to find the data file, it throws FileNotFoundException.
        // If getDatabase method fails to read the file, it throws IOException

        String absolutePath = "src\\..\\lib\\covid-data.csv";
        ArrayList<String[]> db = new ArrayList<>();
        FileReader csv = new FileReader(absolutePath);
        BufferedReader fileContainer = new BufferedReader(csv);
        String line = fileContainer.readLine();
        String prevVaccinatedPpl = "0";

        while (line != null) {
            String[] tempRow = line.split(",");
            String[] row = new String[]{"","","","","0","0","0","0"};

            if (tempRow[GEOGRAPHIC_AREA].equalsIgnoreCase(geographicArea)) {
                boolean isEmptyVP = (tempRow[PEOPLE_VACCINATE].equals("") || tempRow[PEOPLE_VACCINATE].isEmpty());

                if(!isEmptyVP) {
                    prevVaccinatedPpl = tempRow[PEOPLE_VACCINATE];

                } else {
                    tempRow[PEOPLE_VACCINATE] = prevVaccinatedPpl;
                }

                for (int i = 0; i < tempRow.length; i++) {
                    if(tempRow[i] != null && !(tempRow[i].isEmpty())){
                        row[i] = tempRow[i];
                    }
                }

                if(Integer.parseInt(row[NEW_CASES]) <= 0) {
                    row[NEW_CASES] = "0";
                }

                if(Integer.parseInt(row[NEW_DEATHS]) <= 0) {
                    row[NEW_DEATHS] = "0";
                }

                db.add(row);
            }
            line = fileContainer.readLine();
        }

        return db;
    }

    private static DataGroup getDataByDateGroup(ArrayList<String[]> dbOfGeographicArea, DataGroup dg) {
        // It accepts an array list of string array "dbOfGeographicArea"
        // which is the database of a particular country or continent
        // and DataGroup "dg" which is the grouped dates
        // and returns DataGroup which is the list of data of a particular country or continent
        // and particular dates.

        ArrayList<Data> dataArr = dg.getGroupedData();
        int dataArrLength = dataArr.toArray().length;
        int dbArrLength = dbOfGeographicArea.toArray().length;
        int totalCases = 0;
        int totalDeaths = 0;

        for (int i = 0; i < dataArrLength; i++) {
            for (int j = 0; j < dbArrLength; j++) {
                String[] curRow = dbOfGeographicArea.get(j);
                LocalDate tempDate = main.Data.strToLocalDate(curRow[DATE]);
                LocalDate userDate = dataArr.get(i).getDate();

                if (curRow[NEW_CASES] != null && !(curRow[NEW_CASES].isEmpty())) {
                    totalCases += Integer.parseInt(curRow[NEW_CASES]);
                }

                if (curRow[NEW_DEATHS] != null && !(curRow[NEW_DEATHS].isEmpty())) {
                    totalDeaths += Integer.parseInt(curRow[NEW_DEATHS]);
                }

                if (tempDate.isEqual(userDate)) {
                    int newCases = Integer.parseInt(curRow[NEW_CASES]);
                    int newDeaths = Integer.parseInt(curRow[NEW_DEATHS]);
                    int newPeopleVaccinated = Integer.parseInt(curRow[PEOPLE_VACCINATE]);
                    int peopleVaccinated = Integer.parseInt(curRow[PEOPLE_VACCINATE]);

                    if (j != 0) {
                        String[] prevRow = dbOfGeographicArea.get(j - 1);
                        int prevPV = Integer.parseInt(prevRow[PEOPLE_VACCINATE]);

                        if(peopleVaccinated <= 0) {
                            peopleVaccinated = prevPV;
                            newPeopleVaccinated = prevPV;
                        }

                        newPeopleVaccinated = newPeopleVaccinated - prevPV;
                    }

                    Data data = dataArr.get(i);
                    data.editFields(newCases, newDeaths, newPeopleVaccinated, totalCases, totalDeaths, peopleVaccinated);

                    totalCases = 0;
                    totalDeaths = 0;
                    break;
                }
            }
        }

        return dg;
    }
}
