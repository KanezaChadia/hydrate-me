package com.example.hydrateme;

public class HydrateMeUser {

    public static String replaceSpecialChars(String input) {
        String output = input.replaceAll("[@.]", "_");
        return output;
    }

}
