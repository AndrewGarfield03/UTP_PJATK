package com.company.assignment04.test;

import com.company.assignment04.InputParser;
import com.company.assignment04.Person;
import com.company.assignment04.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PersonDatabaseTest {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    private static File inputFile;
    PersonDatabase personDatabase;

    @Before
    public void setUp() throws Exception {
        inputFile = new File("src/com/company/assignment04/persons.txt");
        List<Person> data = InputParser.parse(inputFile);
        this.personDatabase = new PersonDatabase(data);
    }

    @Test
    public void sortedByFirstNameTest() {

        List<Person> sortedByFirstNameList = this.personDatabase.sortedByFirstName();

        Assert.assertEquals("Amit", sortedByFirstNameList.get(0).getFirstName());
        System.out.println(sortedByFirstNameList.toString());
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthdateTest() {

        List<Person> sortedList = this.personDatabase.sortedBySurnameFirstNameAndBirthdate();

        System.out.println(sortedList.toString());
        Assert.assertEquals("Adams", sortedList.get(0).getSurname());
        Assert.assertEquals("1990-01-01", this.dateFormat.format(sortedList.get(0).getBirthdate()));
        Assert.assertEquals("Adams", sortedList.get(1).getSurname());
        Assert.assertEquals("1989-01-10", this.dateFormat.format(sortedList.get(1).getBirthdate()));
    }

    @Test
    public void sortedByBirthdateTest() {

        List<Person> sortedList = this.personDatabase.sortedByBirthdate();

        System.out.println(sortedList.toString());
        Assert.assertEquals("John", sortedList.get(0).getFirstName());
        Assert.assertEquals("Doe", sortedList.get(0).getSurname());
        Assert.assertEquals("1970-03-03", this.dateFormat.format(sortedList.get(0).getBirthdate()));
    }

    @Test
    public void bornOnDayTest()  throws Exception {

        List<Person> resultList = this.personDatabase.bornOnDay(this.dateFormat.parse("1989-01-10"));

        System.out.println(resultList.toString());
        Assert.assertEquals( 2, resultList.size());

        Assert.assertEquals("Amit", resultList.get(0).getFirstName());
        Assert.assertEquals("Mark", resultList.get(1).getFirstName());
        Assert.assertEquals("1989-01-10", this.dateFormat.format(resultList.get(0).getBirthdate()));
        Assert.assertEquals("1989-01-10", this.dateFormat.format(resultList.get(1).getBirthdate()));
    }

}