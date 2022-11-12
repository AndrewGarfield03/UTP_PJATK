package com.company.assignment04.test;

import com.company.assignment04.InputParser;
import com.company.assignment04.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public final class InputParserTest {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    private static File inputFile;

    List<Person> person;

    @Before
    public void Before() {
        this.inputFile = new File("src/com/company/assignment04/persons.txt");
        Assert.assertTrue(inputFile.exists());
    }

    @Test
    public void testParse() throws Exception {

        this.person = InputParser.parse(inputFile);

        Assert.assertEquals("John", this.person.get(0).getFirstName());
        Assert.assertEquals("Smith", this.person.get(0).getSurname() );
        Assert.assertEquals("1990-01-03", this.dateFormat.format(this.person.get(0).getBirthdate()));
    }

    @Test
    public void parse() throws Exception {
        InputParser.parse(inputFile);
        Assert.assertNotNull(InputParser.parse(inputFile));

    }

    @Test
    public void sizeTest() throws Exception {

        this.person = InputParser.parse(inputFile);
        Assert.assertEquals(7, this.person.size());
    }

}