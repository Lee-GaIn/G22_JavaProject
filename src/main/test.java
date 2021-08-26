package main;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        LocalDate st1 = LocalDate.of(2020,11,22);
        LocalDate et1 = LocalDate.of(2020,11,22);

        System.out.println(et1.compareTo(st1));
    }

}