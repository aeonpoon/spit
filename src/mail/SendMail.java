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
		
		Session session = Session.getInstance(props, 
			new javax.mail.Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication("onestopspit@gmail.com", "!Q@W#$ER5t");
				}
			}
		);
		return session;
	}
	
	public boolean welcomeMessage(String email, String name){
		
		Session session = requetSession();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("onestopspit@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Welcome to SPIT!");
			msg.setContent("Dear "+name+", <br/><br/>We One Stop SPIT Team, would like to warmly welcome you to use our web store. "
					+ "Do email us anytime if you had encounter any technical difficulties, we hope that you will enjoy our "
					+ "one stop IT services.<br/><br/>Thank you for using SPIT your one stop IT Store!"
					+ "<br/><br/>Your Sincerely,<br/><b>Team One Stop SPIT</b>", "text/html; charset=utf-8");
			Transport.send(msg);
			
			System.out.println("Email Sent");
			return true;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	public boolean sendforgetPass(String email, String pass){
		
		Session session = requetSession();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("onestopspit@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Welcome to SPIT! - Recover Password");
			msg.setText("Here is your password: " + pass +"<br/>Thank you for using SPIT your one stop IT Store!");
			Transport.send(msg);
			
			System.out.println("Email Sent");
			return true;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	public void memberpurchase(String email, String data, String name){
		
		Session session = requetSession();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("onestopspit@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("SPIT Transaction List!");
			msg.setContent("Dear "+name+", <br/><br/>Here is your order:<br/><br/>"+data+"<br/>Thank you for using SPIT your one stop IT Store!"
					+ "<br/><br/>Your Sincerely,<br/><b>Team One Stop SPIT</b>", "text/html; charset=utf-8");
			Transport.send(msg);
			
			System.out.println("Email Sent");
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public boolean sendMonthlyReport(String data){
		
		Session session = requetSession();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("onestopspit@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("onestopspit@gmail.com"));
			msg.setSubject("SPIT Monthly Report");
			msg.setContent("Here is the report:<br/> " + data +"<br/>Send by one stop IT Store!", "text/html; charset=utf-8");
			Transport.send(msg);
			
			System.out.println("Email Sent");
			return true;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return false;
		}
	}
	
	public boolean sendYearlyReport(String data){
		
		Session session = requetSession();
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("onestopspit@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("onestopspit@gmail.com"));
			msg.setSubject("SPIT Yearly Report");
			msg.setContent("Here is the report:<br/> " + data +"<br/>Send by one stop IT Store!", "text/html; charset=utf-8");
			Transport.send(msg);
			
			System.out.println("Email Sent");
			return true;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return false;
		}
	}
}
