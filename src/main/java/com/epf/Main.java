package com.epf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.epf.persistance.config.DatabaseConnection;
import com.epf.persistance.config.CorsConfig;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            DatabaseConnection.class,
            CorsConfig.class
        );
        context.close();
    }
}