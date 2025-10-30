package core.basesyntax;

import java.util.ArrayList;

public class Employees {
    private ArrayList<EmployeeRecord> employeeRecords;

    public Employees() {
        employeeRecords = new ArrayList<>();
    }

    public void put(String name, WorkRecord workRecord) {
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
        return null;
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
        private ArrayList<WorkRecord> workRecords;

        public EmployeeRecord(String name, ArrayList<WorkRecord> workRecords) {
            this.name = name;
            this.workRecords = workRecords;
        }

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

        public void setWorkRecords(ArrayList<WorkRecord> workRecords) {
            this.workRecords = workRecords;
        }

        public void addWorkRecord(WorkRecord workRecord) {
            this.workRecords.add(workRecord);
        }

        @Override
        public String toString() {
            return name + " : " + workRecords;
        }
    }
}
