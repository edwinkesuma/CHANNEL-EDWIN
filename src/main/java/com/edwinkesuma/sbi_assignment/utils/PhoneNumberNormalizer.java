package com.edwinkesuma.sbi_assignment.utils;

public class PhoneNumberNormalizer {

    public static String normalize(String phone) {
        if (phone == null) {
            return null;
        }

        String normalized = phone.replaceAll("[^0-9]", "");

        if (normalized.startsWith("0")) {
            normalized = "62" + normalized.substring(1);
        } else if (normalized.startsWith("8")) {
            normalized = "62" + normalized;
        }

        return normalized;
    }
}
