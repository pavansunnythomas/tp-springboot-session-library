package com.pavan.tp_library.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.pavan.tp_library.model.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = {
        @UniqueConstraint(columnNames = "isbn")
})
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    // Core book info
    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 150)
    private String author;

    @Column(nullable = false, length = 20)
    private String isbn;

    @Column(length = 150)
    private String publisher;

    @Column
    private Integer publicationYear;

    @Column(length = 50)
    private String language;

    @Column(length = 100)
    private String category;

    // Library-specific data
    @Column(nullable = false)
    private Integer totalCopies;

    @Column(nullable = false)
    private Integer availableCopies;

    @Column(nullable = false)
    private Boolean available;

    // Optional metadata
    @Column
    private Double price;

    // Audit
    @Column(nullable = false, updatable = false)
    private LocalDate addedDate;

    // Domain logic
    public boolean canBeIssued() {
        return Boolean.TRUE.equals(available) && availableCopies > 0;
    }

    public void issueCopy() {
        if (!canBeIssued()) {
            throw new IllegalStateException("No available copies to issue");
        }
        this.availableCopies--;
        this.available = availableCopies > 0;
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            this.availableCopies++;
        }
        this.available = availableCopies > 0;
    }

    public BookEntity(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null");
        }

        // ID: copy only if this is an update flow
        this.bookId = book.getBookId();

        // Core info
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.publisher = book.getPublisher();
        this.publicationYear = book.getPublicationYear();
        this.language = book.getLanguage();
        this.category = book.getCategory();

        // Inventory
        this.totalCopies = book.getTotalCopies();
        this.availableCopies = book.getAvailableCopies();
        this.available = book.getAvailableCopies() != null && book.getAvailableCopies() > 0;

        // Optional metadata
        this.price = book.getPrice();
        this.addedDate = book.getAddedDate() != null
                ? book.getAddedDate()
                : java.time.LocalDate.now();
    }
}
