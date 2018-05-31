package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import business.model.SellTicketModel;

public class Notification {
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void main(String args[]) {
		System.out.println("Sending email...");

		String date = "29.05.2018";
		String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		System.out.println(date + "\n" + currentDate);
		System.out.println(date.compareTo(currentDate));

		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}
 
	public static void registrationMail(String email)  {
		try {
			// Step1
			System.out.println("\n 1st ===> setup Mail Server Properties..");
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			System.out.println("Mail Server Properties have been setup successfully..");
 
			// Step2
			System.out.println("\n\n 2nd ===> get Mail Session..");
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
			generateMailMessage.setSubject("Registration");
			String emailBody = "Your account has been successfully created! Welcome!" + "<br><br> Regards, <br>Admin";
			generateMailMessage.setContent(emailBody, "text/html");
			System.out.println("Mail Session has been created successfully..");
 
			// Step3
			System.out.println("\n\n 3rd ===> Get Session and Send mail");
			Transport transport = getMailSession.getTransport("smtp");
 
			transport.connect("smtp.gmail.com", "laboratory.software.design@gmail.com", "andreeamara");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			
		}
	}
	
	public static void notifyReservation(String email, SellTicketModel ticket)  {
		try {
			// Step1
			System.out.println("\n 1st ===> setup Mail Server Properties..");
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			System.out.println("Mail Server Properties have been setup successfully..");
			 
			// Step2
			System.out.println("\n\n 2nd ===> get Mail Session..");
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			if(generateMailMessage == null) {
				System.out.println("generateMailMessage");
			}
			if(email == null) {
				System.out.println("email");
			}
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
			generateMailMessage.setSubject("Registration");
			String emailBody = "You have made a new reservation." + "<br>Reservation details:" 
					+ "<br>From city: " + ticket.getFromCity()
					+ "<br>To city: " + ticket.getToCity()
					+ "<br>Date: " + ticket.getDate()
					+ "<br>Nr. of tickets: " + ticket.getNumberOfTickets()
					+ "<br>Bus Agency: " + ticket.getBusAgency()
					+ "<br><br> Regards, <br>Admin";
			generateMailMessage.setContent(emailBody, "text/html");
			
			// Step3
			System.out.println("\n\n 3rd ===> Get Session and Send mail");
			Transport transport = getMailSession.getTransport("smtp");
 
			transport.connect("smtp.gmail.com", "laboratory.software.design@gmail.com", "andreeamara");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
