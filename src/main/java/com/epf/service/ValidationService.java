package com.epf.service;

import com.epf.api.exception.ValidationException;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    private final Validator validator;

    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    /**
     * Valide un objet selon les contraintes JSR-380 et lance une exception si des violations sont détectées
     * @param object L'objet à valider
     * @param <T> Le type de l'objet
     * @throws ValidationException Si des violations de contraintes sont détectées
     */
    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .collect(Collectors.joining(", "));
            
            throw new ValidationException(errorMessage);
        }
    }
} 