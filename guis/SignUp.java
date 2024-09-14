package guis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import spotifyclone.profile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class SignUp extends JFrame {
    JLabel l1,l2,l3,l4,l5,l6,l7;
    RoundedButton b1;
    JTextField t1,t2,t3,t4;
    JPanel p1,p2,p3,p4;
    SignUp() throws IOException{
        setSize(1600,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
    
        // add(b1);
        File f = new File("images\\whitelogo.png");
        BufferedImage bi= ImageIO.read(f);
        Image rbi=bi.getScaledInstance( 50,50, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(rbi);
        
        l1= new JLabel(logo);
        p1= new JPanel();
        p1.setBackground(Color.BLACK);
        p1.setLayout(new BorderLayout());
        p1.setBorder(BorderFactory.createEmptyBorder(20, 19, 20, 19));
        l1.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        p1.add(l1,BorderLayout.WEST);
        l2= new JLabel("Spotify");
        l2.setFont(new Font("Gotham Circular",Font.BOLD,24));
        l2.setForeground(Color.white);
        p1.add(l2,BorderLayout.CENTER);
        add(p1,BorderLayout.NORTH);
        setBackground(Color.DARK_GRAY);
        l3= new JLabel("Email");
        l3.setFont(new Font("Gotham Circular", Font.PLAIN, 15));
        l3.setForeground(Color.white);
        l4= new JLabel("Set Password");
        l4.setFont(new Font("Gotham Circular", Font.PLAIN, 15));
        l4.setForeground(Color.white);
        t1= new JTextField();
        t2= new JTextField();
        t1.setPreferredSize(new Dimension(250,40));
        t2.setPreferredSize(new Dimension(180,40));
        t1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        t2.setBorder(BorderFactory.createLineBorder(Color.white,2));
        t1.setBackground(Color.BLACK);
        t2.setBackground(Color.BLACK);
        t1.setForeground(Color.white);
        t2.setForeground(Color.white);
        l6= new JLabel("Name");
        l7= new JLabel("Username");
        l6.setForeground(Color.white);
        l7.setForeground(Color.white);
        t3= new JTextField();
        t4= new JTextField();
        t3.setBorder(BorderFactory.createLineBorder(Color.white,2));
        t3.setBackground(Color.BLACK);
        t3.setForeground(Color.white);
        t4.setBorder(BorderFactory.createLineBorder(Color.white,2));
        t4.setForeground(Color.white);
        t4.setBackground(Color.BLACK);
        t3.setBorder(BorderFactory.createLineBorder(Color.white,2));
        
        p2= new RoundedPanel(35);
        p2.setLayout(new GridLayout(10,1));
        p2.setBackground(Color.BLACK);
        p2.setBorder(BorderFactory.createEmptyBorder(35,100,10,100));
        p2.add(l3);
        p2.add(t1);
        p2.add(l6);
        p2.add(t3);
        p2.add(l7);p2.add(t4);
        p2.add(l4);
        p2.add(t2);
        p3= new JPanel();
        p3.setBackground(new Color(16,16,16));
        p3.setLayout(new FlowLayout());
        p3.setBorder(BorderFactory.createEmptyBorder(50,200,100,200));
        
        
        b1= new RoundedButton("Sign Up", 25);
        b1.addMouseListener((new MouseAdapter() {
             public void mouseEntered(MouseEvent me){
                b1.setBackground(Color.WHITE);
                b1.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent me){
                b1.setBackground(new Color(30,215,96));
                b1.setForeground(Color.white);
            }
        }));
        b1.setForeground(Color.white);
        b1.setBorder(BorderFactory.createEmptyBorder());
        b1.setPreferredSize(new Dimension(260,36));
        p4= new JPanel();
        p4.add(b1);
        p4.setBackground(Color.black);
        p2.add(p4);
        l5= new JLabel("Already have an Account? Login here.");
        l5.setForeground(Color.gray);
        l5.setFont(new Font("Gotham Circular",Font.BOLD,13));
        l5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                dispose();
                try {
                    landingpage li= new landingpage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent me){
                l5.setForeground(Color.white);
            }
            public void mouseExited(MouseEvent me){
                l5.setForeground(Color.gray);
            }
        });
        p2.add(l5);

        p3.add(p2);

        add(p3);

        MyActionListener a= new MyActionListener();
        b1.addActionListener(a);
    }
    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try{
            if(ae.getActionCommand().equals("Sign Up")){
                profile.alreadyexists(t1.getText());

                Boolean success= profile.Registerprofile(t1.getText(),t2.getText(),t3.getText(),t4.getText());
                if(!success){
                    if(profile.alreadyexists(t1.getText())){
                    JOptionPane.showMessageDialog(rootPane, "This profile already exists, You need to login.");
                    dispose();
                    landingpage li= new landingpage();

                    }
                    else{
                        JOptionPane.showMessageDialog(rootPane,"Enter Valid Details\nvalid email address\nfulll name\nusername including digit with no space\npassword with atleast one capital letter,one digit,and underscore");
                    }
                }
                else if(success){
                JOptionPane.showMessageDialog(rootPane, "Congrats Your Profile is Registered\nLogin Now");
                dispose();
                landingpage lp= new landingpage();
                }   
            }
            }
            catch(IOException | HeadlessException | ClassNotFoundException e){
                System.out.println("exception in signup");
            }
        }
        

    }
    class RoundedButton extends JButton {
        private int cornerRadius;
    
        public RoundedButton(String text,int n) {

            super(text);
            cornerRadius=n;
            setContentAreaFilled(false);
            setFocusPainted(false);
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
    
            // Create a rounded rectangle shape
            Shape roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    
            // Set the background color
            g2d.setColor(new Color(30,215,96));
            g2d.fill(roundedRectangle);
    
            // Draw the border
            g2d.setColor(new Color(30,215,96));
            g2d.draw(roundedRectangle);
    
            g2d.dispose();
    
            super.paintComponent(g);
        }
    }
    class RoundedPanel extends JPanel {
        private int cornerRadius;
    
        public RoundedPanel(int cornerRadius) {
            this.cornerRadius = cornerRadius;
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
    
            // Set background color (change as needed)
            g2d.setColor(getBackground());
            g2d.fill(roundedRectangle);
    
            // Set border color (change as needed)
            g2d.setColor(getBackground());
            g2d.draw(roundedRectangle);
    
            g2d.dispose();
        }
    }

    
}