package com.company;

import com.company.Extansions.ExtensionPerson;

import java.text.Collator;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public abstract class Person implements Comparable<Person> {
    private static Collator PolishCollator = Nationality.Polish.getCollator();
    private String pesel;
    private String firstName;
    private String surname;
    private Date birthDate;
    private Nationality nationality;

    public Person(String pesel, String firstName, String surname, Date birthdate, Nationality nationality) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthdate;
        this.nationality = nationality;
        ExtensionPerson.add(this);
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(pesel, person.pesel) && Objects.equals(firstName, person.firstName) && Objects.equals(surname, person.surname) && Objects.equals(birthDate, person.birthDate) && nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, surname, birthDate, nationality);
    }

    @Override
    public final int compareTo(Person person){
        return Comparator.comparing(Person::getFirstName, PolishCollator).thenComparing(Person::getSurname, PolishCollator).thenComparing(Person::getBirthDate).compare(this,person);
    }

    @Override
    public String toString() {
        return "Person{" +
                "pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", nationality=" + nationality +
                '}';
    }
}
