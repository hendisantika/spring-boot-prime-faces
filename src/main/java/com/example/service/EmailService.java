package com.example.service;

import java.util.List;

import org.apache.commons.mail.EmailException;

public interface EmailService {
	public void sendEmail(List<String> to, List<String> cc, String subject, String content) throws EmailException;

}
