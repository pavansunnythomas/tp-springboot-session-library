package com.pavan.tp_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.tp_library.model.entity.BookEntity;
import com.pavan.tp_library.model.entity.BookLedgerEntity;
import com.pavan.tp_library.model.entity.UserEntity;
import com.pavan.tp_library.model.enums.LedgerStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookLedgerRepository extends JpaRepository<BookLedgerEntity, Long> {

    // Active (not returned) ledger for a specific book
    Optional<BookLedgerEntity> findByBookAndStatus(BookEntity book, LedgerStatus status);

    // All active books issued to a user
    List<BookLedgerEntity> findByUserAndStatus(UserEntity user, LedgerStatus status);

    // Overdue books (cron/job use)
    List<BookLedgerEntity> findByStatusAndDueDateBefore(
            LedgerStatus status,
            LocalDate date
    );

    // History for a user
    List<BookLedgerEntity> findByUserOrderByIssueDateDesc(UserEntity user);

    // History for a book
    List<BookLedgerEntity> findByBookOrderByIssueDateDesc(BookEntity book);
}
