package com.moezguer.exception;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException (Long id){
        super("Author id not found: " + id);
    }
}
