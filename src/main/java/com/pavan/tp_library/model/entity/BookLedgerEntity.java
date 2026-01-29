package com.pavan.tp_library.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.pavan.tp_library.model.enums.LedgerStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "book_ledger",
    indexes = {
        @Index(name = "idx_ledger_book_status", columnList = "book_id,status"),
        @Index(name = "idx_ledger_user_status", columnList = "user_id,status")
    }
)
public class BookLedgerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ledgerId;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Transaction details
    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column
    private LocalDate returnDate;

    // Status tracking
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LedgerStatus status;

    // Audit
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;

    @Column(length = 255)
    private String remarks;

    public BookLedgerEntity(BookEntity book, UserEntity user,
                            LocalDate issueDate, LocalDate dueDate) {
        this.book = book;
        this.user = user;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = LedgerStatus.ISSUED;
        this.createdDate = LocalDate.now();
    }

    // Domain behavior (this matters)
    public void markReturned(LocalDate returnDate) {
        if (this.status != LedgerStatus.ISSUED && this.status != LedgerStatus.OVERDUE) {
            throw new IllegalStateException("Book is not currently issued");
        }
        this.returnDate = returnDate;
        this.status = LedgerStatus.RETURNED;
    }

    public void markOverdue() {
        if (this.status == LedgerStatus.ISSUED) {
            this.status = LedgerStatus.OVERDUE;
        }
    }

    public void markLost(String remarks) {
        this.status = LedgerStatus.LOST;
        this.remarks = remarks;
    }
}