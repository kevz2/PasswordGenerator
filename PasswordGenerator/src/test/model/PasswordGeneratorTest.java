package model;

import model.exceptions.LengthNotEneteredException;
import model.exceptions.NegativeLengthException;
import model.exceptions.NoAllowedCharacterException;
import model.exceptions.TooLongLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordGeneratorTest {

    public static final List<String> LOWER_CASE_LETTERS = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
                add("f");
                add("g");
                add("h");
                add("i");
                add("j");
                add("k");
                add("l");
                add("m");
                add("n");
                add("o");
                add("p");
                add("q");
                add("r");
                add("s");
                add("t");
                add("u");
                add("v");
                add("w");
                add("x");
                add("y");
                add("z");
            }});

    public static final List<String> UPPER_CASE_LETTERS = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("A");
                add("B");
                add("C");
                add("D");
                add("E");
                add("F");
                add("G");
                add("H");
                add("I");
                add("J");
                add("K");
                add("L");
                add("M");
                add("N");
                add("O");
                add("P");
                add("Q");
                add("R");
                add("S");
                add("T");
                add("U");
                add("V");
                add("W");
                add("X");
                add("Y");
                add("Z");
            }});

    public static final List<String> NUMBERS = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("0");
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
                add("7");
                add("8");
                add("9");
            }});

    public static final List<String> SPECIAL_SYMBOLS = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("~");
                add("`");
                add("!");
                add("@");
                add("#");
                add("$");
                add("%");
                add("^");
                add("&");
                add("*");
                add("(");
                add(")");
                add("_");
                add("-");
                add("+");
                add("=");
                add("{");
                add("[");
                add("}");
                add("]");
                add("|");
                add(":");
                add(";");
                add("'");
                add("<");
                add(",");
                add(">");
                add(".");
                add("?");
                add("/");
            }});

    private PasswordGenerator generator;

    @BeforeEach
    public void runBefore() {
        generator = new PasswordGenerator();
    }

    @Test
    public void testSetPasswordLengthNonZero() {
        assertEquals(0, generator.getPasswordLength());
        try {
            generator.setPasswordLength(1);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(1, generator.getPasswordLength());
    }

    @Test
    public void testSetPasswordLengthZero() {
        assertEquals(0, generator.getPasswordLength());
        try {
            generator.setPasswordLength(0);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(0, generator.getPasswordLength());
    }

    @Test
    public void testSetPasswordLengthNegative() {
        assertEquals(0, generator.getPasswordLength());
        try {
            generator.setPasswordLength(-1);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(0, generator.getPasswordLength());
    }

    @Test
    public void testGenerateAllTypes() {
        try {
            generator.setPasswordLength(16);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(16, generator.getPasswordLength());
        generator.enableLowerCaseLetters();
        assertTrue(generator.getAllowLowerCaseLetters());
        generator.enableUpperCaseLetters();
        assertTrue(generator.getAllowUpperCaseLetters());
        generator.enableNumbers();
        assertTrue(generator.getAllowNumbers());
        generator.enableSpecialSymbols();
        assertTrue(generator.getSpecialSymbols());
        generator.finalizeAllowedCharacters();
        List<String> array = new ArrayList<String>();
        for (String s1 : LOWER_CASE_LETTERS) {
            array.add(s1);
        }
        for (String s2 : UPPER_CASE_LETTERS) {
            array.add(s2);
        }
        for (String s3 : NUMBERS) {
            array.add(s3);
        }
        for (String s4 : SPECIAL_SYMBOLS) {
            array.add(s4);
        }
        assertEquals(array, generator.getAllowedCharacters());
        String password = "";
        try {
            password = generator.generate();
        } catch (NoAllowedCharacterException e) {
            System.out.println("No allowed characters to generate password!");
        }
        assertEquals(16, password.toCharArray().length);

    }

    @Test
    public void testGenerateThreeTypes() {
        try {
            generator.setPasswordLength(19);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(19, generator.getPasswordLength());

        generator.enableLowerCaseLetters();
        assertTrue(generator.getAllowLowerCaseLetters());
        generator.enableUpperCaseLetters();
        assertTrue(generator.getAllowUpperCaseLetters());
        generator.enableNumbers();
        assertTrue(generator.getAllowNumbers());
        generator.enableSpecialSymbols();
        generator.disableSpecialSymbols();
        assertTrue(!generator.getSpecialSymbols());
        generator.finalizeAllowedCharacters();

        List<String> array = new ArrayList<String>();
        for (String s1 : LOWER_CASE_LETTERS) {
            array.add(s1);
        }
        for (String s2 : UPPER_CASE_LETTERS) {
            array.add(s2);
        }
        for (String s3 : NUMBERS) {
            array.add(s3);
        }
        assertEquals(array, generator.getAllowedCharacters());
        String password = "";
        try {
            password = generator.generate();
        } catch (NoAllowedCharacterException e) {
            System.out.println("No allowed characters to generate password!");
        }
        assertEquals(19, password.toCharArray().length);

    }

    @Test
    public void testGenerateTwoTypes() {
        try {
            generator.setPasswordLength(50);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(50, generator.getPasswordLength());

        generator.enableLowerCaseLetters();
        assertTrue(generator.getAllowLowerCaseLetters());
        generator.enableUpperCaseLetters();
        generator.disableUpperCaseLetters();
        assertTrue(!generator.getAllowUpperCaseLetters());
        generator.enableNumbers();
        assertTrue(generator.getAllowNumbers());
        generator.enableSpecialSymbols();
        generator.disableSpecialSymbols();
        assertTrue(!generator.getSpecialSymbols());
        generator.finalizeAllowedCharacters();

        List<String> array = new ArrayList<String>();
        for (String s1 : LOWER_CASE_LETTERS) {
            array.add(s1);
        }
        for (String s2 : NUMBERS) {
            array.add(s2);
        }
        assertEquals(array, generator.getAllowedCharacters());
        String password = "";
        try {
            password = generator.generate();
        } catch (NoAllowedCharacterException e) {
            System.out.println("No allowed characters to generate password!");
        }
        assertEquals(50, password.toCharArray().length);

    }

    @Test
    public void testGenerateOneType() {
        try {
            generator.setPasswordLength(10);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(10, generator.getPasswordLength());

        generator.enableLowerCaseLetters();
        generator.disableLowerCaseLetters();
        assertTrue(!generator.getAllowLowerCaseLetters());
        generator.enableUpperCaseLetters();
        generator.disableUpperCaseLetters();
        assertTrue(!generator.getAllowUpperCaseLetters());
        generator.enableNumbers();
        assertTrue(generator.getAllowNumbers());
        generator.enableSpecialSymbols();
        generator.disableSpecialSymbols();
        assertTrue(!generator.getSpecialSymbols());
        generator.finalizeAllowedCharacters();

        List<String> array = new ArrayList<String>();
        for (String s1 : NUMBERS) {
            array.add(s1);
        }
        assertEquals(array, generator.getAllowedCharacters());
        String password = "";
        try {
            password = generator.generate();
        } catch (NoAllowedCharacterException e) {
            System.out.println("No allowed characters to generate password!");
        }
        assertEquals(10, password.toCharArray().length);

    }

    @Test
    public void testGenerateNoTypePasswordLengthZero() {
        try {
            generator.setPasswordLength(0);
        } catch (LengthNotEneteredException e1) {
            System.out.printf("Length not entered!");
        } catch (NegativeLengthException e2) {
            System.out.println("Negative password length not allowed!");
        } catch (TooLongLengthException e3) {
            System.out.println("Password length is over 100!");
        }
        assertEquals(0, generator.getPasswordLength());

        generator.enableLowerCaseLetters();
        generator.disableLowerCaseLetters();
        assertTrue(!generator.getAllowLowerCaseLetters());
        generator.enableUpperCaseLetters();
        generator.disableUpperCaseLetters();
        assertTrue(!generator.getAllowUpperCaseLetters());
        generator.enableNumbers();
        generator.disableNumbers();
        assertTrue(!generator.getAllowNumbers());
        generator.enableSpecialSymbols();
        generator.disableSpecialSymbols();
        assertTrue(!generator.getSpecialSymbols());
        generator.finalizeAllowedCharacters();

        List<String> array = new ArrayList<String>();
        assertEquals(array, generator.getAllowedCharacters());
        String password = "";
        try {
            password = generator.generate();
        } catch (NoAllowedCharacterException e) {
            System.out.println("No allowed characters to generate password!");
        }
        assertEquals(0, password.toCharArray().length);

    }

}
