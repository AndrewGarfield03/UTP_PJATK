package com.company;
import java.util.Random;

public class DegreeGenerator {
    public static Degree generateDegree()  {
        return Degree.values()[new Random().nextInt(Degree.values().length)];
    }
}
