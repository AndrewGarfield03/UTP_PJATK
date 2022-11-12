package com.company.Extansions;

import com.company.Teacher;

import java.util.*;

public class ExtensionTeacher {
    private static Set<Teacher> teacherSet;
    private static List<Teacher> teacherList;

    static {
        teacherSet = new HashSet<>();
        teacherList = new ArrayList<>();
    }

    public static void add(Teacher teacher){
        teacherSet.add(teacher);
        teacherList.add(teacher);
        teacherList.sort(Comparator.naturalOrder());
    }

    public static Teacher randomTeacher() {
        return teacherList.get(new Random().nextInt(teacherList.size()));
    }
}
