package com.ecore.roles.exception;

public class ResourceExistsException extends RuntimeException {

    public <T> ResourceExistsException(Class<T> resource) {
        super(String.format("%s already exists", resource.getSimpleName()));
    }
}
