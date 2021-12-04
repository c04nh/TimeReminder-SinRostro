import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
       graphics.fillRoundRect(0, 0, width, height, 0, 0); 
       FontMetrics fontMetrics = graphics.getFontMetrics(); 
       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
       int textX = (width - stringBounds.width) / 2; 
       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
       graphics.setColor(Color.white); 
       graphics.setFont(getFont()); 
       graphics.drawString(getText(), textX, textY); 
       graphics.dispose(); 
       super.paintComponent(g); 
       }
    }
 
public class GUI
{
    // ���� 
    public static void main(String args[]) throws IOException, AddressException, MessagingException
    {
    	Color b =new Color(83, 96, 120);
        JFrame f = new JFrame();
        Font font = new Font("Mermaid", Font.BOLD, 23);
        Font smallfont = new Font("Mermaid", Font.PLAIN, 15);
        JTextField t1 = new JTextField(""){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        JLabel email = new JLabel("Email");
        email.setBounds(30, 18, 100, 50); 
        email.setFont(font);
        email.setForeground(Color.white);
        f.add(email);
        t1.setBounds(25, 60, 350, 35); 
        t1.setFont(smallfont);
        f.add(t1);
        JLabel title = new JLabel("Title");
        title.setBounds(30, 98, 100, 50); 
        title.setFont(font);
        title.setForeground(Color.white);
        f.add(title);
        JTextField t2 = new JTextField(""){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        t2.setBounds(25, 140, 350, 35);
        t2.setFont(smallfont);
        f.add(t2);
        JLabel content = new JLabel("Content");
        content.setBounds(30, 178, 100, 50); 
        content.setFont(font);
        content.setForeground(Color.white);
        f.add(content);
        JTextArea t3 = new JTextArea("");
        t3.setFont(smallfont);
        t3.setLineWrap(true);
        int v = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
        int h = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ;
        JScrollPane scrollPane = new JScrollPane(t3, v, h){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        scrollPane.setBounds(25, 220, 350, 270); 
        f.add(scrollPane);
        RoundedButton btn = new RoundedButton("send");
        btn.setBounds(265, 505, 110, 35); 
        btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        Mail mail = new Mail();
				mail.setupServerProperties();
				String usermail = t1.getText();
		        String title = t2.getText();
		        String content = t3.getText();
				try {
					mail.draftEmail(usermail, title, content);
				} catch (MessagingException | IOException e1) {
					// TODO Auto-generated catch block
					
				}
				try {
					mail.sendEmail();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					JLabel label = new JLabel("Wrong Email Address!");
					label.setFont(new Font("Mermaid", Font.PLAIN, 18));
					JOptionPane.showMessageDialog(null, label, "Time Reminder", 1);
				}
		        t1.setText("");
		        t2.setText("");
		        t3.setText("");
			}
        	
        });
        f.add(btn);
        f.setSize(400, 600);
        f.setLayout(null);  
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(b);
        f.setTitle("Time Reminder");
        
	}
}