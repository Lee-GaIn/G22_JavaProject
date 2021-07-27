import java.time.LocalDate;
import java.util.ArrayList;
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

        String dateToDate = "^((2020|2021)/([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01]))," +
                             "((2020|2021)/([0]?[1-9]|1[012])/([0]?[1-9]|[12][0-9]|3[01]))$";

        // yyyy/mm/dd ~ yyyy/mm/dd
        if(Pattern.matches(dateToDate, userTime)){
            String[] datesArr = userTime.split(",");
            for(int i = 0; i < datesArr.length; i++){
                LocalDate date = strToLocalDate(datesArr[i]);
                timeRange.add(i, date);
            }
        }

        // n days from yyyy/mm/dd

        // n days to yyyy/mm/dd

        // exception

    }

    public String getGeographicArea(){
        return geographicArea;
    }

    public ArrayList<LocalDate> getTimeRange(){
        return timeRange;
    }

    // Method

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
