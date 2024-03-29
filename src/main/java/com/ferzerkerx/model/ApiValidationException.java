package com.ferzerkerx.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiValidationException extends RuntimeException {

    public ApiValidationException(String message) {
        super(message);
    }

}
