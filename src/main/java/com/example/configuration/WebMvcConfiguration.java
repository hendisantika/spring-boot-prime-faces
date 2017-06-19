package com.example.configuration;

import java.util.Locale;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("es"));
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setAlwaysUseMessageFormat(true);
	    messageSource.setUseCodeAsDefaultMessage(true);
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setBasenames("classpath:messages");
	    return messageSource;
	}
	
	@Override
	public Validator getValidator() {
		ReloadableResourceBundleMessageSource messageSource =
			new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:validation");
		messageSource.setFallbackToSystemLocale(false);
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource);
		return factory;
	}
	
	@Bean
	public ServerProperties getServerProperties() {
	    return new ServerCustomization();
	}

}
