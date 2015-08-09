package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public Session requetSession(){
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props, 
			new javax.mail.Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication("onestopspit@gmail.com", "!Q@W#$ER5t");
				}
			}
		);
		return session;
	}
	
	public boolean sendforgetPass(String email, String pass){
		
		Session session = requetSession();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("onestop@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Welcome to SPIT! - Recover Password");
			msg.setText("Here is your password: " + pass +"\nThank you for using SPIT your one stop IT Store!");
			Transport.send(msg);
			
			System.out.println("Email Sent");
			return true;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return false;
		}
	}
}
