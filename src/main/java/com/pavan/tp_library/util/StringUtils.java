package com.pavan.tp_library.util;

public final class StringUtils {

    // This prevents unwanted instantiation
    private StringUtils() {
        throw new AssertionError("Utility class - do not instantiate");
    }

    public static boolean isNullOrEmpty(String e) {
        return e == null || e.isBlank();
    }
}
