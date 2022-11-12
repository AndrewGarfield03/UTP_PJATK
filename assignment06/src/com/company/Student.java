package com.company;

import com.company.Extansions.ExtensionStudent;
import com.company.Extansions.ExtensionStudentGroup;

import java.util.Date;
import java.util.Objects;

public class Student extends Person{
    private final String studentBookNum;

    Student(String pesel, String firstName, String surname, Date birthdate, Nationality nationality, String studentBookNum) {
        super(pesel, firstName, surname, birthdate, nationality);
        this.studentBookNum = studentBookNum;
        ExtensionStudent.add(this);
    }

    public String getStudentBookNum() {
        return studentBookNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(studentBookNum, student.studentBookNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentBookNum);
    }

    @Override
    public String toString() {
        return super.toString() + "studentBookNum='" + studentBookNum + '\'' + '}';
    }
}