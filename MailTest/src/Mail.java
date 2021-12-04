import java.awt.Font;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Mail
{
		
	Session newSession = null;
	MimeMessage mimeMessage = null;
	public static void main(String args[]) throws AddressException, MessagingException, IOException
	{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Mail mail = new Mail();
//		mail.setupServerProperties();
//		String usermail = "nhsally@naver.com";
//		String title = "타이틀";
//		String content = "콘텐츠";
//		mail.draftEmail(usermail, title, content);
//		mail.sendEmail();
	}

	void sendEmail() throws MessagingException {
		String fromUser = "Mirim.TimeReminder@gmail.com";  //Enter sender email id
		String fromUserName = "Time Reminder";
		String fromUserPassword = "alflathvmxmdnpdjcofflswl";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		try {
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			JLabel label = new JLabel("Email successfully sent!");
			label.setFont(new Font("Mermaid", Font.PLAIN, 18));
			JOptionPane.showMessageDialog(null, label, "Time Reminder", 1);
		}catch (NullPointerException e) {
			JLabel label = new JLabel("EMPTY!");
			label.setFont(new Font("Mermaid", Font.PLAIN, 18));
			JOptionPane.showMessageDialog(null, label, "Time Reminder", 1);
		}
		
	}

	MimeMessage draftEmail(String usermail, String title, String content) throws AddressException, MessagingException, IOException {
		String[] emailReceipients = {usermail}; 
		String emailSubject = title;
		String emailBody = content;
		mimeMessage = new MimeMessage(newSession);
		
		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);

	    
		String BODY = String.join(
		        System.getProperty("line.separator"),
		        "<p>"+emailBody+"</p>"
		    );
		
        MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(BODY, "text/html;charset=utf-8");
		MimeMultipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(bodyPart);
		mimeMessage.setContent(multiPart);
		return mimeMessage;
	}

	void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties,null);
	}
	
}	