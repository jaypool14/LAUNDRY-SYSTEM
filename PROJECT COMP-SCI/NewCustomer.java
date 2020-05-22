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
import javax.swing.JTextArea;

public class NewCustomer extends JFrame{

    JPanel panel;
    JLabel title_label,name_label,number_label,address_label,email_label, message;
    JTextField name_label_text,number_label_text,email_label_text;
    JTextArea address_label_text;
    JPasswordField password_text;
    JButton submit, cancel;
    public void initUI(){
        newCustomer();
        setVisible(true);
    }
    
    public JPanel newCustomer() {
        
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        
        //TITLE
        title_label = new JLabel();
        title_label.setText("CREATE NEW CUSTOMER");
        title_label.setForeground(Color.WHITE);
        title_label.setFont(new Font("Century",Font.BOLD,40));
        
        // NAME
        name_label = new JLabel();
        name_label.setText("NAME :");
        name_label.setForeground(Color.WHITE);
        name_label.setFont(new Font("Century",Font.BOLD,20));
        name_label_text = new JTextField(20);
        
        // NUMBER
        number_label = new JLabel();
        number_label.setText("NUMBER :");
        number_label.setForeground(Color.WHITE);
        number_label.setFont(new Font("Century",Font.BOLD,20));
        number_label_text = new JTextField(20);
        
        //ADDRESS
        address_label = new JLabel();
        address_label.setText("ADDRESS :");
        address_label.setForeground(Color.WHITE);
        address_label.setFont(new Font("Century",Font.BOLD,20));
        address_label_text = new JTextArea(5,20);
        
        //EMAIL
        email_label = new JLabel();
        email_label.setText("EMAIL :");
        email_label.setForeground(Color.WHITE);
        email_label.setFont(new Font("Century",Font.BOLD,20));
        email_label_text = new JTextField(20);

        // SubmitButton
        submit = new JButton("ADD CUSTOMER");
        
        constraints.anchor = GridBagConstraints.WEST;
        
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(name_label,constraints);
        
        constraints.gridx = 1;
        panel.add(name_label_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(number_label,constraints);
        
        constraints.gridx = 1;
        panel.add(number_label_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(address_label,constraints);
        
        constraints.gridx = 1;
        panel.add(address_label_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(email_label,constraints);
        
        constraints.gridx = 1;
        panel.add(email_label_text,constraints);

        constraints.gridx = 1;
        constraints.gridy = 12;
        constraints.gridwidth = 2;
        //constraints.anchor = GridBagConstraints.NORTH;
        panel.add(submit,constraints);

        
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 0;
        //constraints.ipadx = 4;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(title_label,constraints);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener((event) -> customeraddaction ());
        add(panel, BorderLayout.CENTER);
        setTitle("Enter customer details here !");
        pack();
        panel.setOpaque(false);
        return panel;
    }
       
    public static void main(String[] args) {
        var login = new NewCustomer();
        login.initUI();
    }
    
    public boolean customeraddaction ()
    {SQL sql = new SQL();
        String Name = name_label_text.getText();
        String Address = address_label_text.getText();
        String Number =  number_label_text.getText();
        String Email =  email_label_text.getText();
        
     
        String query = String.format("INSERT INTO customer (name,number, address,email) VALUES ('%s','%s','%s','%s');", Name, Number, Address, Email);
        System.out.println(query);
        try{
        if (sql.updateQuery(query)==0) {
            //message.setText(" Hello " + userName+ "");
            return true;
        } 
        else {
            System.out.println("False");
            //message.setText(" Invalid user.. ");
            return false;
        }
       }
        catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        return false;
        
    }

}}
    



