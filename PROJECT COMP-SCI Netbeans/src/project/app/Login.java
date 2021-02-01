package project.app;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.util.regex.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Login extends JFrame{

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    public void initUI(){
        createLogin();
        setVisible(true);
    }
    
    public JPanel createLogin() {
        
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        
        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        user_label.setForeground(Color.WHITE);
        user_label.setFont(new Font("Serif",Font.PLAIN,20));
        userName_text = new JTextField(20);
        
        // Password
        password_label = new JLabel();
        password_label.setText("Password :");
        password_label.setForeground(Color.WHITE);
        password_label.setFont(new Font("Serif",Font.PLAIN,20));
        password_text = new JPasswordField(20);

        // SubmitButton
        submit = new JButton("LOGIN");
        
         String BACKHGROUND_IMAGE_URL = "./src/project/resources/logo.png";
        final ImageIcon backgroundImage = new ImageIcon(BACKHGROUND_IMAGE_URL);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(BACKHGROUND_IMAGE_URL));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        //Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        //ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel picLabel = new JLabel(new ImageIcon(img));
        
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(picLabel,constraints);
        
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(user_label,constraints);
        
        constraints.gridx = 1;
        panel.add(userName_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(password_label,constraints);
        
        constraints.gridx = 1;
        panel.add(password_text,constraints);

        constraints.gridy = 3;
        //constraints.weighty = 1;
        //constraints.anchor = GridBagConstraints.CENTER;
        panel.add(submit,constraints);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener((event) -> submit_action());
        add(panel);
        setTitle("Please Login Here !");
        pack();
        panel.setOpaque(false);
        return panel;
    }

    public boolean submit_action() {
        SQL sql = new SQL();
        String userName = userName_text.getText();
        String password = password_text.getText();
        boolean userName_check = Pattern.matches("[a-zA-z0-9]+", userName);
        boolean password_check = Pattern.matches("[a-zA-Z0-9!@#$%^&]+", password);
        if (userName_check == false || password_check == false)
            return false;
            
        String query = "SELECT * FROM login WHERE username='"+userName+"' AND password='"+password+"'";
        System.out.println(query);
        try{
            ResultSet rs =sql.execute(query);
        if (rs.next()) {
            //message.setText(" Hello " + userName+ "");
            rs.close();
            return true;
        } 
        else {
            rs.close();
            System.out.println("False");
            //message.setText(" Invalid user.. ");
            return false;
        }
        }
        catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        return false;
       }
    
    }      
    public static void main(String[] args) {
        var login = new Login();
        login.initUI();
    }
}