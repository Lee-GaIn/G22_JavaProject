package main;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws  Exception{
        Scanner sc = new Scanner(System.in);
        System.out.printf("Welcome! This is a data analysis machine for COVID-19. \n" +
                        "Do you want to analyze the data? (Y/N)>> ");
        String ans = sc.nextLine();
        while (ans.equalsIgnoreCase("Y")){
            // Data part
            Date d1 = Date.createDateObj();
            d1.display();

            // main.Summary part
            Summary.createSummaryObj(d1);

            // Display part


            // etc
            System.out.printf("Do you want to analyze the data again? (Y/N)>> ");
            ans = sc.nextLine();
        }

        System.out.println("Thank you for using our service.");
    }
}
