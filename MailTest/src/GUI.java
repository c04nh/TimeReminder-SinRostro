import java.awt.Color;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class GUI
{
    // 메인 
    public static void main(String args[]) throws IOException, AddressException, MessagingException
    {
//    	Mail mail = new Mail();
//		mail.setupServerProperties();
//		String usermail = "nhsally@naver.com";
//		String title = "타이틀";
//		String content = "콘텐츠";
//		mail.draftEmail(usermail, title, content);
//		mail.sendEmail();
    	Color b=new Color(83, 96, 120);
        JFrame f= new JFrame();
        f.setTitle("Time Reminder");
        f.setSize(400, 600);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        JPanel c=new JPanel();  // 이렇게 컬러값을 생성 후
        c.setBackground(b);
        f.add(c);  
	}
}