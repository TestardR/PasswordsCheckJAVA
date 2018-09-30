package com.romaintestard.PasswordCheck;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PasswordCheck {

    public static void main(String[] args) {
        // Read a file of passwords
        String filename = "C:\\Users\\Romain\\Desktop\\Dev web\\JavaPrograms\\files\\files\\password.txt";
        String[] passwords = new String[13];

        File file = new File(filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (int i = 0; i < passwords.length; i++) {
                passwords[i] = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not open the file");
        } catch (IOException e1) {
            System.out.println("Error: Could not read the file");
        }
        for (String password : passwords){
            System.out.println("****\n" + password + "\n");
            boolean hasNumber = false;
            boolean hasLetter = false;
            boolean hasSpecialChar = false;

            for(int n=0; n < password.length(); n++){
                // Condition 1: contains number
                if ("0123456789".contains(password.substring(n, n+1))) {
                    hasNumber = true;
                   /* System.out.println("Position " + n + " contains a number");*/
                }
                // Condition 2: contains letter
                else if ("abcdefghijklmnopqrstuvwxyz".contains(password.substring(n, n+1).toLowerCase())) {
                    hasLetter = true;
                    /*System.out.println("Position " + n + " contains a letter");*/
                }
                // Condition 3: contains special character, !@#$
                else if ("!@#$%&^*?()à{}=+-/".contains(password.substring(n, n+1))) {
                    hasSpecialChar = true;
                    /*System.out.println("Position " + n + " contains a character");*/
                }
                else {
                   /* hasInvalidChar=true;*/
                    /*System.out.println("Position " + n + " contains an invalid character");*/
                    try {
                        throw new InvalidCharacterException(password.substring(n, n+1));
                    } catch (InvalidCharacterException e) {
                        e.toString();
                    }
                }

            }

            // Test & Exception Handling
            try {
                if (!hasNumber) {
                    throw new NumberCriteriaException(password);
                } else if (!hasLetter){
                    throw new LetterCriteriaException(password);
                }
                else if (!hasSpecialChar) {
                    throw new SpecialCharCriteriaException(password);
                }
                else {
                    System.out.println("Valid password");
                }
            } catch (NumberCriteriaException | LetterCriteriaException | SpecialCharCriteriaException e) {
                System.out.println("Invalid password");
                System.out.println(e.toString());
            }
        }
    }
}

class InvalidCharacterException extends Exception {
        String ch;
        public InvalidCharacterException(String ch) {
            this.ch= ch;
        }
        public String toString(){
            return "InvalidCharacterException: " + ch;
        }
}

class NumberCriteriaException extends Exception {
    String password;
    public NumberCriteriaException(String password) {
        this.password= password;
    }
    public String toString(){
        return "NumberCriteriaException: " + password;
    }
}

class LetterCriteriaException extends Exception {
    String password;
    public LetterCriteriaException(String password) {
        this.password= password;
    }
    public String toString(){
        return "LetterCriteriaException: " + password;
    }
}

class SpecialCharCriteriaException extends Exception {
    String password;
    public SpecialCharCriteriaException(String password) {
        this.password= password;
    }
    public String toString(){
        return "SpecialCharCriteriaException: " + password;
    }
}