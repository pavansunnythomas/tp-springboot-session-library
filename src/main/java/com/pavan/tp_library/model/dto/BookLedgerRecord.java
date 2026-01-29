package com.pavan.tp_library.model.dto;

import java.time.LocalDate;

import com.pavan.tp_library.model.enums.LedgerStatus;

public record BookLedgerRecord(
        Long ledgerId,

        // Relationships
        Long bookId,
        Long userId,

        // Transaction details
        LocalDate issueDate,
        LocalDate dueDate,
        LocalDate returnDate,

        // Status
        LedgerStatus status,

        // Audit
        LocalDate createdDate,
        String remarks
) {
    public BookLedgerRecord {
        // Identity
        if (bookId == null) {
            throw new IllegalArgumentException("bookId must not be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }

        // Dates
        if (issueDate == null) {
            throw new IllegalArgumentException("issueDate must not be null");
        }
        if (dueDate == null || dueDate.isBefore(issueDate)) {
            throw new IllegalArgumentException("dueDate must be on or after issueDate");
        }
        if (returnDate != null && returnDate.isBefore(issueDate)) {
            throw new IllegalArgumentException("returnDate cannot be before issueDate");
        }

        // Status consistency
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }

        switch (status) {
            case ISSUED -> {
                if (returnDate != null) {
                    throw new IllegalArgumentException(
                            "ISSUED ledger must not have a returnDate"
                    );
                }
            }
            case RETURNED -> {
                if (returnDate == null) {
                    throw new IllegalArgumentException(
                            "RETURNED ledger must have a returnDate"
                    );
                }
            }
            case OVERDUE -> {
                if (returnDate != null) {
                    throw new IllegalArgumentException(
                            "OVERDUE ledger must not have a returnDate"
                    );
                }
            }
            case LOST -> {
                // returnDate optional, remarks recommended but not enforced
            }
        }

        // Audit
        if (createdDate != null && createdDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("createdDate cannot be in the future");
        }
    }
}
