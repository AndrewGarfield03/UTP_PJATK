package com.company;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class PeselTest {
    private static final Date[] validBirthDates = {new Date(89,1,1)};
    private static final String[] validInput = {"89010175399"};
    private static final String[] inValidInput = {"89010175391"};

    private static Method validateMethod;
    private static Method validateChecksumMethod;
    private static Method getBirthDateChecker;
    private static Method getSex;

    static{
        try{
            validateMethod= Pesel.class.getDeclaredMethod("validate", String.class, boolean.class);
            validateMethod.setAccessible(true);

            validateChecksumMethod= Pesel.class.getDeclaredMethod("checkingControlDigit", String.class);
            validateChecksumMethod.setAccessible(true);

            getBirthDateChecker=Pesel.class.getDeclaredMethod("dateChecker", String.class, boolean.class);
            getBirthDateChecker.setAccessible(true);

            getSex=Pesel.class.getDeclaredMethod("sexParser", String.class);
            getSex.setAccessible(true);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validate() throws InvocationTargetException, IllegalAccessException {
        for (String validPesel : validInput){
            Assert.assertTrue((boolean) validateMethod.invoke(null,validPesel,false));
        }
        for (String invalidPesel :inValidInput){
            Assert.assertFalse((boolean) validateMethod.invoke(null,invalidPesel,false));
        }
    }

    @Test
    public void validateCheckSum() throws InvocationTargetException, IllegalAccessException {
        for (String validPesel : validInput){
            Assert.assertTrue((boolean) validateChecksumMethod.invoke(null,validPesel));
        }
        for (String invalidPesel :inValidInput){
            Assert.assertFalse((boolean) validateChecksumMethod.invoke(null,invalidPesel));
        }
    }

    @Test
    public void dateCheck() throws InvocationTargetException, IllegalAccessException {
        try{
            for(int i =0;i<validInput.length;i++){
                String pesel = validInput[i];
                Date date = validBirthDates[i];
                Assert.assertEquals(date,getBirthDateChecker.invoke(null,pesel,false));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sexCheck() throws InvocationTargetException, IllegalAccessException {
        try{
            for (String validPesel : validInput){
                Assert.assertEquals(Sex.Male,getSex.invoke(null,validPesel));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
