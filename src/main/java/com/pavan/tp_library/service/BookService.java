package com.pavan.tp_library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pavan.tp_library.model.Book;
import com.pavan.tp_library.model.dto.BookRecord;
import com.pavan.tp_library.model.entity.BookEntity;
import com.pavan.tp_library.repository.BookRepository;

import ch.qos.logback.core.util.StringUtil;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    // @autowired is added explicitly since only one constructor is declared;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // READ by ID
    public BookEntity findById(Long id) {
        log.info("Fetching book with id {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));
    }

    // READ by author (or all)
    public List<BookRecord> findByAuthor(String author) {
        if (author == null || author.isBlank()) {
            log.info("Fetching all books");
            return bookRepository.findAll().stream().map(book -> new BookRecord(book)).toList();
        }

        log.info("Fetching books by author {}", author);
        return bookRepository.findByAuthorContainingIgnoreCase(author).stream().map(book -> new BookRecord(book))
                .toList();
    }

    // CREATE
    public BookRecord addNewBook(Book newBook) {
        log.info("Adding new book with ISBN {}", newBook.getIsbn());
        BookEntity bookToBeAdded = new BookEntity(newBook);
        return new BookRecord(bookRepository.save(bookToBeAdded));
    }

    // UPDATE
    public BookEntity updateBook(Long id, BookRecord updatedBook) {
        log.info("Updating book with id {}", id);

        BookEntity existingBook = findById(id);

        // Update mutable fields only
        if(!StringUtil.isNullOrEmpty(updatedBook.title()))
            existingBook.setTitle(updatedBook.title());
        if(!StringUtil.isNullOrEmpty(updatedBook.author()))
            existingBook.setAuthor(updatedBook.author());
        if(!StringUtil.isNullOrEmpty(updatedBook.isbn()))
            existingBook.setIsbn(updatedBook.isbn());
        if(!StringUtil.isNullOrEmpty(updatedBook.publisher()))
            existingBook.setPublisher(updatedBook.publisher());
        if(updatedBook.publicationYear() != null)
            existingBook.setPublicationYear(updatedBook.publicationYear());
        if(!StringUtil.isNullOrEmpty(updatedBook.language()))
            existingBook.setLanguage(updatedBook.language());
        if(!StringUtil.isNullOrEmpty(updatedBook.category()))
            existingBook.setCategory(updatedBook.category());
        if(updatedBook.price() != null)
            existingBook.setPrice(updatedBook.price());
        if(updatedBook.totalCopies() != null)
            existingBook.setTotalCopies(updatedBook.totalCopies());
        if(updatedBook.availableCopies() != null) {
            if(updatedBook.availableCopies() > existingBook.getTotalCopies())
                throw new IllegalArgumentException("Available copies cannot exceed total copies");
            existingBook.setAvailableCopies(updatedBook.availableCopies());
        } 
        if(updatedBook.available() != null) 
            existingBook.setAvailable(updatedBook.available());
        if(updatedBook.addedDate() != null)
            existingBook.setAddedDate(updatedBook.addedDate());

        return bookRepository.save(existingBook);
    }

    // DELETE
    public String removeBook(Long id) {
        log.info("Removing book with id {}", id);

        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found with id " + id);
        }

        bookRepository.deleteById(id);

        return "Book removed";
    }
}
