package com.example.against_pandemic;

public interface Credentials {
    SignUp signUp=new SignUp();
    LogIn logIn=new LogIn();

    /*default void signup_implimentaion() {
        boolean validateEmail(String email, SignUp signUp);
    }
    default void login_implimentaion() {
        boolean validateEmail(String email, LogIn logIn);
    }*/
}
