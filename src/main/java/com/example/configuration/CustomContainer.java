package com.example.configuration;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-prime-faces
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 28/05/21
 * Time: 06.58
 */
public class CustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    public void customize(ConfigurableServletWebServerFactory container) {
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.faces"));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.faces"));
        container.addErrorPages(new ErrorPage("/error/error.faces"));
    }
}
