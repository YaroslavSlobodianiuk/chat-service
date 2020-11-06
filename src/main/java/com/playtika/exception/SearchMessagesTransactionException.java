package com.playtika.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SearchMessagesTransactionException extends RuntimeException {

    public SearchMessagesTransactionException(String message) {
        super(message);
    }
}
