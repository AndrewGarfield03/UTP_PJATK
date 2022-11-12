package com.company.Extansions;

import com.company.Department;
import com.company.Person;

import java.util.*;

public class ExtensionDepartment {
    private static Set<Department> departmentSet;
    private static List<Department> departmentList;

    static {
        departmentSet = new HashSet<>();
        departmentList = new ArrayList<>();
    }

    public static void add(Department department){
        departmentSet.add(department);
        departmentList.add(department);
        departmentList.sort(Comparator.naturalOrder());
    }

    public static Department randomDepartment() {
        return departmentList.get(new Random().nextInt(departmentList.size()));
    }
}
