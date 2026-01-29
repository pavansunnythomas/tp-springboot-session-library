package com.pavan.tp_library.model.dto;

import java.time.LocalDate;

import com.pavan.tp_library.util.DateUtils;
import com.pavan.tp_library.util.StringUtils;

public record UserRecord(
        Long userId,

        // Identity
        String fullName,
        String email,
        String phoneNumber,

        // Library membership
        String membershipNumber,
        LocalDate membershipStartDate,
        Boolean active,

        // Lending constraints
        Integer maxBooksAllowed,
        Integer currentlyBorrowedCount,

        // Audit
        LocalDate createdDate
) {
       public UserRecord {
        // Identity
        if (StringUtils.isNullOrEmpty(fullName)) {
            throw new IllegalArgumentException("Full name must not be blank");
        }

        if (StringUtils.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email must not be blank");
        }

        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Email format is invalid");
        }

        // Membership
        if (StringUtils.isNullOrEmpty(membershipNumber)) {
            throw new IllegalArgumentException("Membership number must not be blank");
        }

        if (!DateUtils.isValidBeforeDate(membershipStartDate)) {
            throw new IllegalArgumentException("Membership start date is required and cannot be in the future");
        }

        // Lending rules
        if (maxBooksAllowed == null || maxBooksAllowed <= 0) {
            throw new IllegalArgumentException("Max books allowed must be > 0");
        }

        if (currentlyBorrowedCount == null || currentlyBorrowedCount < 0) {
            throw new IllegalArgumentException("Currently borrowed count must be >= 0");
        }

        if (currentlyBorrowedCount > maxBooksAllowed) {
            throw new IllegalArgumentException(
                    "Borrowed books cannot exceed allowed limit"
            );
        }

        // Active flag default consistency
        if (active == null) {
            active = true;
        }

        // Audit
        if (!DateUtils.isValidBeforeDate(createdDate)) {
            throw new IllegalArgumentException("Created date cannot be in the future");
        }
    }     
}