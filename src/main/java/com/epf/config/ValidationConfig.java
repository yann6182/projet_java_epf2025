package com.epf.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidationConfig {

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


    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        return bean;
    }


    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(jakartaValidator());
        return processor;
    }
} 