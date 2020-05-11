import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
        
        
        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        user_label.setFont(new Font("Serif",Font.PLAIN,20));
        userName_text = new JTextField();
        
        // Password
        password_label = new JLabel();
        password_label.setText("Password :");
        password_label.setFont(new Font("Serif",Font.PLAIN,20));
        password_text = new JPasswordField();

        // SubmitButton
        submit = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(3,4));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener((event) -> submit_action());
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(250, 125);
        
        return panel;
    }

    public boolean submit_action() {
        String userName = userName_text.getText();
        String password = password_text.getText();
        if (userName.trim().equals("admin") && password.trim().equals("admin")) {
            message.setText(" Hello " + userName+ "");
            return true;
        } else {
            message.setText(" Invalid user.. ");
            return false;
        }

    }
    
        
    public static void main(String[] args) {
        var login = new Login();
        login.initUI();
    }
}
    
    


