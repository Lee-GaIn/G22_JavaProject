package main;

import processeddata.DataGroup;
import util.ExceptionManager;
import util.UserInputManager;
import util.ValidationManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Data {
    private String geographicArea;
    private LocalDate[] timeRange = new LocalDate[2];

    // Constructor
    private Data(String geographicArea, String userTime) throws Exception {
        this.geographicArea = geographicArea;
        setTimeRange(userTime);
    }

    // Getter and Setter
    private void setTimeRange(String userTime) throws Exception {
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
            if (dateAndNum[1].contains("day")) {
                String[] numOnly = dateAndNum[1].split(" ");
                int numOfDays = Integer.parseInt(numOnly[0]);
                LocalDate startDate = strToLocalDate(date);
                LocalDate endDate = startDate.plusDays(numOfDays);
                timeRange[0] = startDate;
                timeRange[1] = endDate;
            }
            if (dateAndNum[1].contains("week")) {
                String[] numOnly = dateAndNum[1].split(" ");
                int numOfWeeks = Integer.parseInt(numOnly[0]) * 7;
                LocalDate startDate = strToLocalDate(date);
                LocalDate endDate = startDate.plusDays(numOfWeeks);
                timeRange[0] = startDate;
                timeRange[1] = endDate;
            }
        }

        // Set time range for option [3] A number of days or weeks to a specific date
        String dateValid = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))";
        String[] dateSplit = userTime.split(",");
        if (Pattern.matches(dateValid, dateSplit[1])) {
            String getDate = dateSplit[1];
            if (dateSplit[0].contains("day")) {
                String[] getNum = dateSplit[0].split(" ");
                int numDays = Integer.parseInt(getNum[0]);
                LocalDate endDate = strToLocalDate(getDate);
                LocalDate startDate = endDate.minusDays(numDays);
                timeRange[0] = startDate;
                timeRange[1] = endDate;
            }
            if (dateSplit[0].contains("week")) {
                String[] getNum = dateSplit[0].split(" ");
                int numWeeks = Integer.parseInt(getNum[0]) * 7;
                LocalDate endDate = strToLocalDate(getDate);
                LocalDate startDate = endDate.minusDays(numWeeks);
                timeRange[0] = startDate;
                timeRange[1] = endDate;
            }
        }

        ExceptionManager.checkTimeRangeException(timeRange);

        }

    public String getGeographicArea() {
        return geographicArea;
    }

    public LocalDate[] getTimeRange() {
        return timeRange;
    }

    // Method
    @Override
    public String toString() {
        return String.format("\nGeographic Area: %s \nStart date: %s \nEnd date: %s \n", geographicArea, timeRange[0], timeRange[1]);
    }

    public static Data createDataObj() throws Exception {
        String date = "";

        // Choose geographic area.
        UserInputManager.displayMenu("""
                                    [STEP 1]
                                    Please enter a continent or country name you want to choose. (Vietnam, Asia...)>>\s""");
        String geographicArea = UserInputManager.getGeographicUserInput();
        UserInputManager.displayMenu("\n");

        // Choose date.
        String menu = """
                    ************************************************************
                    Available form for determining date.
                    \t[1] A pair of start date and end date
                    \t[2] A number of days or weeks from a specific date
                    \t[3] A number of days or weeks to a specific date
                    ************************************************************
                    Please enter a number to decide the form of date range(1/2/3)>>\s""";
        UserInputManager.displayMenu(menu);
        int dateMethod = UserInputManager.getIntUserInput();

        switch (dateMethod) {
            case 1:
                UserInputManager.displayMenu("Please enter a start date(mm/dd/yyyy)>> ");
                String startDate1 = UserInputManager.getStrUserInput();
                UserInputManager.displayMenu("Please enter an end date(mm/dd/yyyy)>> ");
                String endDate1 = UserInputManager.getStrUserInput();
                date = startDate1 + "," + endDate1;
                break;
            case 2:
                UserInputManager.displayMenu("Please enter a start date(mm/dd/yyyy)>> ");
                String startDate2 = UserInputManager.getStrUserInput();
                UserInputManager.displayMenu("Please enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate2 = UserInputManager.getStrUserInput();

                date = startDate2 + "," + particularDate2;
                break;
            case 3:
                UserInputManager.displayMenu("Please enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate3 = UserInputManager.getStrUserInput();
                UserInputManager.displayMenu("Please enter an end date(mm/dd/yyyy)>> ");
                String endDate3 = UserInputManager.getStrUserInput();
                date = particularDate3 + "," + endDate3;
                break;
            default:
                ExceptionManager.throwInvalidOption();
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

    public void showData() {
        System.out.println(this);
    }

    static DataGroup setDateList(LocalDate[] userTimeRange) {
        // This method receives LocalDate "userTimeRange" as an parameter
        // and returns DataGroup.

        DataGroup dg = new DataGroup();
        LocalDate start = userTimeRange[0];
        dg.addData(new processeddata.Data(start));
        LocalDate end = userTimeRange[1];
        int count = 1;
        LocalDate nextDate = start.plusDays(count);
        while (nextDate.isBefore(end)) {
            count++;
            dg.addData(new processeddata.Data(nextDate));
            nextDate = start.plusDays(count);
        }
        dg.addData(new processeddata.Data(end));
        return dg;
    }

    static ArrayList<DataGroup> noGrouping(DataGroup userTimeRange) {
        // This method receives DataGroup "userTimeRange" as an parameter
        // and returns ArrayList of DataGroup
        // where each group only contains 1 date.

        ArrayList<DataGroup> noGroup = new ArrayList<>();
        int size = userTimeRange.getSize();
        for (int i = 0; i < size; i++) {
            DataGroup dg = new DataGroup();
            dg.addData(userTimeRange.getData(i));
            noGroup.add(dg);
        }
        return noGroup;
    }

    static ArrayList<DataGroup> groupByGroupNum(DataGroup userTimeRange, int numOfGroups) {
        // This method receives DataGroup "userTimeRange" as an parameter
        // and returns ArrayList of DataGroup.
        // The number of groups is decided by the user input.

        ArrayList<DataGroup> groups = new ArrayList<>();
        int numGroups = numOfGroups;
        int size = userTimeRange.getSize();
        int count = 0;
        if (numGroups == 0) {
            System.out.println("ERROR: The number of groups cannot be zero!");
            System.exit(1);
        } else if (size < numGroups) {
            System.out.println("ERROR: The number of groups is bigger than the number of days input!");
            System.exit(2);
        } else {
            for (int i = 0; i < numOfGroups; i++) {
                int numElements = size / numGroups;
                if (size % numGroups != 0) {
                    numElements++;
                }
                DataGroup dg = new DataGroup();
                for (int j = 0; j < numElements; j++) {
                    dg.addData(userTimeRange.getData(count));
                    count++;
                }
                groups.add(dg);
                numGroups--;
                size -= numElements;
            }
        }
        return groups;
    }

    static ArrayList<DataGroup> groupByDayNum(DataGroup userTimeRange, int numOfDays) throws Exception {
        // This method receives DataGroup "userTimeRange" as an parameter
        // and returns ArrayList of DataGroup.
        // The number of dates in a group is decided by the user input
        // if the divided groups do not have the same number of dates, raise exception.

        ArrayList<DataGroup> groups = new ArrayList<>();
        int i = 0;
        int size = userTimeRange.getSize();
        if (numOfDays == 0) {
            System.out.println("ERROR: The number of days in a group cannot be zero!");
            System.exit(3);
        } else {
            ExceptionManager.checkGroupNum(size, numOfDays);
            while (i < size) {
                DataGroup dg = new DataGroup();
                for (int j = 0; j < numOfDays; j++) {
                    if (i < size) {
                        dg.addData(userTimeRange.getData(i));
                        i++;
                    }
                }
                groups.add(dg);
            }
        }
        return groups;
    }
}
