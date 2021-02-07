package org.springframework.samples.petclinic.service;

public interface EnvioEmailService{
	
	public abstract void sendEmail(String to, String subject, String content);
	
}
