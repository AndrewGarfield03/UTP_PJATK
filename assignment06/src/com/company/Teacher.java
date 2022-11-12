package com.company;

import com.company.Extansions.ExtensionTeacher;

import java.util.Date;
import java.util.Objects;

public class Teacher extends Person{
    private Degree degree;

    Teacher(String pesel, String firstName, String surname, Date birthdate, Nationality nationality, Degree degree) {
        super(pesel, firstName, surname, birthdate, nationality);
        this.degree=degree;
        ExtensionTeacher.add(this);
    }

    public Degree getDegree() {
        return degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(degree, teacher.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), degree);
    }

    @Override
    public String toString() {
        return super.toString() +
                "degree=" + degree +
                '}';
    }
}
