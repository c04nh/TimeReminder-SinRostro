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

import com.mysql.cj.xdevapi.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mail
{
		
	Session newSession = null;
	MimeMessage mimeMessage = null;
	public static void main(String args[]) throws AddressException, MessagingException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		DBConnection dbConn = new DBConnection();
//		dbConn.connect();
		Mail mail = new Mail();
		mail.setupServerProperties();
//		String[] date = dbConn.writedate.split("-");
//		mail.draftEmail(usermail, title, content, date);
		String usermail = br.readLine();
		String title = br.readLine();
		String content = br.readLine();
		mail.draftEmail(usermail, title, content);
		mail.sendEmail();
	}

	private void sendEmail() throws MessagingException {
		String fromUser = "Mirim.TimeReminder@gmail.com";  //Enter sender email id
		String fromUserName = "Time Reminder";
		String fromUserPassword = "alflathvmxmdnpdjcofflswl";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email successfully sent!!!");
	}

	private MimeMessage draftEmail(String usermail, String title, String content) throws AddressException, MessagingException, IOException {
		String[] emailReceipients = {usermail}; 
		String emailSubject = "[Time Reminder]";
		String emailtitle = title;
		String emailBody = content;
		mimeMessage = new MimeMessage(newSession);
		
		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);

	    
		String BODY = String.join(
		        System.getProperty("line.separator"),
		        "<h2>"+emailtitle+"</h2><br><p>"+emailBody+"</p>"
		    );
		
        MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(BODY, "text/html;charset=utf-8");
		MimeMultipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(bodyPart);
		mimeMessage.setContent(multiPart);
		return mimeMessage;
	}

	private void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties,null);
	}
	
}	

//class DBConnection {
//	Connection conn;
//	java.sql.Statement state = null;
//	
//	String title, content, usermail, date, writedate;
//
//	public void connect() {
//		String url = "jdbc:mysql://localhost:3306/TIMEREMINDER?serverTimezone=UTC";
//		String user = "root";
//		String password = "111111";
//		String driverName = "com.mysql.cj.jdbc.Driver";
//		
//		try {
//			Class.forName(driverName);
//			conn = DriverManager.getConnection(url, user, password);
//			
//			state = conn.createStatement();
//			String sql = "SELECT title, content, mail, senddate, DATE_FORMAT(writedate, '%Y-%m-%d') FROM timereminder WHERE DATE_FORMAT(senddate, '%Y-%m-%d:%H%i') = DATE_FORMAT(NOW(), '%Y-%m-%d:%H%i')";
//			ResultSet rs = ((java.sql.Statement) state).executeQuery(sql);
//			while(rs.next()) {
//				title = rs.getString(1);
//				content = rs.getString(2);
//				usermail = rs.getString(3);
//				date = rs.getString(4);
//				writedate = rs.getString(5);
//			}
//			
//		} catch (ClassNotFoundException e) {
//			System.out.println("[로드 오류]\n" + e.getStackTrace());
//		} catch (SQLException e) {
//			System.out.println("[연결 오류]\n" + e.getStackTrace());
//		}
//	}
//	
//}