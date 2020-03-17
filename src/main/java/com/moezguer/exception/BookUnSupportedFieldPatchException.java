package com.moezguer.exception;

public class BookUnSupportedFieldPatchException extends RuntimeException {

    public BookUnSupportedFieldPatchException(final String name) {
        super("Field " + name + " update is not allow.");
    }

}
