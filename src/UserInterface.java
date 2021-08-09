import java.awt.*;
import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Welcome! This is a Data analysis machine for COVID-19. \n");
        System.out.printf("Do you want to analyze the data? (Y/N)>> ");
        String ans = sc.nextLine();
        while (ans.equalsIgnoreCase("Y")){
            Data d1 = Data.createDataObj();
            // testing ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
            System.out.println(d1.getGeographicArea());
            for(LocalDate ld : d1.getTimeRange()){
                System.out.println(ld.toString());
            }
            // testing ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
            System.out.printf("Do you want to analyze the data again? (Y/N)>> ");
            ans = sc.nextLine();
        }

        System.out.println("Thank you for using our service.");
    }
}
