package com.company.assignment04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputParser {
	
	// 1. Use regular expresssions (Pattern) for validating input data
	//    U�y� regularnych wyra�e� (Pattern) do walidacji danych wej�ciowych
	//
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd" 
	//    Konwersj� wej�ciowego ci�gu znak�w reprezentuj�cego dat� nale�y oprze� np. DateFormat 
	//    SimpleDateFormat format "yyyy-MM-dd"

	private static final DateFormat dateFormatPattern = new SimpleDateFormat("yyyy-mm-dd");
	private static final String inputLinePattern = "\\w+\\s\\w+\\s[0-9]{4}\\-[0-1][0-9]\\-[0-3][0-9]";

	public static List<Person> parse(File file) throws IOException {

		List<Person> persons = new ArrayList<>();

		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = bf.readLine()) != null) {
				//System.out.println(line);

				if (!isInputLineInCorrectFormat(line))
					continue;

				String[] arr = line.split(" ");
				if (arr.length < 3)
					continue;

				String firstName = arr[0];
				String surname = arr[1];
				Date birthdate = null;
				try {
					birthdate = dateFormatPattern.parse(arr[2]);
				} catch (ParseException e) {
					continue;
				}

				persons.add(new Person(firstName, surname, birthdate));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return persons;
	}

	private static boolean isInputLineInCorrectFormat(String line) {

		return Pattern.matches(inputLinePattern, line);
	}
}