package com.epf.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Configuration pour la validation des beans
 * Utilise Jakarta Validation API avec Hibernate Validator pour Spring 6
 */
@Configuration
public class ValidationConfig {

    /**
     * Crée un validateur Jakarta explicitement configuré
     */
    @Bean
    public Validator jakartaValidator() {
        try {
            ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                    .configure()
                    .buildValidatorFactory();
            return factory.getValidator();
        } catch (Exception e) {
            throw new RuntimeException("Erreur d'initialisation du validateur Hibernate", e);
        }
    }

    /**
     * Configure le validateur pour les objets Java dans Spring
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        return bean;
    }

    /**
     * Active la validation des méthodes avec @Validated
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(jakartaValidator());
        return processor;
    }
} 