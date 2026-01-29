package com.pavan.tp_library.model.dto;

import java.time.LocalDate;

import com.pavan.tp_library.model.entity.BookEntity;
import com.pavan.tp_library.util.DateUtils;

public record BookRecord(

        Long bookId,

        // Core book info
        String title,
        String author,
        String isbn,
        String publisher,
        Integer publicationYear,
        String language,
        String category,

        // Library-specific data
        Integer totalCopies,
        Integer availableCopies,
        Boolean available,

        // Optional metadata
        Double price,
        LocalDate addedDate) {
    public BookRecord {
        // Publication year sanity check
        if (publicationYear != null) {
            int currentYear = LocalDate.now().getYear();
            if (publicationYear < 1450 || publicationYear > currentYear) {
                throw new IllegalArgumentException("Publication year is invalid");
            }
        }

        // Copies consistency
        if (totalCopies != null && totalCopies < 0) {
            throw new IllegalArgumentException("Total copies must be >= 0");
        }

        if (availableCopies != null && availableCopies < 0) {
            throw new IllegalArgumentException("Available copies must be >= 0");
        }

        if (availableCopies != null &&  totalCopies != null && availableCopies > totalCopies) {
            throw new IllegalArgumentException("Available copies cannot exceed total copies");
        }

        // Price sanity
        if (price != null && price < 0) {
            throw new IllegalArgumentException("Price must be >= 0");
        }

        // addedDate sanity
        if (addedDate != null && !DateUtils.isValidBeforeDate(addedDate)) {
            throw new IllegalArgumentException("Added date cannot be in the future");
        }
    }

    public BookRecord(BookEntity entity) {
        this(
                entity.getBookId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getIsbn(),
                entity.getPublisher(),
                entity.getPublicationYear(),
                entity.getLanguage(),
                entity.getCategory(),
                entity.getTotalCopies(),
                entity.getAvailableCopies(),
                entity.getAvailable(),
                entity.getPrice(),
                entity.getAddedDate());
    }
}
