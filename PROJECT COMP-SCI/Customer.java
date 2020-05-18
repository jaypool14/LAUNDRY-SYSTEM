import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Customer extends JFrame{

    JPanel panel;
    JLabel name_label,number_label,address_label,email_label, message;
    JTextField name_label_text,number_label_text,address_label_text,email_label_text;
    JPasswordField password_text;
    JButton submit, cancel;
    public void initUI(){
        createCustomer();
        setVisible(true);
    }
    
    public JPanel createCustomer() {
        
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        // NAME
        name_label = new JLabel();
        name_label.setText("NAME :");
        name_label.setForeground(Color.WHITE);
        name_label.setFont(new Font("Serif",Font.PLAIN,20));
        name_label_text = new JTextField(20);
        
        // NUMBER
        number_label = new JLabel();
        number_label.setText("NUMBER :");
        number_label.setForeground(Color.WHITE);
        number_label.setFont(new Font("Serif",Font.PLAIN,20));
        number_label_text = new JTextField(20);
        
        //ADDRESS
        address_label = new JLabel();
        address_label.setText("NUMBER :");
        address_label.setForeground(Color.WHITE);
        address_label.setFont(new Font("Serif",Font.PLAIN,20));
        address_label_text = new JTextField(20);
        
        //EMAIL
        email_label = new JLabel();
        email_label.setText("NUMBER :");
        email_label.setForeground(Color.WHITE);
        email_label.setFont(new Font("Serif",Font.PLAIN,20));
        email_label_text = new JTextField(20);

        // SubmitButton
        submit = new JButton("SUBMIT");
        
                
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(name_label,constraints);
        
        constraints.gridx = 1;
        panel.add(name_label_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(number_label,constraints);
        
        constraints.gridx = 1;
        panel.add(number_label_text,constraints);
        
          constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(address_label,constraints);
        
        constraints.gridx = 1;
        panel.add(address_label_text,constraints);
        
          constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(email_label,constraints);
        
        constraints.gridx = 1;
        panel.add(password_text,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(submit,constraints);

        message = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;

        panel.add(message,constraints);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener((event) -> submit_action());
        add(panel, BorderLayout.CENTER);
        setTitle("Enter customer details here !");
        pack();
        panel.setOpaque(false);
        return panel;
    }

    public int Customer_id() {
 
    }
    
        
    public static void main(String[] args) {
        var login = new Customer();
        Customer.initUI();
    }
}
    
    



