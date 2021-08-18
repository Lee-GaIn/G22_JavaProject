package main;

import processeddata.DataGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Data {
    private String geographicArea;
    private LocalDate[] timeRange = new LocalDate[2];

    // Constructor
    private Data(String geographicArea, String userTime) {
        this.geographicArea = geographicArea;
        setTimeRange(userTime);
    }

    // Getter and Setter
    private void setTimeRange(String userTime) {
        // Set time range for option [1] A pair of start date and end date
        String dateToDate = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))," +
                "(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))$";
        if (Pattern.matches(dateToDate, userTime)) {
            String[] datesArr = userTime.split(",");
            for (int i = 0; i < datesArr.length; i++) {
                LocalDate date = strToLocalDate(datesArr[i]);
                timeRange[i] = date;
            }
        }

        // Set time range for option [2] A number of days or weeks from a specific date
        String dateCheck = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))";
        String[] dateAndNum = userTime.split(",");
        if (Pattern.matches(dateCheck, dateAndNum[0])) {
            String date = dateAndNum[0];
            if (dateAndNum[1].contains("days")) {
                String[] numOnly = dateAndNum[1].split(" ");
                int numOfDays = Integer.parseInt(numOnly[0]);
                LocalDate startDate = strToLocalDate(date);
                LocalDate endDate = startDate.plusDays(numOfDays);
                timeRange.add(startDate);
                timeRange.add(endDate);
            }
            if (dateAndNum[1].contains("weeks")) {
                String[] numOnly = dateAndNum[1].split(" ");
                int numOfWeeks = Integer.parseInt(numOnly[0]) * 7;
                LocalDate startDate = strToLocalDate(date);
                LocalDate endDate = startDate.plusDays(numOfWeeks);
                timeRange.add(startDate);
                timeRange.add(endDate);
            }
        }

//      Set time range for option [3] A number of days or weeks to a specific date
        String dateValid = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))";
        String[] dateSplit = userTime.split(",");
        if (Pattern.matches(dateValid, dateSplit[1])) {
            String getDate = dateSplit[1];
            if (dateSplit[0].contains("days")) {
                String[] getNum = dateSplit[0].split(" ");
                int numDays = Integer.parseInt(getNum[0]);
                LocalDate endDate = strToLocalDate(getDate);
                LocalDate particularDate = endDate.minusDays(numDays);
                timeRange.add(particularDate);
                timeRange.add(endDate);
            }
            if (dateSplit[0].contains("weeks")) {
                String[] getNum = dateSplit[0].split(" ");
                int numWeeks = Integer.parseInt(getNum[0]) * 7;
                LocalDate endDate = strToLocalDate(getDate);
                LocalDate particularDate = endDate.minusDays(numWeeks);
                timeRange.add(particularDate);
                timeRange.add(endDate);
            }
        }
        // exception

    }

    public String getGeographicArea() {
        return geographicArea;
    }

    public LocalDate[] getTimeRange() {
        return timeRange;
    }

    // Method
    public String toString() {
        return String.format("\nGeographic Area: %s \nStart date: %s \nEnd date: %s \n", geographicArea, timeRange[0], timeRange[1]);
    }

    public static Data createDataObj() {
        String date = "";
        Scanner sc = new Scanner(System.in);

        // Choose geographic area.
        UserInterface.displayMenu("[STEP 1] \nPlease enter a continent or country name you want to choose." +
                "(Vietnam, Asia...)>> ");
        String geographicArea = sc.nextLine().trim();
        UserInterface.displayMenu("\n");

        // Choose date
        String menu = """
                    ************************************************************
                    Available form for determining date.
                    \t[1] A pair of start date and end date
                    \t[2] A number of days or weeks from a specific date
                    \t[3] A number of days or weeks to a specific date
                    ************************************************************
                    Please enter a number to decide the form of date range(1/2/3)>>\s""";
        UserInterface.displayMenu(menu);
        int dateMethod = UserInterface.getIntUserInput();

        switch (dateMethod) {
            case 1:
                UserInterface.displayMenu("Please enter a start date(mm/dd/yyyy)>> ");
                String startDate1 = sc.nextLine();
                UserInterface.displayMenu("Please enter an end date(mm/dd/yyyy)>> ");
                String endDate1 = sc.nextLine();
                date = startDate1 + "," + endDate1;
                break;
            case 2:
                UserInterface.displayMenu("Please enter a start date(mm/dd/yyyy)>> ");
                String startDate2 = sc.nextLine();
                UserInterface.displayMenu("Please enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate2 = sc.nextLine();

                date = startDate2 + "," + particularDate2;
                break;
            case 3:
                UserInterface.displayMenu("Please enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate3 = sc.nextLine();
                UserInterface.displayMenu("Please enter an end date(mm/dd/yyyy)>> ");
                String endDate3 = sc.nextLine();
                date = particularDate3 + "," + endDate3;
                break;
            default:
                // make exception later
                // FIXME: 2021-08-14 
        }
        return new Data(geographicArea, date);
    }

    public static LocalDate strToLocalDate(String aDate) {
        // This method receives string "aDate" (mm/dd/yyyy) as an parameter
        // and returns LocalDate.

        String[] aDateArr = aDate.split("/");
        int year = Integer.parseInt(aDateArr[2]);
        int month = Integer.parseInt(aDateArr[0]);
        int day = Integer.parseInt(aDateArr[1]);

        return LocalDate.of(year, month, day);
    }

    public void showData(){
        System.out.println(this.toString());
    }

    static DataGroup ListOfDates(ArrayList<LocalDate> userTimeRange) {
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


    static ArrayList<DataGroup> noGrouping(DataGroup userTimeRange) {
        ArrayList<DataGroup> noGroup = new ArrayList<>();
        for (int i = 0; i< userTimeRange.getSize(); i++)
        {
            DataGroup dg = new DataGroup();
            dg.addData(userTimeRange.getData(i));
            noGroup.add(dg);
        }
        return noGroup;
    }

    static ArrayList<DataGroup> groupByGroupNum(DataGroup userTimeRange, int numOfGroups) {
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

    static ArrayList<DataGroup> groupByDayNum(DataGroup userTimeRange, int numOfDays) {
        ArrayList<DataGroup> groups = new ArrayList<>();
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
}
