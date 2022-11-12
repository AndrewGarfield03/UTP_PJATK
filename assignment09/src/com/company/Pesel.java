package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pesel {

    private static int[] factors = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
    private final String value;
    private final Date birthdate;
    private final Sex sex;

    private static final String digit_Pattern = "[0-9]";
    private static final String two_digit_pattern = digit_Pattern + "{2}";
    private static final String begin = "^";
    private static final String end = "$";
    private static final String PESEL_PATTERN = begin
            + "(" + two_digit_pattern + ")"
            + "(" + two_digit_pattern + ")"
            + "(" + two_digit_pattern + ")"
            + digit_Pattern + "{3}"
            + "(" + digit_Pattern + ")"
            + digit_Pattern + end;
    private static final Pattern wholePattern = Pattern.compile(PESEL_PATTERN);


    private static String year(Date date) {
        String s = String.valueOf(date.getYear());
        if (date.getYear() > 99) s = s.substring(1);
        return s;
    }

    private static String month(Date date) {
        String s = String.valueOf(date.getMonth());
        if (s.length() > 1) return s;
        else return "0" + s;
    }

    private static String day(Date date) {
        String s = String.valueOf(date.getDate());
        if (s.length() > 1) return s;
        else return "0" + s;
    }

    private static String fourDigits() {
        Random rand = new Random();
        int a = rand.nextInt(10000);
        String number = Integer.toString(a);
        int b = number.length();
        if (b == 1) {
            number = "000" + number;
        } else if (b == 2) {
            number = "00" + number;
        } else if (b == 3) {
            number = "0" + number;
        }
        return number;
    }

    private static String datePart(Date birthDate) {
        String year = String.valueOf(year(birthDate));
        String month = String.valueOf(month(birthDate));
        String day = String.valueOf(day(birthDate));
        return year + month + day;
    }

    private static int controlDigit(String previousDigits) {
        int sum = 0;

        int modulo;
        int num;
        for (modulo = 0; modulo < factors.length - 1; ++modulo) {
            num = Character.getNumericValue(previousDigits.charAt(modulo));
            sum += num * factors[modulo];
        }
        modulo = sum % 10;
        if (modulo != 0) {
            num = 10 - modulo;
            return num;
        } else {
            num = 0;
            return num;
        }
    }

    private static String generatePesel(Date birthDate) {
        String datePart = datePart(birthDate);
        String fourDigits = fourDigits();
        String without = datePart + fourDigits;
        int control = controlDigit(without);
        String pesel = without + control;
        return pesel;
    }

    public static boolean checkingControlDigit(String value) {
        int sum = 0;
        for (int i = 0; i < value.length(); i++) {
            sum += Integer.parseInt(String.valueOf(value.charAt(i))) * factors[i];
        }
        return sum % 10 == 0;
    }

    public static boolean validate(String input, boolean exception) throws Exception {
        Matcher matcher = wholePattern.matcher(input);
        boolean matches = matcher.matches();
        if (!matches) {
            if (!exception) {
                return false;
            }
            throw new Exception("PESEL WRONG");
        }
        boolean result = checkingControlDigit(input);
        if (!result && exception) {
            throw new Exception("Invalid Checksum");
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        Pesel p1 = new Pesel("84011201654");
        String p = Pesel.generatePesel(new Date(89, 1, 1));
        System.out.println(p);
        System.out.println(Pesel.validate("84011201654", false));
    }

    public Pesel(String value) throws Exception {
        validate(value, true);
        this.value = value;
        this.birthdate = dateChecker(value, true);
        this.sex = sexParser(value);
    }

    private static Date dateChecker(String s, boolean exception) throws Exception {
        Matcher matcher = wholePattern.matcher(s);
        boolean matches = matcher.matches();
        if (!matches) {
            if (exception) {
                throw new Exception("Date input is incorrect");
            } else {
                return null;
            }
        }
        int twoDigitsOfYear = Integer.parseInt(matcher.group(1));
        int m = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(3));
        int month = m % 20;
        int century = m / 20;
        int year = 0;
        if (century == 0) year = 1900 + twoDigitsOfYear;
        if (century == 1) year = 2000 + twoDigitsOfYear;
        if (century == 2) year = 2100 + twoDigitsOfYear;
        if (century == 3) year = 2200 + twoDigitsOfYear;
        if (century == 4) year = 1800 + twoDigitsOfYear;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static Sex sexParser(String s) {
        return Integer.parseInt(String.valueOf(s.charAt(6))) % 2 == 0 ? Sex.Female : Sex.Male;
    }

    public static Date dateGenerate() {
        long TODAY = new Date().getTime();
        long MILLSECINDAY = TimeUnit.DAYS.toMillis(1);
        Date MAXDATEVALUE = new Date(TODAY - MILLSECINDAY * 365 * 100);
        Date MINDATEVALUE = new Date(TODAY - MILLSECINDAY * 365 * 20);
        Random Random = new Random();
        long startMillis = MAXDATEVALUE.getTime();
        long endMillis = MINDATEVALUE.getTime();
        long randomMillis = Random.nextLong(startMillis, endMillis);
        return new Date(randomMillis);
    }
}
