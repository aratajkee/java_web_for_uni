package com.example.webforuni;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {


    public static String getDate(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
    }
    public static void main(String[] args) {

        System.out.println(getDate());
    }

}
