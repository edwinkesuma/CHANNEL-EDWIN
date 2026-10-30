package com.edwinkesuma.sbi_assignment.utils;

public class TextSanitizer {

    public static String sanitize(String value) {
        if (value == null) {
            return null;
        }

        return value.replaceAll("[.,]", "").trim().replaceAll("\\s+", " ");
    }
}
