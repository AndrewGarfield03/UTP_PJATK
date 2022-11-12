package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DateGeneratorTest {

    @Test
    public void birthDate(){
        Date birthDate = DateGenerator.generateBirthDate();
        System.out.println("Random birth date: " + birthDate);

        Assert.assertTrue(birthDate.getTime() <= DateGenerator.MINDATEVALUE.getTime() && birthDate.getTime()  >= DateGenerator.MAXDATEVALUE.getTime());
    }
}
