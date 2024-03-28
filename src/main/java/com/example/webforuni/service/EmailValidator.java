package com.example.webforuni.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator {

    public final static  String emailRegex = "^\\w+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

}
