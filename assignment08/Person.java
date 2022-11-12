package com.company.assignment08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Comparable<Person> {
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	private final String firstName;
	private final String surname;
	private final Date birthdate;

	public Person(String firstName, String surname, Date birthdate) {
		this.firstName = firstName;
		this.surname = surname;
		this.birthdate = birthdate;
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception
		try {
			output.writeUTF(this.firstName);
			output.writeUTF(this.surname);
			output.writeLong(this.birthdate.getTime());
		}
		catch (IOException ex)
		{
			throw new Assignment08Exception(ex.getMessage(), ex);
		}

	}
	
	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
		Person person = null;
 		try {
			person = new Person(
			input.readUTF(), input.readUTF(), new Date(input.readLong()));
		}
		catch (IOException ex)
		{
			throw new Assignment08Exception(ex.getMessage(), ex);
		}
		return person;
	}

	@Override
	public int compareTo(Person otherPerson) {

		int result = this.getSurname().compareTo(otherPerson.getSurname());
		if (result != 0)
			return result;

		result = this.getFirstName().compareTo(otherPerson.getSurname());
		if (result != 0)
			return result;

		return this.getBirthDate().compareTo(otherPerson.getBirthDate());

	}

	public String toString() {
		return this.firstName + " " + this.surname + " " + this.dateFormat.format(this.birthdate);
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getSurname() {
		return this.surname;
	}

	public Date getBirthDate() {
		return this.birthdate;
	}
}