package com.ecore.roles.exception;

public class InvalidArgumentException extends RuntimeException {

    public <T> InvalidArgumentException(Class<T> resource) {
        super(String.format("Invalid '%s' object", resource.getSimpleName()));
    }

    public <T> InvalidArgumentException(Class<T> resource, String message) {
        super(String.format("Invalid '%s' object. %s", resource.getSimpleName(), message));
    }
}
