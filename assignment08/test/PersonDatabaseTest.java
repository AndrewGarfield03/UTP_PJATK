package com.company.assignment08.test;

import com.company.assignment08.Assignment08Exception;
import com.company.assignment08.Person;
import com.company.assignment08.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonDatabaseTest {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	List<Person> persons;
    private FileOutputStream fileOutputStream;
    private DataOutputStream dataOutputStream;
    private FileInputStream fileInputStream;
    DataInputStream dataInputStream;

    PersonDatabase personDatabase;
	@Before
	public void setUp() throws Exception {
		this.persons = new ArrayList<>();

		this.persons.add(new Person("John", "Smith", this.dateFormat.parse("1990-01-03")));
        this.persons.add(new Person("Mark", "Brown", this.dateFormat.parse("1976-02-02")));
        this.persons.add(new Person("Mark", "Adams", this.dateFormat.parse("1989-01-01")));
        this.persons.add(new Person("Mark", "Adams", this.dateFormat.parse("1989-01-10")));
        this.persons.add(new Person("John", "Doe", this.dateFormat.parse("1970-03-03")));
        this.persons.add(new Person("Simon", "Waters", this.dateFormat.parse("1986-10-10")));
        this.persons.add(new Person("Amit", "Baboo", this.dateFormat.parse("1989-01-10")));

        this.fileOutputStream = new FileOutputStream("src/com/company/assignment08/test_personDatabase.bin");
        this.dataOutputStream = new DataOutputStream(this.fileOutputStream);
        this.fileInputStream = new FileInputStream("src/com/company/assignment08/test_personDatabase.bin");
        this.dataInputStream = new DataInputStream(this.fileInputStream);

        this.personDatabase = new PersonDatabase(this.persons);
	}

	@Test
	public void serializeAndDeserialize() throws Assignment08Exception {
        //serialize and deserialize list of persons and returns new person database instance which contains deserialized list
        PersonDatabase expectedPersonDatabase = serializeAndDeserializePersonDB();

        System.out.println(expectedPersonDatabase.getPersons().toString());
        Assert.assertEquals( 7, expectedPersonDatabase.getPersons().size());

        for (int i = 0; i < expectedPersonDatabase.getPersons().size(); i++) {
            System.out.println(expectedPersonDatabase.getPersons().get(i).toString());

            Assert.assertEquals(expectedPersonDatabase.getPersons().get(i).getFirstName(), this.persons.get(i).getFirstName());
            Assert.assertEquals(expectedPersonDatabase.getPersons().get(i).getSurname(), this.persons.get(i).getSurname());
            Assert.assertEquals(expectedPersonDatabase.getPersons().get(i).getBirthDate(), this.persons.get(i).getBirthDate());
        }
	}

    @Test
    public void sortedByFirstNameTest() throws Assignment08Exception{

        PersonDatabase expectedPersonDatabase = serializeAndDeserializePersonDB();
        List<Person> sortedByFirstNameList = expectedPersonDatabase.sortedByFirstName();

        Assert.assertEquals("Amit", sortedByFirstNameList.get(0).getFirstName());
        System.out.println(sortedByFirstNameList.toString());
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthdateTest() throws Assignment08Exception{
        //serialize and deserialize list of persons and returns new person database instance which contains deserialized list
        PersonDatabase expectedPersonDatabase = serializeAndDeserializePersonDB();
        List<Person> sortedList = expectedPersonDatabase.sortedBySurnameFirstNameAndBirthdate();

        System.out.println(sortedList.toString());
        Assert.assertEquals("Adams", sortedList.get(0).getSurname());
        Assert.assertEquals("1989-01-01", this.dateFormat.format(sortedList.get(0).getBirthDate()));
        Assert.assertEquals("Adams", sortedList.get(1).getSurname());
        Assert.assertEquals("1989-01-10", this.dateFormat.format(sortedList.get(1).getBirthDate()));
    }

    @Test
    public void sortedByBirthdateTest() throws Assignment08Exception{

        PersonDatabase expectedPersonDatabase = serializeAndDeserializePersonDB();
        List<Person> sortedList = expectedPersonDatabase.sortedByBirthdate();

        System.out.println(sortedList.toString());
        Assert.assertEquals("John", sortedList.get(0).getFirstName());
        Assert.assertEquals("Doe", sortedList.get(0).getSurname());
        Assert.assertEquals("1970-03-03", this.dateFormat.format(sortedList.get(0).getBirthDate()));
    }

    @Test
    public void bornOnDayTest() throws Assignment08Exception, ParseException {

        PersonDatabase expectedPersonDatabase = serializeAndDeserializePersonDB();

        List<Person> sortedList = expectedPersonDatabase.bornOnDay(this.dateFormat.parse("1989-01-10"));

        System.out.println(sortedList.toString());
        Assert.assertEquals( 2, sortedList.size());

        Assert.assertEquals("Amit", sortedList.get(0).getFirstName());
        Assert.assertEquals("Mark", sortedList.get(1).getFirstName());
        Assert.assertEquals("1989-01-10", this.dateFormat.format(sortedList.get(0).getBirthDate()));
        Assert.assertEquals("1989-01-10", this.dateFormat.format(sortedList.get(1).getBirthDate()));
    }

	private PersonDatabase serializeAndDeserializePersonDB() throws Assignment08Exception {
        this.personDatabase.serialize(this.dataOutputStream);

        return PersonDatabase.deserialize(this.dataInputStream);
    }
}