package com.ferzerkerx.model;

public class BaseExcpetion extends RuntimeException {

    public BaseExcpetion() {
    }

    public BaseExcpetion(String message) {
        super(message);
    }

    public BaseExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseExcpetion(Throwable cause) {
        super(cause);
    }
}
