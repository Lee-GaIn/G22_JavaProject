package processeddata;

import java.time.LocalDate;

public class Data {
    private LocalDate date;
    private int newCases;
    private int newDeaths;
    private int newPeopleVaccinated;
    private int peopleVaccinated;
    private int totalCases;
    private int totalDeaths;

    // Constructor
    public Data(LocalDate date) {
        // This constructor accepts LocalDate "date" as a parameter
        //  and constructs a new Data instance.

        this.date = date;
    }

    // Getter and Setter
    private void setNewCases(int newCases) {
        // This setter accepts integer "newCases" as a parameter
        //  and set "newCases" attributes.
        //  if "newCases" is smaller than 0, it does not set the "newCases".

        if(newCases < 0) {
            return;
        }

        this.newCases = newCases;
    }

    private void setNewDeaths(int newDeaths) {
        // This setter accepts integer "newDeaths" as a parameter
        //  and set "newDeaths" attributes.
        //  if "newDeaths" is smaller than 0, it does not set the "newDeaths".

        if(newDeaths < 0) {
            return;
        }

        this.newDeaths = newDeaths;
    }

    private void setNewPeopleVaccinated(int newPeopleVaccinated) {
        // This setter accepts integer "newPeopleVaccinated" as a parameter
        //  and set "newPeopleVaccinated" attributes.
        //  if "newPeopleVaccinated" is smaller than 0, it does not set the "newPeopleVaccinated".

        if(newPeopleVaccinated < 0) {
            return;
        }

        this.newPeopleVaccinated = newPeopleVaccinated;
    }

    private void setPeopleVaccinated(int peopleVaccinated) {
        // This setter accepts integer "peopleVaccinated" as a parameter
        //  and set "peopleVaccinated" attributes.

        this.peopleVaccinated = peopleVaccinated;
    }

    private void setTotalCases(int totalCases) {
        // This setter accepts integer "totalCases" as a parameter
        //  and set "totalCases" attributes.

        this.totalCases = totalCases;
    }

    private void setTotalDeaths(int totalDeaths) {
        // This setter accepts integer "totalDeaths" as a parameter
        //  and set "totalDeaths" attributes.

        this.totalDeaths = totalDeaths;
    }

    public LocalDate getDate() {
        // This method returns LocalDate "date" of the data instance.

        return date;
    }

    public int getNewDeaths() {
        // This method returns integer "newDeaths" of the data instance.

        return newDeaths;
    }

    public int getNewCases() {
        // This method returns integer "newCases" of the data instance.

        return newCases;
    }

    public int getNewPeopleVaccinated() {
        // This method returns integer "newPeopleVaccinated" of the data instance.

        return newPeopleVaccinated;
    }

    public int getPeopleVaccinated() {
        // This method returns integer "peopleVaccinated" of the data instance.

        return peopleVaccinated;
    }

    public int getTotalCases() {
        // This method returns integer "totalCases" of the data instance.

        return totalCases;
    }

    public int getTotalDeaths() {
        // This method returns integer "totalDeaths" of the data instance.

        return totalDeaths;
    }

    // Method
    @Override
    public String toString() {
        // This method returns the detail of a Data instance as a string.

        String formatStr = """
                            Date: %s
                            New Cases: %d
                            New Deaths: %d
                            New total of people vaccinated: %d
                            Total of positive cases: %d
                            Total of deaths: %d
                            People Vaccinated: %d""";

        return String.format(formatStr, getDate(), getNewCases(), getNewDeaths(), getNewPeopleVaccinated(),
                                        getTotalCases(),getTotalDeaths(),getPeopleVaccinated());
    }

    void editFields(int newCases, int newDeaths, int newPeopleVaccinated, int totalCases, int totalDeaths, int peopleVaccinated) {
        setNewCases(newCases);
        setNewDeaths(newDeaths);
        setNewPeopleVaccinated(newPeopleVaccinated);
        setTotalCases(totalCases);
        setTotalDeaths(totalDeaths);
        setPeopleVaccinated(peopleVaccinated);
    }
}

