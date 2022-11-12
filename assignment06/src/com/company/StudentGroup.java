package com.company;

import com.company.Extansions.ExtensionStudentGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentGroup implements Comparable<StudentGroup>{

    private String name;
    private List<Student> studentList;

    public StudentGroup(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
        ExtensionStudentGroup.add(this);
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return Objects.equals(name, that.name) && Objects.equals(studentList, that.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentList);
    }

    @Override
    public int compareTo(StudentGroup o) {
        return 0;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + name + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
