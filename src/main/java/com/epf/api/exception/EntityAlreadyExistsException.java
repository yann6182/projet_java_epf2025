package com.epf.api.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
    
    public EntityAlreadyExistsException(String entityType, String identifier) {
        super(String.format("L'entité %s avec l'identifiant '%s' existe déjà", entityType, identifier));
    }
} 