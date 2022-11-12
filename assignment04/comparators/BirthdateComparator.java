package com.company.assignment04.comparators;

import java.util.Comparator;

import com.company.assignment04.Person;

public final class BirthdateComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {

		return person1.getBirthdate().compareTo(person2.getBirthdate());

	}
}