package com.pavan.tp_library.util;

import java.time.LocalDate;

public final class DateUtils {
    // This prevents unwanted instantiation
    private DateUtils() {
        throw new AssertionError("Utility class - do not instantiate");
    }

    public static LocalDate calculateDueDate(LocalDate issueDate, int loanDays) {
        if (issueDate == null) {
            throw new IllegalArgumentException("issueDate must not be null");
        }
        if (loanDays <= 0) {
            throw new IllegalArgumentException("loanDays must be > 0");
        }
        return issueDate.plusDays(loanDays);
    }

    public static boolean isOverdue(LocalDate dueDate) {
        if (dueDate == null) {
            return false;
        }
        return LocalDate.now().isAfter(dueDate);
    }

    public static boolean isValidBeforeDate(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }

}
