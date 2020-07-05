package project.app;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.*;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
//import java.util.GregorianCalendar;
import org.jdatepicker.impl.*;
import javax.swing.JScrollPane;

public class NewEmployee extends JFrame
{   
    JPanel panel;
    JLabel title_label2,name_label2,number_label2,email_label2,joindate_label,designation_label,message;
    JTextField name_label2_text,number_label2_text,email_label2_text,joindate_label_text;
    JButton submit, cancel;
    JComboBox desiglist;
    JDatePickerImpl joindate;
    String[] designation = { "DRIVER", "CLEANER", "MANAGER"};

    public void initUI()
    {
        newEmployee();
        setVisible(true);
        submit.addActionListener((event) -> employeeaddaction (this));
    }

    public JScrollPane newEmployee() 
    {
        desiglist = new JComboBox(designation);
        desiglist.setSelectedIndex(0);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20, 20, 20, 20);

        //TITLE
        title_label2 = new JLabel();
        title_label2.setText(" CREATE NEW EMPLOYEE ");
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
        //------
        joindate = (new JDatePicker()).datepanel();
        //joindate.getModel().setDate(2020,1,1);
        //joindate.getModel().setSelected(true);
        //------
            
        //DESIGNATION
        designation_label = new JLabel();
        designation_label.setText("DESIGNATION:");
        designation_label.setForeground(Color.WHITE);
        designation_label.setFont(new Font("Century",Font.BOLD,20));

        // SubmitButton
        submit = new JButton("ADD EMPLOYEE");

        constraints.anchor = GridBagConstraints.NORTH;    
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 1;
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
        panel.add(joindate_label,constraints);

        constraints.gridx = 1;
        panel.add(joindate,constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        panel.add(email_label2,constraints);

        constraints.gridx = 1;
        panel.add(email_label2_text,constraints);

        constraints.gridx = 1;
        constraints.gridy = 14;

        panel.add(submit,constraints);

        //constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridwidth = 2;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(40, 20, 0, 20);
        panel.add(title_label2,constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 5, 5, 5);
        JSeparator s = new JSeparator();  
        panel.add(s,constraints);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        add(panel, BorderLayout.CENTER);
        setTitle("Enter employee details here !");
        pack();
        panel.setOpaque(false);
        JScrollPane panelPane = new JScrollPane(panel);
        panelPane.setOpaque(false);
        panelPane.getViewport().setOpaque(false);
        //add(panelPane, BorderLayout.CENTER);
        //add(panelPane);
        return panelPane;
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
        // ------------
        Date selectedDate = (Date) joindate.getModel().getValue();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String datestr = formatter.format(selectedDate);
        System.out.println("Date is : "+datestr);
        //joindate.getModel().setDate(2010,2,3);
        //joindate.getModel().setSelected(true);
        // ------------
        boolean num_check = Pattern.matches("[0-9]{10}", Number);
        boolean mail_check = Pattern.matches("[a-zA-Z_]+@[a-z]{1,10}\\.[a-z]{2,3}", Email);
        if (num_check == false)
        {
            JOptionPane.showMessageDialog(jframe, "Invalid Number. Try again","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }    
        if (mail_check == false)
        {
            JOptionPane.showMessageDialog(jframe, "Invalid Email. Try again","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String check_query = String.format("SELECT * FROM EMPLOYEE WHERE NAME='%s' AND EMAIL='%s';", Name, Email);
        try
        {
            ResultSet rs =sql.execute(check_query);
            if (rs.next()) {
                //message.setText(" Hello " + userName+ "");
                rs.close();
                JOptionPane.showMessageDialog(jframe, "Existing Employee","Error",JOptionPane.ERROR_MESSAGE); 
                return false;
            } 
        }
        catch ( HeadlessException | SQLException e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }

        String query = String.format("INSERT INTO EMPLOYEE (NAME,NUMBER,EMAIL,JOINDATE,DESIGNATION) VALUES ('%s','%s','%s','%s','%s');",
                                      Name, Number, Email,datestr,Designation);   
        System.out.println(query);
        try
        {
            if (sql.updateQuery(query)!=0) 
            {
                name_label2_text.setText("");
                number_label2_text.setText("");
                email_label2_text.setText("");
                //joindate_label_text.setText("");
                ((JTextField) desiglist.getEditor().getEditorComponent()).setText("");
                joindate.getModel().setSelected(false);
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
        catch ( HeadlessException e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
    }
}

