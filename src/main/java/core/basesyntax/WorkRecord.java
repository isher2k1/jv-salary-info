package core.basesyntax;

import java.time.LocalDate;

public class WorkRecord {
    private int hours;
    private int incomePerHour;
    private LocalDate date;

    public WorkRecord(int hours, int incomePerHour, LocalDate date) {
        this.hours = hours;
        this.incomePerHour = incomePerHour;
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getIncomePerHour() {
        return incomePerHour;
    }

    public void setIncomePerHour(int incomePerHour) {
        this.incomePerHour = incomePerHour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getGeneralIncome() {
        return this.incomePerHour * this.hours;
    }

    public boolean isWorkedAtPeriod(LocalDate fromDate, LocalDate toDate) {
        return (date.isAfter(fromDate) && date.isBefore(toDate))
                || date.isEqual(fromDate) || date.isEqual(toDate);
    }

    @Override
    public String toString() {
        return "{"
                + "hours=" + hours
                + ", incomePerHour=" + incomePerHour
                + ", date=" + date
                + '}';
    }
}
