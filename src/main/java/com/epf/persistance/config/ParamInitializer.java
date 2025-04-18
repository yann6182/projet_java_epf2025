package com.epf.persistance.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;


public class ParamInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("springdoc.swagger-ui.path", "/swagger-ui/index.html");
        servletContext.setInitParameter("springdoc.api-docs.path", "/v3/api-docs");
        servletContext.setInitParameter("springdoc.swagger-ui.disable-swagger-default-url", "true");
    }
}