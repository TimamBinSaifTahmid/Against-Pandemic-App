package com.example.against_pandemic;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validation {


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");


    boolean validateEmail(String email,SignUp signUp) {

        if (email.isEmpty()) {
            signUp.email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUp.email.setError("Please enter a valid email address");
            return false;
        } else {
            signUp.email.setError(null);
            return true;
        }
    }

     boolean validatePassword(String password,SignUp signUp) {

        if (password.isEmpty()) {
            signUp.password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            signUp.password.setError("Password too weak");
            return false;
        } else {
            signUp.password.setError(null);
            return true;
        }
    }

    boolean validateEmail(String email,LogIn logIn) {

        if (email.isEmpty()) {
            logIn.email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            logIn.email.setError("Please enter a valid email address");
            return false;
        } else {
            logIn.email.setError(null);
            return true;
        }
    }

    boolean validatePassword(String password,LogIn logIn) {

        if (password.isEmpty()) {
            logIn.password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            logIn.password.setError("Password too weak");
            return false;
        } else {
            logIn.password.setError(null);
            return true;
        }
    }

}
