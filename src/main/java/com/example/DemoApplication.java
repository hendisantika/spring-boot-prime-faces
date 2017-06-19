package com.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemoApplication {
	private final static Logger logger = LoggerFactory
			.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		logger.info("Application has been launched");
	}
	

	@Configuration
	@Profile("dev")
	static class ConfigureJSFContextParameters implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {

			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
			servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
			servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
			servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

		}
	}

	@Configuration
	@Profile("production")
	static class ConfigureJSFContextParametersProd implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {

			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
			servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
			servletContext.setInitParameter("facelets.DEVELOPMENT", "false");
			servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "-1");

		}
	}
}
