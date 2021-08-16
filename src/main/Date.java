package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Date {
    private static String geographicArea;
    private static ArrayList<LocalDate> timeRange = new ArrayList<>(2);
    // startDate and endDate
    // FIXME: 2021-08-09 Lee Gain

    // Constructor
    private Date(String geographicArea, String userTime) {
        this.geographicArea = geographicArea;
        setTimeRange(userTime);
    }

    // Getter and Setter
    private void setTimeRange(String userTime) {
        // regular expression for mm/dd/yyyy "([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021)"
        // Use it if you need, I will delete above comment before submitting :>>

        // yyyy/mm/dd ~ yyyy/mm/dd
        // userTime = yyyy/mm/dd,yyyy/mm/dd
        String dateToDate = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))," +
                "(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))$";
        if (Pattern.matches(dateToDate, userTime)) {
            String[] datesArr = userTime.split(",");
            for (int i = 0; i < datesArr.length; i++) {
                LocalDate date = strToLocalDate(datesArr[i]);
                timeRange.add(i, date);
            }
        }

        // A number of days or weeks from a particular date
        // userTime = mm/dd/yyyy,n days OR mm/dd/yyyy,n weeks
        String dateCheck = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))";
        String[] dateAndNum = userTime.split(",");
        if (Pattern.matches(dateCheck, dateAndNum[0])) {
            String date = dateAndNum[0];
            if (dateAndNum[1].contains("days")) {
                String[] numOnly = dateAndNum[1].split(" ");
                int numOfDays = Integer.parseInt(numOnly[0]) - 1;
                LocalDate startDate = strToLocalDate(date);
                LocalDate endDate = startDate.plusDays(numOfDays);
                timeRange.add(startDate);
                timeRange.add(endDate);
            }
            if (dateAndNum[1].contains("weeks")) {
                String[] numOnly = dateAndNum[1].split(" ");
                int numOfWeeks = Integer.parseInt(numOnly[0]) * 7 - 1;
                LocalDate startDate = strToLocalDate(date);
                LocalDate endDate = startDate.plusDays(numOfWeeks);
                timeRange.add(startDate);
                timeRange.add(endDate);
            }
        }

        // A number of days or weeks to a particular date
        // userTime = n days,mm/dd/yyyy OR n weeks,mm/dd/yyyy
        String dateValid = "^(([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01])/(2020|2021))";
        String[] dateSplit = userTime.split(",");
        if (Pattern.matches(dateValid, dateSplit[1])) {
            String getDate = dateSplit[1];
            if (dateSplit[0].contains("days")) {
                String[] getNum = dateSplit[0].split(" ");
                int numDays = Integer.parseInt(getNum[0]) - 1;
                LocalDate endDate = strToLocalDate(getDate);
                LocalDate particularDate = endDate.minusDays(numDays);
                timeRange.add(particularDate);
                timeRange.add(endDate);
            }
            if (dateSplit[0].contains("weeks")) {
                String[] getNum = dateSplit[0].split(" ");
                int numWeeks = Integer.parseInt(getNum[0]) * 7 - 1;
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

    public ArrayList<LocalDate> getTimeRange() {
        return timeRange;
    }

    // Method
    public static Date createDateObj() {
        String date = "";
        Scanner sc = new Scanner(System.in);

        // Choose geographic area.
        System.out.printf("[STEP 1] \nPlease enter a continent or country name you want to choose." +
                "(Vietnam, Asia...)>> ");
        String geographicArea = sc.nextLine().trim();

        // Choose date
        String menu = "************************************************************\n" +
                "Available form for determining date.\n" +
                "\t[1] A pair of start date and end date \n" +
                "\t[2] A number of days or weeks from a specific date \n" +
                "\t[3] A number of days or weeks to a specific date \n" +
                "************************************************************\n" +
                "Please enter a number to decide the form of date range(1/2/3)>> ";
        System.out.printf(menu);
        int dateMethod = Integer.parseInt(sc.nextLine());
        switch (dateMethod) {
            case 1:
                System.out.printf("\nPlease enter a start date(mm/dd/yyyy)>> ");
                String startDate1 = sc.nextLine();
                System.out.printf("Please enter an end date(mm/dd/yyyy)>> ");
                String endDate1 = sc.nextLine();
                date = startDate1 + "," + endDate1;
                break;
            case 2:
                System.out.printf("\nPlease enter a start date(mm/dd/yyyy)>> ");
                String startDate2 = sc.nextLine();
                System.out.printf("Please enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate2 = sc.nextLine();

                date = startDate2 + "," + particularDate2;
                break;
            case 3:
                System.out.printf("\nPlease enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate3 = sc.nextLine();
                System.out.printf("Please enter an end date(mm/dd/yyyy)>> ");
                String endDate3 = sc.nextLine();
                date = particularDate3 + "," + endDate3;
                break;
            default:
                // make exception later
                // FIXME: 2021-08-14 
        }
        return new Date(geographicArea, date);
    }

    public static LocalDate strToLocalDate(String aDate) {
        // This method receives string "aDate" (mm/dd/yyyy) as an parameter
        // and returns LocalDate.

        String[] aDateArr = aDate.split("/");
        int year = Integer.parseInt(aDateArr[2]);
        int month = Integer.parseInt(aDateArr[0]);
        int day = Integer.parseInt(aDateArr[1]);

        LocalDate date = LocalDate.of(year, month, day);

        return date;
    }

    public void display() {
        System.out.printf("\nGeographic Area: %s \nStart date: %s \nEnd date: %s \n", geographicArea, timeRange.get(0), timeRange.get(1));
    }
}
