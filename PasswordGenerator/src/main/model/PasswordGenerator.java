package model;

import model.exceptions.LengthNotEneteredException;
import model.exceptions.NegativeLengthException;
import model.exceptions.NoAllowedCharacterException;
import model.exceptions.TooLongLengthException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
Represents a password generator
 */

public class PasswordGenerator {

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


    private boolean allowLowerCaseLetters;
    private boolean allowUpperCaseLetters;
    private boolean allowNumbers;
    private boolean allowSpecialSymbols;
    private List<String> allowedCharacters;
    private int length;
    private String password;


    // MODIFIES: this
    // EFFECTS: constructs a new password generator
    public PasswordGenerator() {
        this.allowLowerCaseLetters = false;
        this.allowUpperCaseLetters = false;
        this.allowNumbers = false;
        this.allowSpecialSymbols = false;
        allowedCharacters = new ArrayList<>();
        length = 0;
        password = "";
    }

    // MODIFIES: this
    // EFFECTS: enables lower case letters to be used when generating passwords
    public void enableLowerCaseLetters() {
        allowLowerCaseLetters = true;
    }

    // MODIFIES: this
    // EFFECTS: prevents lower case letters from being used when generating passwords
    public void disableLowerCaseLetters() {
        allowLowerCaseLetters = false;
    }

    // EFFECTS: returns all the allowed lower case letters
    public boolean getAllowLowerCaseLetters() {
        return allowLowerCaseLetters;
    }

    // MODIFIES: this
    // EFFECTS: enables upper case letters to be used when generating passwords
    public void enableUpperCaseLetters() {
        allowUpperCaseLetters = true;
    }

    // MODIFIES: this
    // EFFECTS: prevents upper case letters from being used when generating passwords
    public void disableUpperCaseLetters() {
        allowUpperCaseLetters = false;
    }

    // EFFECTS: returns all the allowed upper case letters
    public boolean getAllowUpperCaseLetters() {
        return allowUpperCaseLetters;
    }

    // MODIFIES: this
    // EFFECTS: enables numbers to be used when generating passwords
    public void enableNumbers() {
        allowNumbers = true;
    }

    // MODIFIES: this
    // EFFECTS: prevents numbers from being used when generating passwords
    public void disableNumbers() {
        allowNumbers = false;
    }

    // EFFECTS: returns all the allowed numbers
    public boolean getAllowNumbers() {
        return allowNumbers;
    }

    // MODIFIES: this
    // EFFECTS: enables special symbols to be used when generating passwords
    public void enableSpecialSymbols() {
        allowSpecialSymbols = true;
    }

    // MODIFIES: this
    // EFFECTS: prevents special symbols from being used when generating passwords
    public void disableSpecialSymbols() {
        allowSpecialSymbols = false;
    }

    // EFFECTS: returns all the allowed special symbols
    public boolean getSpecialSymbols() {
        return allowSpecialSymbols;
    }

    // EFFECTS: returns password length
    public int getPasswordLength() {
        return length;
    }

    // MODIFIES: this
    // EFFECTS: sets the password length to the user input n;
    //          throws LengthNotEnteredException when n is not given;
    //          throws NegativeLengthException when the given n is negative;
    //          throws TooLongLengthException when the given n is over 100
    public void setPasswordLength(int n) throws LengthNotEneteredException, NegativeLengthException,
            TooLongLengthException {
        if (n < 0) {
            throw new NegativeLengthException();
        } else if (n > 100) {
            throw new TooLongLengthException();
        } else {
            length = n;
        }
    }

    // MODIFIES: this
    // EFFECTS: collects all the allowed characters when generating the next password
    public void finalizeAllowedCharacters() {

        allowedCharacters.clear();
        checkLowerCaseLetters();
        checkUpperCaseLetters();
        checkNumbers();
        checkSpecialSymbols();

    }

    // MODIFIES: this
    // EFFECTS: adds all the lower case letters to the list of allowed characters if and only if the user
    //          sets allowLowerCaseLetter to true
    public void checkLowerCaseLetters() {
        if (allowLowerCaseLetters) {
            for (String s : LOWER_CASE_LETTERS) {
                allowedCharacters.add(s);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all the upper case letters to the list of allowed characters if and only if the user
    //          sets allowUpperCaseLetter to true
    public void checkUpperCaseLetters() {
        if (allowUpperCaseLetters) {
            for (String s : UPPER_CASE_LETTERS) {
                allowedCharacters.add(s);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all the numbers to the list of allowed characters if and only if the user
    //          sets allowNumbers to true
    public void checkNumbers() {
        if (allowNumbers) {
            for (String s : NUMBERS) {
                allowedCharacters.add(s);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all the special symbols to the list of allowed characters if and only if the user
    //          sets allowSpecialSymbols to true
    public void checkSpecialSymbols() {
        if (allowSpecialSymbols) {
            for (String s : SPECIAL_SYMBOLS) {
                allowedCharacters.add(s);
            }
        }
    }

    // EFFECTS: returns all the characters that can be used to generate the next password
    public List<String> getAllowedCharacters() {
        return allowedCharacters;
    }

    // EFFECTS: generates the password from the allowed characters with its length specified by
    //          the length from the user;
    //          throws NoAllowedCharacterException when the user wants to generate a password with length greater
    //          than 0 but no characters are allowed to be used
    public String generate() throws NoAllowedCharacterException {
        Random randomNumbers = new SecureRandom();

        StringBuilder password = new StringBuilder();

        if (getAllowedCharacters().isEmpty() && length > 0) {
            throw new NoAllowedCharacterException();
        } else {
            for (int i = 0; i < length; i++) {
                int currentNumber = randomNumbers.nextInt(allowedCharacters.size());
                password.append(allowedCharacters.get(currentNumber));
            }
        }

        return password.toString();
    }
}
