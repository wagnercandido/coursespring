package com.candidowagner.coursespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.candidowagner.coursespring.services.EmailService;
import com.candidowagner.coursespring.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}