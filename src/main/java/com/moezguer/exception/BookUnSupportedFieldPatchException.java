package com.moezguer.exception;

import java.util.Set;

public class BookUnSupportedFieldPatchException extends RuntimeException {

    public BookUnSupportedFieldPatchException(final Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
