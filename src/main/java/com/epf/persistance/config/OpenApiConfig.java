package com.epf.persistance.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Plants vs. Zombies")
                        .description("API REST pour la gestion des ressources du jeu Plants vs. Zombies")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Équipe de développement PvZ")
                                .email("contact@pvz.com")
                                .url("https://www.pvz.com"))
                        .license(new License()
                                .name("License")
                                .url("https://license.url")));
    }


    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .build();
    }


    @Bean
    public GroupedOpenApi plantesApi() {
        return GroupedOpenApi.builder()
                .group("plantes-api")
                .pathsToMatch("/plantes/**")
                .build();
    }


    @Bean
    public GroupedOpenApi zombiesApi() {
        return GroupedOpenApi.builder()
                .group("zombies-api")
                .pathsToMatch("/zombies/**")
                .build();
    }


    @Bean
    public GroupedOpenApi mapsApi() {
        return GroupedOpenApi.builder()
                .group("maps-api")
                .pathsToMatch("/maps/**")
                .build();
    }
}
