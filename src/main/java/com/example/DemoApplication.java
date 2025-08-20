package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	private final static Logger logger = LoggerFactory
			.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.run(args);
		logger.info("Application has been launched");
	}


    // Joinfaces handles JSF context configuration automatically
}
