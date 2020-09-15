package com.in28minutes.restwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestwebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestwebservicesApplication.class, args);
	}

	// In below case we need to specify Locale in each controller endpoint
	// @Bean
	// public LocaleResolver localeResolver() {
	// SessionLocaleResolver slr = new SessionLocaleResolver();
	// slr.setDefaultLocale(Locale.US);
	// return slr;
	// }

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	// We don't need bean to specify messageSource basename
	// Alternative way is to mention application properties
	// spring.messages.basename=messages
	// @Bean
	// public ResourceBundleMessageSource messageSource() {
	// ResourceBundleMessageSource messageSource = new
	// ResourceBundleMessageSource();
	// messageSource.setBasename("messages");
	// return messageSource;
	// }
}
