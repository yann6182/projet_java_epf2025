package com.epf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration pour les ressources statiques de SpringDoc (Swagger UI)
 */
@Configuration
@EnableWebMvc
public class SpringDocConfig implements WebMvcConfigurer {

    /**
     * Configure les mappings de ressources pour Swagger UI
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ressources de Swagger UI
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.15.5/");
        
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * Ajoute des mappings de contrôleurs de vue pour rediriger vers Swagger UI
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
        registry.addViewController("/swagger-ui")
                .setViewName("forward:/swagger-ui/index.html");
    }
} 