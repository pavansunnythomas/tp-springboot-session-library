package com.pavan.tp_library.model;

import java.time.LocalDate;

import com.pavan.tp_library.model.dto.UserRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long userId;

    // Identity
    private String fullName;
    private String email;
    private String phoneNumber;

    // Library membership
    private String membershipNumber;
    private LocalDate membershipStartDate;
    private Boolean active;

    // Lending constraints
    private Integer maxBooksAllowed;
    private Integer currentlyBorrowedCount;

    // Audit
    private LocalDate createdDate;


    public User(UserRecord record) {
    if (record == null) {
        throw new IllegalArgumentException("UserRecord must not be null");
    }

    this.userId = record.userId();

    this.fullName = record.fullName();
    this.email = record.email();
    this.phoneNumber = record.phoneNumber();

    this.membershipNumber = record.membershipNumber();
    this.membershipStartDate = record.membershipStartDate();

    this.active = record.active() != null ? record.active() : true;

    this.maxBooksAllowed = record.maxBooksAllowed();
    this.currentlyBorrowedCount = record.currentlyBorrowedCount() != null
            ? record.currentlyBorrowedCount()
            : 0;

    this.createdDate = record.createdDate() != null
            ? record.createdDate()
            : LocalDate.now();
}
}
