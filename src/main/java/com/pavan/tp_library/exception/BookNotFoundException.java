package com.pavan.tp_library.exception;

public class BookNotFoundException extends Exception{

    private String message;

    public BookNotFoundException(String message) {
        super();
        this.message = message;
    }

    public BookNotFoundException() {
        super();
        this.message = "Book Not Found";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
