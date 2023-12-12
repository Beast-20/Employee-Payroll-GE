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

public class EmployeePayroll {
    public static void writeFile(String path,List<Employee> emp_list){
      try(CSVWriter writer = new CSVWriter(new FileWriter(path,false))){
          int size = emp_list.size();
          String[] header = {"Name","ID","Salary"};
          writer.writeNext(header);
          for(int i = 0;i<size;i++){
            String[] temp = emp_list.get(i).getEmployeearray();
            writer.writeNext(temp);
          }
          System.out.println("Employee List Added");
      }
      catch(IOException exception){
        exception.printStackTrace();
      }
    }

    public static List<Employee> readFile(String path){
        List<Employee> temp = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(path))){
            String[] header = reader.readNext();
            String[] line;
            //List<Employee> temp = new ArrayList<>();
            while((line=reader.readNext())!=null){
                 Employee emp = new Employee(line[0], Integer.parseInt(line[1]),Integer.parseInt(line[2]));
                 temp.add(emp);
            }
            
        }
        catch(IOException exception){
            exception.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static int countEntries(String path){
        int cnt = 0;
        try(CSVReader reader = new CSVReader(new FileReader(path))) {
          reader.readNext();
          while(reader.readNext()!=null){
            cnt+=1;
          }
            
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
        catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return cnt;
    }

    public static int total_salary_given_by_all_company(String path){
        int total = 0;
        List<Employee> employees = readFile(path);
        int size = employees.size();
        for(int i = 0;i<size;i++){
            total+=employees.get(i).getSalary();
        }
        return total;
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.print("Name of "+(i+1)+" employee : ");
            String name = scanner.next();

            System.out.print("Company ID of "+(i+1)+" employee : ");
            int companyId = scanner.nextInt();

            System.out.print("Salary of "+(i+1)+" employee : ");
            int salary = scanner.nextInt();

            employees.add(new Employee(name, companyId, salary));
        }

        scanner.close();
        System.out.println(employees);
        String path = "Employee data";
        writeFile(path, employees);
        System.out.println(readFile(path));
        System.out.println(total_salary_given_by_all_company(path));

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
