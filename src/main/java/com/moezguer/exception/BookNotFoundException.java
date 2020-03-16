package com.moezguer.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final Long id) {
        super("Book id not found : " + id);
    }

}
