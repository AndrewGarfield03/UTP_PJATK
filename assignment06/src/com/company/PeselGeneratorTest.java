package com.company;

import org.junit.Assert;
import org.junit.Test;

public class PeselGeneratorTest {

    @Test
    public void pesel(){
        String pesel = PeselGenerator.generatePesel();
        System.out.println("Random pesel: " + pesel);
        Assert.assertEquals(11, pesel.length());
    }
}
