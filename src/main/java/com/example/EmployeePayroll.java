package com.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;

public class EmployeePayroll {
    public static void main(String[] args) {
        DBOperations dbOperations = new DBOperations();

        // 1. Reading data
        ArrayList<Employee> employeeList = dbOperations.read_data();
        System.out.println("Employee List:");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        // 2. Updating data
        int newSalary = 60000;
        String employeeNameToUpdate = "Ferissa";
        dbOperations.update_data(newSalary, employeeNameToUpdate);

        // 3. Get data by date range
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";
        ArrayList<String> dataByDateRange = dbOperations.get_by_date_range(startDate, endDate);
        System.out.println("\nData by Date Range:");
        for (String data : dataByDateRange) {
            System.out.println(data);
        }

        // 4. Get stats by gender
        String statsQuery = "SELECT gender, sum(salary), min(salary), max(salary), avg(salary) FROM employee_payroll GROUP BY gender;";
        ArrayList<String> statsByGender = dbOperations.getStatsByGender(statsQuery);
        System.out.println("\nStats by Gender:");
        for (String stats : statsByGender) {
            System.out.println(stats);
        }
    }
}

