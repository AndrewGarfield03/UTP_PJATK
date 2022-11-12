package com.company.Extansions;

import com.company.Subject;

import java.util.*;

public class ExtensionSubject {
    private static Set<Subject> subjectSet;
    private static List<Subject> studentList;

    static {
        subjectSet = new HashSet<>();
        studentList = new ArrayList<>();
    }

    public static void add(Subject subject){
        subjectSet.add(subject);
        studentList.add(subject);
        studentList.sort(Comparator.naturalOrder());
    }
}
