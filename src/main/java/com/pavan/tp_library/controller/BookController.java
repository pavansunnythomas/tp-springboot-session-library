package com.pavan.tp_library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.tp_library.exception.BookNotFoundException;
import com.pavan.tp_library.model.Book;
import com.pavan.tp_library.model.dto.NewBookRecord;
import com.pavan.tp_library.model.dto.BookRecord;
import com.pavan.tp_library.service.BookService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ******************* Create *******************
    @PostMapping("/books")
    public BookRecord createBook(@RequestBody NewBookRecord newBook) {
        // 'newBook' is automatically populated from the JSON sent by the client
        return bookService.addNewBook(new Book(newBook));
    }

    // ******************* Read *********************

    @GetMapping("/books")
    public List<BookRecord> getBooksByAuthor(@RequestParam(required = false) String author) {
        // Logic to find books where author is "Rowling"
        return bookService.findByAuthor(author);
    }
    @GetMapping("/books/{id}")
    public BookRecord getBookById(@PathVariable("id") Long bookId) throws BookNotFoundException {
        // Logic to find book with ID 101
        return new BookRecord(bookService.findById(bookId));
    }

    // ******************* Update *******************
    @PutMapping("/books/{id}")
    public BookRecord updateBook(@PathVariable Long id, @RequestBody BookRecord update) {
        // 'update' is automatically populated from the JSON sent by the client
        return new BookRecord(bookService.updateBook(id, update));
    }

    // ******************* Delete *******************
    @DeleteMapping("/books/{id}")
    public String removeBook(@PathVariable Long id) {
        // Logic to remove the book
        return bookService.removeBook(id);
    }

}
