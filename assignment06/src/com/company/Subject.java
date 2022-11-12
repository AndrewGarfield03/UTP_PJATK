package com.company;

import com.company.Extansions.ExtensionSubject;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Subject implements Comparable<Subject>{

    private String name;
    private Department supervisingDepartment;
    private Teacher lecturer;
    private List<Student> attendingStudents;

    public Subject(String name, Department supervisingDepartment, Teacher lecturer, List<Student> attendingStudents) {
        this.name = name;
        this.supervisingDepartment = supervisingDepartment;
        this.lecturer = lecturer;
        this.attendingStudents = attendingStudents;
        ExtensionSubject.add(this);
    }

    public String getName() {
        return name;
    }

    public Department getSupervisingDepartment() {
        return supervisingDepartment;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public List<Student> getAttendingStudents() {
        return attendingStudents;
    }

    @Override
    public int compareTo(Subject subject) {
        return Comparator.comparing(Subject::getName).compare(this, subject);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", supervisingDepartment=" + supervisingDepartment +
                ", lecturer=" + lecturer +
                ", attendingStudents=" + attendingStudents +
                '}';
    }
}
