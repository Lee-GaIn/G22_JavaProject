package data;

import java.time.LocalDate;

public class Data {
    private LocalDate date;
    private int newCases;
    private int newDeaths;
    private int peopleVaccinated;
    private int newPeopleVaccinated;

    //Constructor
    public Data(LocalDate date) {
        this.date = date;
    }

    //Getter and Setter
    public void setPositiveCases(int newCases) {
        if(newCases < 0){
            return;
        }
            this.newCases = newCases;
    }

    public void setNewDeaths(int newDeaths) {
        if(newDeaths < 0){
            return;
        }this.newDeaths = newDeaths;
    }

    public void setPeopleVaccinated(int peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }

    public void setNewPeopleVaccinated(int newPeopleVaccinated) {
        if(newPeopleVaccinated < 0){
            return;
        }
        this.newPeopleVaccinated = newPeopleVaccinated;
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

    public int getNewPeopleVaccinated() {
        return newPeopleVaccinated;
    }

    //Method
    public String dateToString(){
        return date.toString();
    }

    public void displayData() {
        System.out.printf("Date: %s\nNew Cases: %d\nNew Deaths: %d\nPeople Vaccinated: %d\nNew total of people vaccinated: %d\n",
                getDate(), getNewCases(), getNewDeaths(), getPeopleVaccinated(), getNewPeopleVaccinated());
    }
}

