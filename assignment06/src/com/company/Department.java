package com.company;

import com.company.Extansions.ExtensionDepartment;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Department implements Comparable<Department>{
    private String name;
    private List<Teacher> employees;

    public Department(String name, List<Teacher> employees) {
        this.name = name;
        this.employees = employees;
        ExtensionDepartment.add(this);
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employees);
    }

    @Override
    public int compareTo(Department department) {
        return Comparator.comparing(Department::getName).compare(this, department);
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
