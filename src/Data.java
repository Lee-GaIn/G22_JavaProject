import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

class test {
    public static void main(String[] args) {
        // This class was created for only testing. It will be deleted before submitting.:>>

        // This is an example for the first setter
        String user = "2020/10/20,2021/05/12";
        String area = "USA";
        Data d1= new Data(area, user);
        System.out.println(d1.getGeographicArea());
        for(LocalDate s:d1.getTimeRange()){
            System.out.println(s.toString());
        }
    }
}
public class Data {

    private String geographicArea;
    private ArrayList<LocalDate> timeRange = new ArrayList<LocalDate>(2);

    // Constructor
    public Data(String geographicArea, String userTime){
        this.geographicArea = geographicArea;
        setTimeRange(userTime);
    }

    // Getter and Setter
    private void setTimeRange(String userTime){
        // regular expression for yyyy/mm/dd "((2020|2021)/([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01]))"
        // Use it if you need, I will delete above comment before submitting :>>

        // yyyy/mm/dd ~ yyyy/mm/dd
        // userTime = yyyy/mm/dd,yyyy/mm/dd
        String dateToDate = "^((2020|2021)/([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01]))," +
                "((2020|2021)/([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01]))$";
        if(Pattern.matches(dateToDate, userTime)){
            String[] datesArr = userTime.split(",");
            for(int i = 0; i < datesArr.length; i++){
                LocalDate date = strToLocalDate(datesArr[i]);
                timeRange.add(i, date);
            }
        }

        // A number of days or weeks from a particular date
        // userTime = yyyy/mm/dd,n days OR n weeks, yyyy/mm/dd


        // A number of days or weeks to a particular date
        // userTime = n days,yyyy/mm/dd OR n weeks,yyyy/mm/dd

        // exception

    }

    public String getGeographicArea(){
        return geographicArea;
    }

    public ArrayList<LocalDate> getTimeRange(){
        return timeRange;
    }

    // Method

    public static Data createDataObj(){
        String date = "";
        Scanner sc = new Scanner(System.in);

        // Choose geographic area.
        System.out.printf("Please enter a continent or country name you want to choose." +
                            "(Vietnam, Asia...)>> ");
        String geographicArea = sc.nextLine();

        // Choose date
        System.out.printf("\nAvailable form for determining date.\n" +
                "\t[1] A pair of start date and end date\n" +
                "\t[2] A number of days or weeks from a specific date\n" +
                "\t[3] A number of days or weeks to a specific date\n\n");
        System.out.printf("Please enter a number to decide the form of date range(1/2/3)>> ");
        int dateMethod = Integer.parseInt(sc.nextLine());
        switch (dateMethod){
            case 1:
                System.out.printf("\nPlease enter a start date(yyyy/mm/dd)>> ");
                String startDate1 = sc.nextLine();
                System.out.printf("Please enter an end date(yyyy/mm/dd)>> ");
                String endDate1 = sc.nextLine();
                date = startDate1 + "," + endDate1;
                break;
            case 2:
                System.out.printf("\nPlease enter a start date(yyyy/mm/dd)>> ");
                String startDate2 = sc.nextLine();
                System.out.printf("Please enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate2 = sc.nextLine();

                date = startDate2 + "," + particularDate2 ;
                break;
            case 3:
                System.out.printf("\nPlease enter a number of days or weeks(n days, n weeks)>> ");
                String particularDate3 = sc.nextLine();
                System.out.printf("Please enter an end date(yyyy/mm/dd)>> ");
                String endDate3 = sc.nextLine();
                date = particularDate3 + "," + endDate3;
                break;
            default:
                // make exception later
        }
        return new Data(geographicArea, date);
    }
    private LocalDate strToLocalDate(String aDate){
        // This method receives string "aDate" (yyyy/mm/dd) as an parameter
        // and returns LocalDate.

        String[] aDateArr = aDate.split("/");
        int year = Integer.parseInt(aDateArr[0]);
        int month = Integer.parseInt(aDateArr[1]);
        int day = Integer.parseInt(aDateArr[2]);

        LocalDate date = LocalDate.of(year, month, day);

        return date;
    }
}
