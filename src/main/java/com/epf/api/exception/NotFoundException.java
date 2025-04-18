package com.epf.api.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    
    public NotFoundException(String entityType, String identifier) {
        super(String.format("L'entité %s avec l'identifiant '%s' n'a pas été trouvée", entityType, identifier));
    }
}
