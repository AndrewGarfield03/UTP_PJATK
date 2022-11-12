package com.company;

import java.util.Random;

public class PeselGenerator {

    public static String year() {
        Random rand = new Random();
        int minYear = 1800;
        int maxYear = 2299;
        int randomYear = rand.nextInt(maxYear - minYear + 1) + minYear;
        String year = Integer.toString(randomYear);
        //System.out.println(year);
        return (year);
    }

    public static String month() {
        Random rand = new Random();
        int minMonth = 1;
        int maxMonth = 12;
        int randomMonth = rand.nextInt(maxMonth - minMonth + 1) + minMonth;
        if(randomMonth < 10){
            String month = "0" + randomMonth;
            //System.out.println(month);
            return (month);
        }else {
            String month = Integer.toString(randomMonth);
            //System.out.println(month);
            return (month);
        }
    }

    public static String day() {
        Random rand = new Random();
        int minDay = 1;
        int maxDay = 28;
        int randomDay = rand.nextInt(maxDay - minDay + 1) + minDay;
        if(randomDay < 10){
            String day = "0" + randomDay;
            //System.out.println(day);
            return (day);
        }else {
            String day = Integer.toString(randomDay);
            //System.out.println(day);
            return (day);
        }
    }

    public static String fourDigits() {
        Random rand = new Random();
        int a = rand.nextInt(10000);
        String number = Integer.toString(a);
        int b = number.length();
        if (b == 1){
            number = "000" + number;
        }
        else if (b == 2){
            number = "00" + number;
        }
        else if (b == 3){
            number = "0" + number;
        }
        //System.out.println(number);
        return(number);
    }

    public static String datePart(){
        int year = Integer.parseInt(year());
        int month = Integer.parseInt(month());
        int day = Integer.parseInt(day());
        int firstPart = year % 100;
        int secondPart = 0;
        if(year/100 == 18){
            secondPart = month + 80;
        }else if(year/100 == 19){
            secondPart = month;
        }else if(year/100 == 20){
            secondPart = month + 20;
        } else if(year/100 == 21){
            secondPart = month + 40;
        } else if(year/100 == 22){
            secondPart = month + 60;
        }
        String strFirstPart = String.valueOf(firstPart);
        if(firstPart < 10) {
            strFirstPart = "0" + firstPart;
        }
        String strSecondPart = String.valueOf(secondPart);
        if(secondPart < 10) {
            strSecondPart = "0" + secondPart;
        }
        String strThirdPart = String.valueOf(day);
        if(day < 10) {
            strThirdPart = "0" + day;
        }
        //System.out.println(strFirstPart + " " +  strSecondPart + " " + strThirdPart);
        return strFirstPart + strSecondPart  + strThirdPart;
    }

    public static int controlDigit(String previousDigits){
        int sum = 0;
        int[] factors= new int[10];
        factors[0]=1;
        factors[1]=3;
        factors[2]=7;
        factors[3]=9;
        factors[4]=1;
        factors[5]=3;
        factors[6]=7;
        factors[7]=9;
        factors[8]=1;
        factors[9]=3;

        for (int i = 0; i < factors.length; i++){
            int digit = Character.getNumericValue(previousDigits.charAt(i));
            sum += digit * factors[i];
        }
        //System.out.println("sum:"+sum);
        int modulo = sum % 10;

        //System.out.println("modulo:" + modulo);
        if(modulo != 0){
            int num = 10 - modulo;
            return num;
        }else{
            int num = 0;
            return num;
        }
    }

    public static String generatePesel(){
        String datePart = datePart();
        String fourDigits = fourDigits();
        String without = datePart + fourDigits;
        int control = controlDigit(without);
        String pesel = without + control;
        return pesel;
    }


}
