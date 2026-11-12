package src.main.java;

import tech.tablesaw.api.Table;

public class DataCleaning {

    public static void main(String[] args) {
        Table data = Table.read().csv("resources\\data.csv");
        dataSummary(data);
        // dataCleaning(data);
    }

    public static void dataSummary(Table data) {
        System.out.println(data.structure());
        System.out.println(data.summary());
    }

    public static void dataCleaning(Table data) {

    }
}
