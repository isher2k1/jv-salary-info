package core.basesyntax;

import java.time.LocalDate;
import java.util.ArrayList;

public class Employees {
    private ArrayList<EmployeeRecord> employeeRecords;

    public Employees() {
        employeeRecords = new ArrayList<>();
    }

    public void put(String name, WorkRecord workRecord, LocalDate dateFrom, LocalDate dateTo) {
        if (!workRecord.isWorkedAtPeriod(dateFrom, dateTo)) {
            return;
        }
        for (EmployeeRecord record : employeeRecords) {
            if (record.getName().equals(name)) {
                record.addWorkRecord(workRecord);
                return;
            }
        }
        EmployeeRecord employeeRecord = new EmployeeRecord(name);
        employeeRecord.addWorkRecord(workRecord);
        employeeRecords.add(employeeRecord);
    }

    public ArrayList<WorkRecord> get(String name) {
        for (EmployeeRecord record : employeeRecords) {
            if (record.getName().equals(name)) {
                return record.getWorkRecords();
            }
        }
        return new ArrayList<>();
    }

    public int getGeneralOutcome(String name) {
        for (EmployeeRecord record : employeeRecords) {
            if (record.getName().equals(name)) {
                return record.generalOutcome;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (EmployeeRecord record : employeeRecords) {
            stringBuilder.append(record)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private class EmployeeRecord {
        private String name;
        private int generalOutcome;
        private ArrayList<WorkRecord> workRecords;

        public EmployeeRecord(String name) {
            this.name = name;
            this.workRecords = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<WorkRecord> getWorkRecords() {
            return workRecords;
        }

        public void addWorkRecord(WorkRecord workRecord) {
            this.workRecords.add(workRecord);
            this.generalOutcome += workRecord.getGeneralIncome();
        }

        @Override
        public String toString() {
            return name + " : " + workRecords;
        }
    }
}
