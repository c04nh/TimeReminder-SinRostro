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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class RoundedButton extends JButton {
    public RoundedButton() { super(); decorate(); } 
    public RoundedButton(String text) { super(text); decorate(); } 
    public RoundedButton(Action action) { super(action); decorate(); } 
    public RoundedButton(Icon icon) { super(icon); decorate(); } 
    public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    @Override 
    protected void paintComponent(Graphics g) {
       Color c = new Color(206, 206, 206);
       int width = getWidth(); 
       int height = getHeight(); 
       Font smallfont = new Font("Serif", Font.BOLD, 20);
       Graphics2D graphics = (Graphics2D) g; 
       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
       if (getModel().isArmed()) { graphics.setColor(c.brighter()); } 
       else if (getModel().isRollover()) { graphics.setColor(c.darker()); } 
       else { graphics.setColor(c); } 
       graphics.fillRoundRect(0, 0, width, height, 0, 0); 
       FontMetrics fontMetrics = graphics.getFontMetrics(); 
       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
//       int textX = (width - stringBounds.width) / 2; 
//       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
       graphics.setColor(Color.white); 
       graphics.setFont(smallfont); 
       graphics.drawString(getText(), 31, 24); 
       graphics.dispose(); 
       super.paintComponent(g); 
    }
}
 
public class GUI
{
	String m;
    public static void main(String args[]) throws IOException, AddressException, MessagingException
    {
    	Color b =new Color(89, 96, 129);
    	Color c = new Color(206, 206, 206);
        JFrame f = new JFrame();
        String [] address = {"메일 선택", "daum.net", "gmail.com", "hanmail.net", "kakao.com", "nate.com", "naver.com", "outlook.com", "e-mirim.hs.kr"}; 
        Font font = new Font("Serif", Font.BOLD, 23);
        Font smallfont = new Font("Serif", Font.PLAIN, 17);
        JTextField t1 = new JTextField(""){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        JLabel email = new JLabel("Email");
        email.setBounds(28, 18, 100, 50); 
        email.setFont(font);
        email.setForeground(Color.white);
        f.add(email);
        t1.setBounds(25, 60, 150, 35); 
        t1.setFont(smallfont);
        f.add(t1);
        JLabel atsign = new JLabel("@");
        atsign.setBounds(180, 55, 40, 40); 
        atsign.setFont(font);
        atsign.setForeground(Color.white);
        f.add(atsign);
        JComboBox combo = new JComboBox(address); 
        combo.setBounds(205, 60, 170, 35); 
        combo.setFont(smallfont);
        combo.setBackground(Color.white);
        combo.setForeground(Color.black);
        for (int i = 0; i < combo.getComponentCount(); i++) 
        {
            if (combo.getComponent(i) instanceof JComponent) {
                ((JComponent) combo.getComponent(i)).setBorder(new EmptyBorder(0, 0, 0, 10));
            }


            if (combo.getComponent(i) instanceof AbstractButton) {
                ((AbstractButton) combo.getComponent(i)).setBorderPainted(false);
            }
        }
        f.add(combo);
        GUI g1 = new GUI();
        combo.addActionListener(new ActionListener() { 
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    g1.m = combo.getSelectedItem().toString();
        	}
        });
        JLabel title = new JLabel("Title");
        title.setBounds(28, 98, 100, 50); 
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
        content.setBounds(28, 178, 100, 50); 
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
        RoundedButton btn = new RoundedButton("SEND");
        btn.setBounds(265, 505, 110, 35); 
        btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        Mail mail = new Mail();
				mail.setupServerProperties();	
				String usermail = t1.getText() +"@"+ g1.m;
		        String title = t2.getText() ;
		        String content = t3.getText();
		        try {
					mail.draftEmail(usermail, title, content);
				} catch (MessagingException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					mail.sendEmail();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					JLabel label = new JLabel("Wrong Email Address!");
					label.setFont(new Font("Serif", Font.PLAIN, 18));
					JOptionPane.showMessageDialog(null, label, "Time Reminder", 1);
				}
		        t1.setText("");
		        t2.setText("");
		        t3.setText("");
		        combo.setSelectedIndex(0);
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