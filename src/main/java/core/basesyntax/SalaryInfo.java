package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String INPUT_DELIMITER = " ";
    private static final String OUTPUT_DELIMITER = " - ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employees employees = initEmployees(data);
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + OUTPUT_DELIMITER + dateTo + System.lineSeparator());
        for (String name : names) {
            ArrayList<WorkRecord> workRecords = employees.get(name);
            int generalOutcome = calculateGeneralOutcome(workRecords, dateFrom, dateTo);
            stringBuilder.append(name)
                    .append(OUTPUT_DELIMITER)
                    .append(generalOutcome)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    private Employees initEmployees(String[] data) {
        Employees employees = new Employees();

        for (String s : data) {
            String[] splitted = s.split(INPUT_DELIMITER);
            String name = splitted[NAME_INDEX];
            int hours = Integer.parseInt(splitted[HOURS_INDEX]);
            int incomePerHour = Integer.parseInt(splitted[INCOME_INDEX]);
            LocalDate date = LocalDate.parse(splitted[DATE_INDEX], formatter);

            WorkRecord workRecord = new WorkRecord(hours, incomePerHour, date);
            employees.put(name, workRecord);

        }
        return employees;
    }

    private int calculateGeneralOutcome(ArrayList<WorkRecord> workRecords,
                                        String dateFrom, String dateTo) {
        int generalOutcome = 0;
        for (WorkRecord record : workRecords) {
            if (record.isWorkedAtPeriod(LocalDate.parse(dateFrom, formatter),
                    LocalDate.parse(dateTo, formatter))) {
                generalOutcome += record.getGeneralIncome();
            }
        }
        return generalOutcome;
    }
}
