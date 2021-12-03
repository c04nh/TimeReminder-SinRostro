import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;
import javax.swing.border.Border;

class RoundedButton extends JButton {
    public RoundedButton() { super(); decorate(); } 
    public RoundedButton(String text) { super(text); decorate(); } 
    public RoundedButton(Action action) { super(action); decorate(); } 
    public RoundedButton(Icon icon) { super(icon); decorate(); } 
    public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    @Override 
    protected void paintComponent(Graphics g) {
       Color c = new Color(255,247,242);
       Color o = new Color(247,99,12);
       int width = getWidth(); 
       int height = getHeight(); 
       Graphics2D graphics = (Graphics2D) g; 
       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
       if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
       else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
       else { graphics.setColor(c); } 
       graphics.fillRoundRect(0, 0, width, height, 10, 10); 
       FontMetrics fontMetrics = graphics.getFontMetrics(); 
       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
       int textX = (width - stringBounds.width) / 2; 
       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
       graphics.setColor(o); 
       graphics.setFont(getFont()); 
       graphics.drawString(getText(), textX, textY); 
       graphics.dispose(); 
       super.paintComponent(g); 
       }
    }
 
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
        
        JTextField t1 = new JTextField(""){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        JLabel email = new JLabel("메일 주소");
        email.setBounds(55, 50, 100, 50); 
        f.add(email);
        t1.setBounds(50, 90, 300, 40); 
        f.add(t1);
        JLabel title = new JLabel("메일 제목");
        title.setBounds(55, 140, 100, 50); 
        f.add(title);
        JTextField t2 = new JTextField(""){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        t2.setBounds(50, 180, 300, 40);
        f.add(t2);
        JLabel content = new JLabel("메일 내용");
        content.setBounds(55, 230, 100, 50); 
        f.add(content);
        JTextArea t3 = new JTextArea("");
        t3.setLineWrap(true);
        int v = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
        int h = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ;
        JScrollPane scrollPane = new JScrollPane(t3, v, h){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        scrollPane.setBounds(50, 270, 300, 200); 
        f.add(scrollPane);
        RoundedButton btn = new RoundedButton("send");
        btn.setBounds(120, 500, 160, 40); 
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