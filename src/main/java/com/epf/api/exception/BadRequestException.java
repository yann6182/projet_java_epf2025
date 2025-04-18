package com.epf.api.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException() {
        super("La requête est mal formée ou contient des données invalides");
    }
} 