package com.epf.config;

import com.epf.persistance.config.OpenApiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Configuration principale de l'application
 * Intègre toutes les configurations nécessaires au fonctionnement de l'application
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.epf.api",
        "com.epf.service",
        "com.epf.persistance.Dao"
})
@Import({
        ValidationConfig.class,
        com.epf.persistance.config.OpenApiConfig.class,
        SecurityConfig.class
})
public class AppConfig implements WebMvcConfigurer {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pvz?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dataSource.setUsername("epf");
        dataSource.setPassword("Messi6");
        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/")
                .addResourceLocations("classpath:/static/images/")
                .addResourceLocations("classpath:/resources/images/");
                
        registry.addResourceHandler("/plante/**")
                .addResourceLocations("/plante/")
                .addResourceLocations("classpath:/static/plante/")
                .addResourceLocations("classpath:/resources/plante/");
        
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/4.15.5/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedHeader("*");
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
} 