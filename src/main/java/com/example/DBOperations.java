package com.example;

import java.util.ArrayList;

import javax.swing.plaf.nimbus.State;

import java.sql.*;

public class DBOperations {
    public Connection getConnection()throws SQLException{
        String url = "jdbc:mysql://localhost:3306/practice";
        String user = "root";
        String password = "Henlo@123";
        return DriverManager.getConnection(url,user,password);
    }


    public ArrayList<Employee> read_data() {
    ArrayList<Employee> read_list = new ArrayList<>();
    String sqlQuery = "select ep.id, ep.emp_name, ep.salary, ep.start_date, ep.gender, ep.phone_number, ep.address, ep.basic_pay, ep.deductions, ep.taxable_pay, ep.income_tax, ep.net_pay, dep.dept_name from employee_payroll ep inner join employee_department ed ON ep.id = ed.id inner join department dep ON ed.dept_id = dep.dept_id;";

    try (
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
    ) {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String empName = resultSet.getString("emp_name");
            double salary = resultSet.getDouble("salary");
            String startDate = resultSet.getString("start_date");
            String gender = resultSet.getString("gender");
            String phoneNumber = resultSet.getString("phone_number");
            String address = resultSet.getString("address");
            double basicPay = resultSet.getDouble("basic_pay");
            double deductions = resultSet.getDouble("deductions");
            double taxablePay = resultSet.getDouble("taxable_pay");
            double incomeTax = resultSet.getDouble("income_tax");
            double netPay = resultSet.getDouble("net_pay");
            String deptName = resultSet.getString("dept_name");

            // Create a new Employee object
            Employee employee = new Employee(id, salary, empName, startDate, gender, phoneNumber, address, basicPay, deductions, taxablePay, incomeTax, netPay, deptName);

            // Add the employee to the list
            read_list.add(employee);
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Handle the exception appropriately in your actual code
    }

    return read_list;
}
   public void update_data(int salary, String name){
    String sqlQuery = "update employee_payroll set salary = ? where name = ?;";
      try(
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery); 
      )
      {
        statement.setInt(1, salary);
        statement.setString(2, name);
        statement.executeUpdate();
        System.out.println("Updated successfully");
      }
       catch (SQLException e) {
    e.printStackTrace();
}
   }

   public ArrayList<String> get_by_date_range(String start_date, String end_date){
     String sqlQuery = "select ep.name, dep.dept_name, ep.salary from employee_payroll ep inner join employee_department ed ON ep.id = ed.id inner join department dep ON ed.dept_id = dep.dept_id where start_date between cast(? as date) and cast(? as date);";
     ArrayList<String> data = new ArrayList<>();
     try (
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
    ) {
        preparedStatement.setString(1, start_date);
        preparedStatement.setString(2, end_date);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String empName = resultSet.getString("name");
                String deptName = resultSet.getString("dept_name");
                int salary = resultSet.getInt("salary");
                data.add(empName+","+deptName+","+salary);


            }
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Handle the exception appropriately in your actual code
    }
     return data;

   }

   public ArrayList<String> getStatsByGender(String sqlQuery) {
    ArrayList<String> data = new ArrayList<>();
    try (
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);) {
        while (resultSet.next()) {
            String gender = resultSet.getString("gender");
            double sum = resultSet.getDouble("sum(salary)");
            double min = resultSet.getDouble("min(salary)");
            double max = resultSet.getDouble("max(salary)");
            double avg = resultSet.getDouble("avg(salary)");
            data.add(gender + ", " + sum + ", " + min + ", " + max + ", " + avg);
        }
    } catch (SQLException exception) {
        System.out.println(exception.getMessage());
        exception.printStackTrace();
    }
    return data;
}

}
