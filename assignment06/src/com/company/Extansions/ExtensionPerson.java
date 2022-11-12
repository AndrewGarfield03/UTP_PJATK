package com.company.Extansions;

import com.company.Person;

import java.util.*;

public class ExtensionPerson {
    private static Set<Person> personSet;
    private static List<Person> personList;

    static {
        personSet = new HashSet<>();
        personList = new ArrayList<>();
    }

    public static void add(Person person){
        personSet.add(person);
        personList.add(person);
        personList.sort(Comparator.naturalOrder());
    }

}
