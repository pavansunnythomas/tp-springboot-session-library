package com.pavan.tp_library.model;

import java.time.LocalDate;

import com.pavan.tp_library.model.dto.BookLedgerRecord;
import com.pavan.tp_library.model.enums.LedgerStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookLedger {
    
    private Long ledgerId;

    // Relationships (store IDs, not objects, to keep things clean)
    private Long bookId;
    private Long userId;

    // Transaction details
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Status tracking
    private LedgerStatus status;

    // Audit
    private LocalDate createdDate;
    private String remarks;

    public BookLedger(BookLedgerRecord record) {
    if (record == null) {
        throw new IllegalArgumentException("BookLedgerRecord must not be null");
    }

    this.ledgerId = record.ledgerId();

    this.bookId = record.bookId();
    this.userId = record.userId();

    this.issueDate = record.issueDate();
    this.dueDate = record.dueDate();
    this.returnDate = record.returnDate();

    this.status = record.status();

    this.createdDate = record.createdDate() != null
            ? record.createdDate()
            : LocalDate.now();

    this.remarks = record.remarks();
}
}
