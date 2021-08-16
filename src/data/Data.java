package data;

import java.time.LocalDate;

public class Data {
    private LocalDate date;
    private int newCases;
    private int newDeaths;
    private int newPeopleVaccinated;
    private int peopleVaccinated;
    private int totalCases;
    private int totalDeaths;

    //Constructor
    public Data(LocalDate date) {
        this.date = date;
    }

    //Getter and Setter
    public void setnewCases(int newCases) {
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

    public void setNewPeopleVaccinated(int newPeopleVaccinated) {
        if(newPeopleVaccinated < 0){
            return;
        }
        this.newPeopleVaccinated = newPeopleVaccinated;
    }

    public void setPeopleVaccinated(int peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
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

    public int getNewPeopleVaccinated() {
        return newPeopleVaccinated;
    }

    public int getPeopleVaccinated() {
        return peopleVaccinated;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    //Method
    public String dateToString(){
        return date.toString();
    }

    public String toString() {
        return String.format("Date: %s\nNew Cases: %d\nNew Deaths: %d\nNew total of people vaccinated: %d\n" +
                            "Total of positive cases: %d\nTotal of deaths: %d\nPeople Vaccinated: %d",
                            getDate(), getNewCases(), getNewDeaths(), getNewPeopleVaccinated(),
                            getTotalCases(),getTotalDeaths(),getPeopleVaccinated());
    }
}

