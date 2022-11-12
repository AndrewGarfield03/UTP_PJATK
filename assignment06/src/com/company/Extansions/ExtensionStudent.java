package com.company.Extansions;
import com.company.Student;

import java.util.*;

public class ExtensionStudent {
    private static Set<Student> studentSet;
    private static List<Student> studentList;

    static {
        studentSet = new HashSet<>();
        studentList = new ArrayList<>();
    }

    public static void add(Student student){
        studentSet.add(student);
        studentList.add(student);
        studentList.sort(Comparator.naturalOrder());
    }

    public static Student randomStudent() {
        return studentList.get(new Random().nextInt(studentList.size()));
    }
}
