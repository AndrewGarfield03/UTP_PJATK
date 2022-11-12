package com.company.assignment04;

import com.company.assignment04.comparators.BirthdateComparator;
import com.company.assignment04.comparators.FirstNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private List<Person> inputArray;

	public PersonDatabase(List<Person> data) {
		this.inputArray = data;
	}

	public List<Person> sortedByFirstName() {
		// external rule for ordering (based on Comparator --- FirstNameComparator)
		Comparator<Person> firstNameComparator = new FirstNameComparator();
		List<Person> sortedByFirstNameList = new ArrayList<>();
		sortedByFirstNameList.addAll(this.inputArray);
		sortedByFirstNameList.sort(firstNameComparator);

		return sortedByFirstNameList;
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		// natural order (Comparable)
		List<Person> sortedList = new ArrayList<>();
		sortedList.addAll(this.inputArray);
		sortedList.sort(Comparator.naturalOrder());

		return sortedList;
	}
	
	public List<Person> sortedByBirthdate() {
		// external rule for ordering (based on Comparator --- BirthdateComparator)
		Comparator<Person> birthdateComparator = new BirthdateComparator();
		List<Person> sortedList = new ArrayList<>();
		sortedList.addAll(this.inputArray);
		sortedList.sort(birthdateComparator);

		return sortedList;
	}
	
	public List<Person> bornOnDay(Date date) {
		List<Person> sortedByFirstNameList = sortedByFirstName();

		Map<Date, List<Person>> groupingByDate = sortedByFirstNameList
				.stream()
				.collect(Collectors.groupingBy(Person::getBirthdate, TreeMap::new,
				Collectors.mapping(p -> p, Collectors.toList())));
		return groupingByDate.get(date);
	}
}