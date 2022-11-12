package com.company.Extansions;

import com.company.StudentGroup;

import java.util.*;

public class ExtensionStudentGroup {
    private static Set<StudentGroup> studentGroupSet;
    private static List<StudentGroup> studentGroupList;

    static {
        studentGroupSet = new HashSet<>();
        studentGroupList = new ArrayList<>();
    }

    public static void add(StudentGroup studentGroup){
        studentGroupSet.add(studentGroup);
        studentGroupList.add(studentGroup);
        studentGroupList.sort(Comparator.naturalOrder());
    }
}
