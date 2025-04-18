package com.epf.api.exception;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
    
    public ServiceUnavailableException(String serviceName, String reason) {
        super(String.format("Le service %s n'est pas disponible: %s", serviceName, reason));
    }
    
    public ServiceUnavailableException() {
        super("Le service demandé n'est pas disponible actuellement");
    }
} 