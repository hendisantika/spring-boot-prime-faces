package com.example.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emailService")
public class EmailServiceImpl implements EmailService{

	private final static Logger logger = LoggerFactory
			.getLogger(EmailServiceImpl.class);
	
	@Value("${email.host}")
	private  String HOST;	

	@Value("${email.port}")
	private  int PORT;

	@Value("${email.from}")
	private String FROM;

	@Value("${email.password}")
	private String PASSWORD;
	
	@Override
	public void sendEmail(List<String> to, List<String> cc, String subject, String content) throws EmailException {
		Email email = new HtmlEmail();
		email.setHostName(HOST);
		email.setSmtpPort(PORT);
		email.setAuthenticator(new DefaultAuthenticator(FROM, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(FROM);
		email.setSubject(subject);
		email.setMsg(content);
		
		
		if (to != null && to.size()>0){
			for (String toAddress : to){
				email.addTo(toAddress);
			}
		}
		
		if (cc != null && cc.size()>0){
			for (String ccAddress : cc){
				email.addCc(ccAddress);
			}
		}
		
		email.send();
		logger.info("Email notification has been sent successfully....");
	}

}
