package com.moezguer.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(final Long id) {
        super("Author id not found: " + id);
    }

}
