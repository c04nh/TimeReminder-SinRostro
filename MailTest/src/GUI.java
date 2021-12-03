import java.awt.Color;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;
import javax.swing.border.Border;
 
public class GUI
{
    // ∏ﬁ¿Œ 
    public static void main(String args[]) throws IOException, AddressException, MessagingException
    {
//    	Mail mail = new Mail();
//		mail.setupServerProperties();
//		String usermail = "nhsally@naver.com";
//		String title = "≈∏¿Ã∆≤";
//		String content = "ƒ‹≈Ÿ√˜";
//		mail.draftEmail(usermail, title, content);
//		mail.sendEmail();
    	Color b=new Color(83, 96, 120);
        JFrame f= new JFrame();
        
        JTextField t1 = new JTextField(""){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        t1.setBounds(50, 50, 300, 40); 
        f.add(t1);
        JTextField t2 = new JTextField(""){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        t2.setBounds(50, 100, 300, 40);
        f.add(t2);
        JTextArea t3 = new JTextArea("");
        t3.setLineWrap(true);
        int v = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
        int h = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ;
        JScrollPane scrollPane = new JScrollPane(t3, v, h){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        scrollPane.setBounds(50, 150, 300, 200); 
        f.add(scrollPane);
        JButton btn = new JButton("send");
        btn.setBounds(150, 400, 100, 40); 
        f.add(btn);
        f.setSize(400, 600);
        f.setLayout(null);  
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setTitle("Time Reminder");
        
	}
}