package com.smartps.util;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.smartps.dao.ReceptorEmailDao;
import com.smartps.model.ReceptorEmail;

public class GmailSender {
	Session session;
	public GmailSender(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("smartps.utn","habilitacion2016");
				}
			});
	}
	
	//Note that the HTML should not contain the <html>, <head> or <body>
	public void sentMessage(String sentTo, String HTMLMessageContent){
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("smartps.utn@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(sentTo));
			message.setSubject("Últimas deciciones del consejo - Practica Supervisada");
			message.setContent(HTMLMessageContent, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Email enviado a "+sentTo);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sentAllMessages(String HTMLMessageContent){
		List<ReceptorEmail> list = new ReceptorEmailDao().getAll();
		for (ReceptorEmail re : list){
			sentMessage(re.getEmail(), HTMLMessageContent);
		}
	}
}
