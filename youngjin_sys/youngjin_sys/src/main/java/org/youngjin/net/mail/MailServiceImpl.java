package org.youngjin.net.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messageSource;
	public void sendMail() {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("rhkdduf63@gmail.com");
		message.setSubject("그래요");
		message.setText("메일테스트에요");
		 
		message.setTo("rhkdduf63@gmail.com");
		mailSender.send(message);
	}
	
	@Override
	public void sendMail(String subject, String text, String fromUser,
			String toUser, String[] toCC) {
		// TODO Auto-generated method stub
		
	}
}
