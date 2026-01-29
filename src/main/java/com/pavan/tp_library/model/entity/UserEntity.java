package com.pavan.tp_library.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "membership_number")
    }
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // Identity
    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    // Library membership
    @Column(name = "membership_number", nullable = false, length = 50)
    private String membershipNumber;

    @Column(nullable = false)
    private LocalDate membershipStartDate;

    @Column(nullable = false)
    private Boolean active = true;

    // Lending constraints
    @Column(nullable = false)
    private Integer maxBooksAllowed;

    @Column(nullable = false)
    private Integer currentlyBorrowedCount = 0;

    // Audit
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;

    public UserEntity(String fullName, String email, String phoneNumber,
                      String membershipNumber, LocalDate membershipStartDate,
                      Integer maxBooksAllowed) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipNumber = membershipNumber;
        this.membershipStartDate = membershipStartDate;
        this.maxBooksAllowed = maxBooksAllowed;
        this.active = true;
        this.currentlyBorrowedCount = 0;
        this.createdDate = LocalDate.now();
    }

    public void incrementBorrowedCount() {
        this.currentlyBorrowedCount++;
    }

    public void decrementBorrowedCount() {
        if (this.currentlyBorrowedCount > 0) {
            this.currentlyBorrowedCount--;
        }
    }

}
