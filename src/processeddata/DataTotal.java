package processeddata;

import util.ExceptionManager;

import java.util.ArrayList;

public class DataTotal {

    // Methods
    public static int getNewTotal(ArrayList<Data> db, int metric) {
        // This method accepts array list of Data in processeddata package "db" and integer "metric"
        // and returns a total of new cases of the data based on the metric that the user inputs.
        // It throws an exception if the user input is invalid.

        int value = 0;
        for(processeddata.Data dt : db) {
            switch (metric) {
                case 1 -> value += dt.getNewCases();
                case 2 -> value += dt.getNewDeaths();
                case 3 -> value += dt.getNewPeopleVaccinated();
                default -> ExceptionManager.throwInvalidOption();
            }
        }
        return value;
    }

    public static int getUpTo(ArrayList<processeddata.Data> db, int metric) {
        // This method accepts array list of Data in processeddata package "db" and integer "metric"
        // and returns accumulated total data based on the metric that the user inputs.
        // It throws an exception if the user input is invalid.

        int value = 0;
        int dbLength = db.toArray().length;
        processeddata.Data lastDateData = db.get(dbLength - 1);

        switch (metric) {
            case 1 -> value = lastDateData.getTotalCases();
            case 2 -> value = lastDateData.getTotalDeaths();
            case 3 -> value = lastDateData.getPeopleVaccinated();
            default -> ExceptionManager.throwInvalidOption();
        }

        return value;
    }
}
