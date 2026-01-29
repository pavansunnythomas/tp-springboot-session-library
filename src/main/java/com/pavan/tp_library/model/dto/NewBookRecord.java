package com.pavan.tp_library.model.dto;

import java.time.LocalDate;

import com.pavan.tp_library.util.DateUtils;
import com.pavan.tp_library.util.IsbnUtils;
import com.pavan.tp_library.util.StringUtils;

public record NewBookRecord(

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

        public NewBookRecord {
                if (StringUtils.isNullOrEmpty(title)) {
                        throw new IllegalArgumentException("Title must not be blank");
                }

                if (StringUtils.isNullOrEmpty(author)) {
                        throw new IllegalArgumentException("Author must not be blank");
                }

                if (!IsbnUtils.isValidIsbn13(isbn)) {
                        throw new IllegalArgumentException("Invalid ISBN number");
                }

                // Publication year sanity check
                if (publicationYear != null) {
                        int currentYear = LocalDate.now().getYear();
                        if (publicationYear < 1450 || publicationYear > currentYear) {
                                throw new IllegalArgumentException("Publication year is invalid");
                        }
                }

                // Copies consistency
                if (totalCopies == null || totalCopies < 0) {
                        throw new IllegalArgumentException("Total copies must be >= 0");
                }

                if (availableCopies == null || availableCopies < 0) {
                        throw new IllegalArgumentException("Available copies must be >= 0");
                }

                if (availableCopies > totalCopies) {
                        throw new IllegalArgumentException("Available copies cannot exceed total copies");
                }

                // Availability flag consistency
                boolean computedAvailable = availableCopies > 0;
                if (available != null && available != computedAvailable) {
                        throw new IllegalArgumentException(
                                        "Available flag does not match available copies");
                }

                // Price sanity
                if (price != null && price < 0) {
                        throw new IllegalArgumentException("Price must be >= 0");
                }

                // addedDate sanity
                if (!DateUtils.isValidBeforeDate(addedDate)) {
                        throw new IllegalArgumentException("Added date cannot be in the future");
                }
        }

}