package com.company;

import com.company.Extansions.ExtensionDepartment;
import com.company.Extansions.ExtensionStudent;
import com.company.Extansions.ExtensionTeacher;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class GeneratorTest {
    private static final int studentCount = 100;
    private static final int teachersCount = 10;
    private static final int departmentsCount = 3;
    private static final int studentGroupsCount = 12;
    private static final int subjectCount = 10;

    @Test
    public void testAll(){
        generateStudents();
        generateTeachers();
        generateDepartments();
        generateStudentGroups();
        generateSubjects();
    }

    private static void generateStudents(){
        System.out.println("All students: ");
        for (int i = 0; i < studentCount; i++) {
            Date birthDate = DateGenerator.generateBirthDate();
            String pesel = PeselGenerator.generatePesel();
            String name = "Student_Name_" + i;
            String surname = "Student_SurName_" + i;
            String studentBookNum = String.valueOf(i);
            Student student = new Student(pesel, name, surname, birthDate, Nationality.random(), studentBookNum);
            System.out.println(student);
        }
    }

    private static void generateTeachers(){
        System.out.println("All teachers: ");
        for (int i = 0; i < teachersCount; i++) {
            Date birthDate = DateGenerator.generateBirthDate();
            String pesel = PeselGenerator.generatePesel();
            String name = "Teacher_Name_" + i;
            String surname = "Teacher_SurName_" + i;
            Teacher teacher = new Teacher(pesel, name, surname, birthDate, Nationality.random(), DegreeGenerator.generateDegree());
            System.out.println(teacher);
        }
    }

    private static void generateDepartments(){
        System.out.println("All departments: ");
        for(int i = 1; i <= departmentsCount; i++){
            String name = "Department_" + i;
            Teacher[] employees = randomTeachers();
            Department department = new Department(name, List.of(employees));
            System.out.println(department);
        }
    }

    private static void generateStudentGroups(){
        System.out.println("All Student Groups: ");
        int studentsMin = 8;
        int studentsMax = 10;

        for(int i = 1; i <= studentGroupsCount; i++){
            String name = "Student_Group_" + i;
            Student[] students = randomStudents(studentsMin, studentsMax);
            StudentGroup studentGroup = new StudentGroup(name, List.of(students));
            System.out.println(studentGroup);
        }
    }

    private static void generateSubjects(){
        System.out.println("All Subjects: ");
        int studentsMin = 12;
        int studentsMax = 24;

        for(int i = 1; i <= subjectCount; i++){
            String name = "Subject_" + i;
            Department department = ExtensionDepartment.randomDepartment();
            Teacher lecturer = ExtensionTeacher.randomTeacher();
            Student[] students = randomStudents(studentsMin, studentsMax);
            Subject subject = new Subject(name, department, lecturer, List.of(students));
            System.out.println(subject);
        }
    }

    private static Teacher[] randomTeachers(){
        Teacher[] teachers = new Teacher[15];
        for (int i = 0; i < teachers.length; i++) {
            teachers[i] = ExtensionTeacher.randomTeacher();
        }
        return teachers;
    }

    private static Student[] randomStudents(int minStudents, int maxStudents){
        int countOfStudents = (int)((Math.random()*(maxStudents - minStudents)) + minStudents);
        Student[] students = new Student[countOfStudents];
        for (int i = 0; i < students.length; i++) {
            students[i] = ExtensionStudent.randomStudent();
        }
        return students;
    }
}
