package com.company.assignment08;

import com.company.assignment08.comparators.BirthdateComparator;
import com.company.assignment08.comparators.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private List<Person> persons;
	public List<Person> getPersons() {
		return this.persons;
	}

	public PersonDatabase(List<Person> persons) {
		this.persons = persons;
	}

	// assignment 8 - factory method based on deserialization
	public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
		List<Person> resultPersons = new ArrayList<>();
		try {
			while (input.available() > 0) {
				resultPersons.add(Person.deserialize(input));
			}
		}
		catch (IOException ex)
		{
			throw new Assignment08Exception(ex.getMessage(), ex);
		}
		return new PersonDatabase(resultPersons);
	}

	// assignment 8
	public void serialize(DataOutputStream output) {
		this.persons.stream().forEach(p -> {
			try {
				p.serialize(output);
			} catch (Assignment08Exception e) {
				e.printStackTrace();
			}
		});
	}

	// assignment 4
	public List<Person> sortedByFirstName() {
		// external rule for ordering (based on Comparator --- FirstNameComparator)
		Comparator<Person> firstNameComparator = new FirstNameComparator();
		List<Person> sortedByFirstNameList = new ArrayList<>();
		sortedByFirstNameList.addAll(this.persons);
		sortedByFirstNameList.sort(firstNameComparator);

		return sortedByFirstNameList;
	}

	// assignment 4
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		// natural order (Comparable)
		List<Person> sortedList = new ArrayList<>();
		sortedList.addAll(this.persons);
		sortedList.sort(Comparator.naturalOrder());

		return sortedList;
	}

	// assignment 4
	public List<Person> sortedByBirthdate() {
		// external rule for ordering (based on Comparator --- BirthdateComparator)
		Comparator<Person> birthdateComparator = new BirthdateComparator();
		List<Person> sortedList = new ArrayList<>();
		sortedList.addAll(this.persons);
		sortedList.sort(birthdateComparator);

		return sortedList;
	}

	// assignment 4 usage
	public List<Person> bornOnDay(Date date) {
		List<Person> sortedByFirstNameList = sortedByFirstName();

		Map<Date, List<Person>> groupingByDate = sortedByFirstNameList
				.stream()
				.collect(Collectors.groupingBy(Person::getBirthDate, TreeMap::new,
						Collectors.mapping(p -> p, Collectors.toList())));
		return groupingByDate.get(date);
	}
}