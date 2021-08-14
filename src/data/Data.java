package data;

import java.time.LocalDate;

public class Data {
    private LocalDate date;
    private int newCases;
    private int newDeaths;
    private int peopleVaccinated;

    //Constructor
    public Data(LocalDate date) {
        this.date = date;
    }

    //Getter and Setter
    public void setPositiveCases(int newCases) {
        this.newCases = newCases;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public void setPeopleVaccinated(int peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getNewCases() {
        return newCases;
    }

    public int getPeopleVaccinated() {
        return peopleVaccinated;
    }

    //Method
    public void displayData() {
        System.out.printf("Date: %s\nNew Cases: %d\nNew Deaths: %d\nPeople Vaccinated: %d\n",
                getDate(), getNewCases(), getNewDeaths(), getPeopleVaccinated());
    }
}

