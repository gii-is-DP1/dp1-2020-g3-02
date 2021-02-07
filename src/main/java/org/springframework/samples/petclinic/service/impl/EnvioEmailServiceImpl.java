package org.springframework.samples.petclinic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.samples.petclinic.service.EnvioEmailService;
import org.springframework.stereotype.Component;


@Component
public class EnvioEmailServiceImpl implements EnvioEmailService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendEmail(String to, String subject, String content) {
		 SimpleMailMessage email = new SimpleMailMessage();

	        email.setTo(to);
	        email.setSubject(subject);
	        email.setText(content);

	        mailSender.send(email);
		
	}
	
}
