package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employees employees = initEmployees(data);
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        for (String name : names) {
            ArrayList<WorkRecord> workRecords = employees.get(name);
            int generalOutcome = 0;
            for (WorkRecord record : workRecords) {
                if (record.isWorkedAtPeriod(LocalDate.parse(dateFrom, formatter),
                        LocalDate.parse(dateTo, formatter))) {
                    generalOutcome += record.getGeneralIncome();
                }
            }
            stringBuilder.append(name)
                    .append(" - ")
                    .append(generalOutcome)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    public Employees initEmployees(String[] data) {
        Employees employees = new Employees();

        for (String s : data) {
            String[] splitted = s.split(" ");
            String name = splitted[1];
            int hours = Integer.parseInt(splitted[2]);
            int incomePerHour = Integer.parseInt(splitted[3]);
            LocalDate date = LocalDate.parse(splitted[0], formatter);

            WorkRecord workRecord = new WorkRecord(hours, incomePerHour, date);
            employees.put(name, workRecord);

        }
        return employees;
    }
}
