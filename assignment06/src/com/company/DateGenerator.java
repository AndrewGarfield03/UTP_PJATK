package com.company;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DateGenerator{
    private static long TODAY = new Date().getTime();
    private static long MILLSECINDAY = TimeUnit.DAYS.toMillis(1);
    public static final Date MAXDATEVALUE =  new Date(TODAY - MILLSECINDAY * 365 * 65);
    public static final Date MINDATEVALUE =  new Date(TODAY - MILLSECINDAY * 365 * 18);

    private static Random Random = new Random();

    public static Date generateBirthDate(){
        long startMillis = MAXDATEVALUE.getTime();
        long endMillis = MINDATEVALUE.getTime();
        long randomMillis = Random.nextLong(startMillis, endMillis);

        return new Date(randomMillis);
    }

}
