package com.company.assignment08.test;

import com.company.assignment08.Assignment08Exception;
import com.company.assignment08.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonTest {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	private Person person;
	private FileOutputStream fileOutputStream;
	private DataOutputStream dataOutputStream;
	private FileInputStream fileInputStream;
	DataInputStream dataInputStream;

	@Before
	public void setUp() throws FileNotFoundException, ParseException {

		this.person = new Person("John", "Smith", this.dateFormat.parse("1990-01-03"));

		this.fileOutputStream = new FileOutputStream("src/com/company/assignment08/test_person.bin");
		this.dataOutputStream = new DataOutputStream(this.fileOutputStream);
		this.fileInputStream = new FileInputStream("src/com/company/assignment08/test_person.bin");
		this.dataInputStream = new DataInputStream(this.fileInputStream);
	}

	@Test
	public void serializeAndDeserialize() throws Assignment08Exception {
		this.person.serialize(this.dataOutputStream);
		Person resultPerson = Person.deserialize(this.dataInputStream);
		Assert.assertEquals(resultPerson.getFirstName(), this.person.getFirstName());
		Assert.assertEquals(resultPerson.getSurname(), this.person.getSurname());
		Assert.assertEquals(resultPerson.getBirthDate(), this.person.getBirthDate());
	}
}