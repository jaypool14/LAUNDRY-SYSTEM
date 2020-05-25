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

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
public class NewEmployee extends JFrame
{   
    JPanel panel;
    JLabel title_label2,name_label2,number_label2,email_label2,joindate_label,designation_label,message;
    JTextField name_label2_text,number_label2_text,email_label2_text,joindate_label_text;
    JButton submit, cancel;
    JComboBox desiglist;
    String[] designation = { "DRIVER", "CLEANER", "MANAGER"};
   
    public void initUI()
    {
       newEmployee();
       setVisible(true);
       submit.addActionListener((event) -> employeeaddaction (this));
    }

    public JPanel newEmployee() 
    {
        desiglist = new JComboBox(designation);
        desiglist.setSelectedIndex(0);
        
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        
        //TITLE
        title_label2 = new JLabel();
        title_label2.setText("CREATE NEW EMPLOYEE");
        title_label2.setForeground(Color.WHITE);
        title_label2.setFont(new Font("Century",Font.BOLD,40));
        
        // NAME
        name_label2 = new JLabel();
        name_label2.setText("NAME :");
        name_label2.setForeground(Color.WHITE);
        name_label2.setFont(new Font("Century",Font.BOLD,20));
        name_label2_text = new JTextField(20);
        
        // NUMBER
        number_label2 = new JLabel();
        number_label2.setText("NUMBER :");
        number_label2.setForeground(Color.WHITE);
        number_label2.setFont(new Font("Century",Font.BOLD,20));
        number_label2_text = new JTextField(20);
      
        //EMAIL
        email_label2 = new JLabel();
        email_label2.setText("EMAIL :");
        email_label2.setForeground(Color.WHITE);
        email_label2.setFont(new Font("Century",Font.BOLD,20));
        email_label2_text = new JTextField(20);
        
        //JOIN DATE
        joindate_label = new JLabel();
        joindate_label.setText("JOIN DATE:");
        joindate_label.setForeground(Color.WHITE);
        joindate_label.setFont(new Font("Century",Font.BOLD,20));
        joindate_label_text = new JTextField(20);
        
        //DESIGNATION
        designation_label = new JLabel();
        designation_label.setText("DESIGNATION:");
        designation_label.setForeground(Color.WHITE);
        designation_label.setFont(new Font("Century",Font.BOLD,20));
     
        // SubmitButton
        submit = new JButton("ADD EMPLOYEE");
        
        constraints.anchor = GridBagConstraints.WEST;      
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(name_label2,constraints);
        
        constraints.gridx = 1;
        panel.add(name_label2_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(number_label2,constraints);
        
        constraints.gridx = 1;
        panel.add(number_label2_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(designation_label,constraints);
        
        constraints.gridx = 1;
        panel.add(desiglist,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(email_label2,constraints);
        
        constraints.gridx = 1;
        panel.add(email_label2_text,constraints);

        constraints.gridx = 1;
        constraints.gridy = 12;
        constraints.gridwidth = 2;
    
        panel.add(submit,constraints);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 0;
       
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(title_label2,constraints);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        add(panel, BorderLayout.CENTER);
        setTitle("Enter employee details here !");
        pack();
        panel.setOpaque(false);
        return panel;
    }
    public static void main(String[] args) 
    {
        var login = new NewEmployee();
        login.initUI();
    }
    
    public boolean employeeaddaction (JFrame jframe)
    {
       SQL sql = new SQL();
       String Name = name_label2_text.getText();
       String Number = number_label2_text.getText();
       String Email = email_label2_text.getText();
       String Designation= (String)desiglist.getSelectedItem();
     
       String query = String.format("INSERT INTO EMPLOYEE (NAME,NUMBER,EMAIL,JOINDATE,DESIGNATION) VALUES ('%s','%s','%s','%s','%s');", Name, Number, Email,"1120",Designation);
       System.out.println(query);
       try
       {
           if (sql.updateQuery(query)!=0) 
           {
               //message.setText(" Hello " + userName+ "");
               JOptionPane.showMessageDialog(jframe, "Employee added"); 
               return true;
           } 
           else 
           {
               System.out.println("False");
               JOptionPane.showMessageDialog(jframe, "Invalid details","Error",JOptionPane.ERROR_MESSAGE); 
               //message.setText(" Invalid user.. ");
               return false;
           }
       }
       catch ( Exception e ) 
       {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           return false;
       }
    }
}
    


