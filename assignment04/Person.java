package com.company.assignment04;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Person implements Comparable<Person> {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

	public String getFirstName() {
		return this.firstName;
	}

	public String getSurname() {
		return this.surname;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	private final String firstName;
	private final String surname;
	private final Date birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		this.firstName = firstName;
		this.surname = surname;
		this.birthdate = birthdate;
	}

	@Override
	public int compareTo(Person otherPerson) {

		int result = this.getSurname().compareTo(otherPerson.getSurname());
		if (result != 0)
			return result;

		result = this.getFirstName().compareTo(otherPerson.getSurname());
		if (result != 0)
			return result;

		return this.getBirthdate().compareTo(otherPerson.getBirthdate());

	}

	public String toString() {
		return this.firstName + " " + this.surname + " " + this.dateFormat.format(this.birthdate);
	}
}