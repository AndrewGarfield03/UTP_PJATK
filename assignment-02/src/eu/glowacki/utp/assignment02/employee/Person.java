package eu.glowacki.utp.assignment02.employee;


import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public abstract class Person {

    // To implement an attribute means that you provide a backing field and
    // getter or optionally setter for read-write properties/attributes
    //
    // NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
    // THOSE SHOULD BE COMPUTED ON-LINE
    //
    // attributes:
    // * first name (read-only)
    // * surname (read-only)
    // * birth date (read-only) --- date MUST BE represented by an instance of
    // the type designed for date representation (either Date or LocalDate)
    //
    // * age (derived --- computed based on birth date) --- implemented as a
    // getter calculating the difference between the current date and birth date

    private final String _firstName;
    private final String _lastName;
    private final LocalDate _birthDate;

    protected Person(String firstName, String lastName, LocalDate birthDate) {
        _firstName = firstName;
        _lastName = lastName;
        _birthDate = birthDate;
    }

    public String getFirstName() { // getter
        return _firstName;
    }

    public int getAge() {
        int age = Period.between(_birthDate, LocalDate.now()).getYears();
        return age;
    }

    public LocalDate getBirthDate() {
        return _birthDate;
    }

    public String getLastName() {
        return _lastName;
    }

    private int comapreAge(Person person) {
        return _birthDate.compareTo(person._birthDate);
    }

    public boolean isOlder(Person person) {
        return comapreAge(person) > 0;
    }

    public boolean isYounger(Person person) {
        return comapreAge(person) < 0;
    }
}