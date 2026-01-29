package com.pavan.tp_library.util;

public final class IsbnUtils {

    private IsbnUtils() {
        throw new AssertionError("Utility class - do not instantiate");
    }

    public static boolean isValidIsbn13(String isbn) {
        if (isbn == null) {
            return false;
        }

        String clean = isbn.replaceAll("[^0-9]", "");
        if (clean.length() != 13) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = clean.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit == (clean.charAt(12) - '0');
    }

    public static String cleanIsbn(String isbn) {
        return isbn != null ? isbn.replaceAll("[^0-9]", "") : null;
    }
}
