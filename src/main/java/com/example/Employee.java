package com.example;

public class Employee {
    int id;
    double salary;
    String name;
    String start_date;
    String gender;
    String phone_number;
    String address;
    double basic_pay;
    double deductions;
    double taxable_pay;
    double income_tax;
    double net_pay;
    String department;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;

    }

    public Employee(int id, double salary, String name, String start_date, String gender,
            String phone_number, String address, double basic_pay, double deductions,
            double taxable_pay, double income_tax, double net_pay, String department) {
        this.id = id;
        this.salary = salary;
        this.name = name;
        this.start_date = start_date;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
        this.basic_pay = basic_pay;
        this.deductions = deductions;
        this.taxable_pay = taxable_pay;
        this.income_tax = income_tax;
        this.net_pay = net_pay;
        this.department = department;
    }

    public String[] getEmployeearray() {
        String[] temp = new String[3];
        temp[0] = name;
        temp[1] = id + "";
        temp[2] = salary + "";
        return temp;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public double getBasic_pay() {
        return basic_pay;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getTaxable_pay() {
        return taxable_pay;
    }

    public double getIncome_tax() {
        return income_tax;
    }

    public double getNet_pay() {
        return net_pay;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return this.id + ", "
                + this.name + ", "
                + this.start_date + ", "
                + this.gender + ", "
                + this.phone_number + ", "
                + this.address + ", "
                + this.salary + ", "
                + this.deductions + ", "
                + this.taxable_pay + ", "
                + this.income_tax + ", "
                + this.department;
    }
}

