package com.pavan.tp_library.model;

import java.time.LocalDate;

import com.pavan.tp_library.model.dto.BookRecord;
import com.pavan.tp_library.model.dto.NewBookRecord;
import com.pavan.tp_library.util.IsbnUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    private Long bookId;

    // Core book info
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer publicationYear;
    private String language;
    private String category;

    // Library-specific data
    private Integer totalCopies;
    private Integer availableCopies;
    private Boolean available;

    // Optional metadata
    private Double price;
    private LocalDate addedDate;

    public Book(NewBookRecord book) {
        if (book == null) {
            throw new IllegalArgumentException("BookRecord must not be null");
        }

        this.title = book.title();
        this.author = book.author();
        this.isbn = IsbnUtils.cleanIsbn(book.isbn());
        this.publisher = book.publisher();
        this.publicationYear = book.publicationYear();
        this.language = book.language();
        this.category = book.category();

        this.totalCopies = book.totalCopies();
        this.availableCopies = book.availableCopies();
        this.available = book.available() != null
                ? book.available()
                : book.availableCopies() > 0;

        this.price = book.price();
        this.addedDate = book.addedDate() != null
                ? book.addedDate()
                : java.time.LocalDate.now();
    }

        public Book(BookRecord book) {
        if (book == null) {
            throw new IllegalArgumentException("BookRecord must not be null");
        }

        this.bookId = book.bookId();

        this.title = book.title();
        this.author = book.author();
        this.isbn = IsbnUtils.cleanIsbn(book.isbn());
        this.publisher = book.publisher();
        this.publicationYear = book.publicationYear();
        this.language = book.language();
        this.category = book.category();

        this.totalCopies = book.totalCopies();
        this.availableCopies = book.availableCopies();
        this.available = book.available() != null
                ? book.available()
                : book.availableCopies() > 0;

        this.price = book.price();
        this.addedDate = book.addedDate() != null
                ? book.addedDate()
                : java.time.LocalDate.now();
    }

}