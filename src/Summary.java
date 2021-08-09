import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Summary {

    public static Summary createSummaryObj(Data userData){
        Scanner sc = new Scanner(System.in);
        // part 1

        // part 2
        // data for testing
        ArrayList<ArrayList<LocalDate>> group = new ArrayList<ArrayList<LocalDate>>(){};
        // data for testing

        System.out.printf("Available metric\n");
        System.out.printf("[1] Positive case\n" +
                        "[2] Deaths\n" +
                        "[3] People vaccinated\n");
        System.out.printf("Please choose your metric(1/2/3)>> ");
        int metric = Integer.parseInt(sc.nextLine());
        dataByMetric(userData, metric, group);
        // part 3

        return new Summary();
    }
    // part 1


    // part 2
    private static void dataByMetric(Data userData, int metric, ArrayList<ArrayList<LocalDate>> group){
        String geographicArea = userData.getGeographicArea();
        String listOfContinent = "Africa-Asia-Europe-North America-Oceania-South America";
        boolean isContinent = listOfContinent.contains(geographicArea);
        getDatabase(geographicArea, isContinent);
    }

    private static void getDatabase(String geographicArea, boolean isContinent){
        // it returns database for particular country or continent

    }


    private void searchFromDatabase(String geographicArea, ArrayList<LocalDate> dateList){
        // it returns database for each group
    }

    //part 3
}
