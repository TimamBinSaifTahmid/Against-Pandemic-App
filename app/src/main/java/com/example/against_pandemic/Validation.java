package com.example.against_pandemic;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validation {
    static int flag;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");


    String validateEmail(String email) {

        if (email.isEmpty()) {
            //signUp.email.setError("Field can't be empty");
            String errorMassage= "Field can't be empty";
            return errorMassage;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           // signUp.email.setError("Please enter a valid email address");
            String errorMassage= "Please enter a valid email address";
            return errorMassage;
        } else {
            //signUp.email.setError(null);
            String errorMassage= "null";
            return errorMassage;
        }
    }

     String validatePassword(String password) {

        if (password.isEmpty()) {
            //signUp.password.setError("Field can't be empty");
            String errorMassage= "Field can't be empty";
            return errorMassage;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
           // signUp.password.setError("Password too weak");
            String errorMassage= "Password too weak";
            return errorMassage;
        } else {
           // signUp.password.setError(null);
            String errorMassage= "null";
            return errorMassage;
        }
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int isloggedin(){
        return flag;
    }

}
