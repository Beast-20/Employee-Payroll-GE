package com.example;

public class Employee {
    String name;
    int id;
    int salary;

    public Employee(String name, int id, int salary){
        this.name = name;
        this.id = id;
        this.salary = salary;

    }

    public String[] getEmployeearray(){
        String[] temp = new String[3];
        temp[0] = name;
        temp[1] = id + "";
        temp[2] = salary + "";
        return temp;
    }

    public String get_name(){
        return this.name;
    }

    public int get_id(){
        return this.id;
    }

    public int get_salary(){
        return this.salary;
    }

    @Override
    public String toString() {
        return name + "," + id + "," + salary;
    }
}
