package com.company.assignment08.comparators;

import com.company.assignment08.Person;

import java.util.Comparator;

public final class BirthdateComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {

		return person1.getBirthDate().compareTo(person2.getBirthDate());

	}
}