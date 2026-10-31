package com.edwinkesuma.sbi_assignment.utils;

public final class PhoneNumberNormalizer {

    private PhoneNumberNormalizer() {
    }

    public static String normalize(String phone) {
        if (phone == null || phone.isBlank()) {
            return phone;
        }

        String normalized = phone.replaceAll("[^0-9]", "");

        if (normalized.startsWith("0")) {
            return "62" + normalized.substring(1);
        }

        if (normalized.startsWith("8")) {
            return "62" + normalized;
        }

        return normalized;
    }
}