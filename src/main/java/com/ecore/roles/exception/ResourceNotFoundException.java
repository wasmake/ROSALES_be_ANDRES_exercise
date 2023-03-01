package com.ecore.roles.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public <T> ResourceNotFoundException(Class<T> resource, UUID id) {
        super(String.format("%s %s not found", resource.getSimpleName(), id));
    }

    public <T> ResourceNotFoundException(Class<T> resource) {
        super(String.format("%s not found", resource.getSimpleName()));
    }
}
