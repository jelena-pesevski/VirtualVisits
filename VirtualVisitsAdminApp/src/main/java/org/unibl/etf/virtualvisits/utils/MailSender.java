package org.unibl.etf.virtualvisits.utils;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.unibl.etf.virtualvisits.models.dao.UserDAO;
import org.unibl.etf.virtualvisits.models.dto.User;


public class MailSender {

	private static String username;
	private static String password;
	private static Properties properties;
	
	static {
		ResourceBundle bundle=PropertyResourceBundle.getBundle("org.unibl.etf.virtualvisits.utils.MailSender");
		username=bundle.getString("username");
		password=bundle.getString("password");
		
		
		properties = new Properties();

	    Enumeration<String> keys = bundle.getKeys();
	    while (keys.hasMoreElements()) {
	      String key = keys.nextElement();
	      properties.put(key, bundle.getString(key));
	    }
	}
	

	public static void sendNewPassword(Integer userId, String newPassword) {	
		User user=UserDAO.getById(userId);
		if(user==null) {
			return;
		}
		
		Session session=Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});
	
		
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getMail()));
			message.setSubject("IP virtual visits - password reset");
			message.setText(" New password for "+ user.getUsername()+" is:"+ newPassword);
			Transport.send(message);
		}catch(MessagingException e) {
			e.printStackTrace();
			System.out.println("Mail can't be sent");
		}
		
	}
}
