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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JSeparator;
import java.util.regex.*;
import javax.swing.JScrollPane;

public class ViewCustomer extends JFrame
{   JPanel panel;
    JLabel search_label,title_label,name_label,number_label,address_label,email_label, message;
    JTextField search_label_text,name_label_text,number_label_text,email_label_text;
    JTextArea address_label_text;
    JPasswordField password_text;
    private JButton search,edit,delete,save;
    JComboBox search_box;
    String ID="";
    int selected = 0;
    
    public void initUI()
    {
        viewCustomer();
        setVisible(true);
        search.addActionListener((event) -> searchcustomeraction (this));
        edit.addActionListener((event) -> editaction (this));
        delete.addActionListener((event) -> deleteaction (this));
        save.addActionListener((event) -> saveaction (this));
    }
    
    public void setlistners(JFrame jframe)
    {
        search.addActionListener((event) -> searchcustomeraction (jframe));
        edit.addActionListener((event) -> editaction (jframe));
        delete.addActionListener((event) -> deleteaction (jframe));
        save.addActionListener((event) -> saveaction (jframe));
    }

    public boolean searchcustomeraction (JFrame jframe)
    {
        String typedText = ((JTextField)search_box.getEditor().getEditorComponent()).getText();      
        System.out.println(typedText);
        String[] Details=typedText.split(" : ");
        SQL sql=new SQL();
        String query = String.format("SELECT * FROM customer WHERE name='%s' AND email='%s'",Details[0],Details[1]);
        System.out.println(query);
        System.out.println(Details[0]+":"+Details[1]);
        try{
            ResultSet rs =sql.execute(query,true);
            if (rs.next()) {
                selected = 1;
                ID=rs.getString("ID");
                String name = rs.getString("name");
                name_label_text.setText(name);
                String number = rs.getString("number");
                number_label_text.setText(number);
                String address = rs.getString("address");
                address_label_text.setText(address);
                String email = rs.getString("email");
                email_label_text.setText(email);
                name_label_text.setEnabled(false);
                number_label_text.setEnabled(false);
                address_label_text.setEnabled(false);
                email_label_text.setEnabled(false);
                save.setVisible(false);
                edit.setVisible(true);
                sql.c.close();
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
        catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }

    }

    public boolean editaction (JFrame jframe)
    {
        if (selected==1)
        {
            name_label_text.setEnabled(true);
            number_label_text.setEnabled(true);
            address_label_text.setEnabled(true);
            email_label_text.setEnabled(true);
            edit.setVisible(false);
            save.setVisible(true);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(jframe, "Select a Customer to Edit.","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean saveaction(JFrame jframe)
    {   SQL sql = new SQL();

        String Name = name_label_text.getText();
        String Address = address_label_text.getText();
        String Number =  number_label_text.getText();
        String Email =  email_label_text.getText();
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

        String query = String.format("UPDATE customer set name = '%s', number= '%s',address ='%s',email ='%s' where ID=%s", Name, Number, Address, Email,ID);
        System.out.println(query);
        try
        {
            if (sql.updateQuery(query)==0) 
            {
                //message.setText(" Hello " + userName+ "");
                name_label_text.setText("");
                number_label_text.setText("");
                address_label_text.setText("");
                email_label_text.setText("");
                ((JTextField) search_box.getEditor().getEditorComponent()).setText("");
                edit.setVisible(true);
                save.setVisible(false);
                selected = 0;
                JOptionPane.showMessageDialog(jframe, "Customer Updated"); 
                return true;
            } 
            else 
            {
                JOptionPane.showMessageDialog(jframe, "Invalid details","Error",JOptionPane.ERROR_MESSAGE); 
                //System.out.println("False");
                //message.setText(" Invalid user.. ");
                return false;
            }
        }
        catch ( HeadlessException e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            JOptionPane.showMessageDialog(jframe, "ERROR!!","Error",JOptionPane.ERROR_MESSAGE); 
            return false;
        }

        
    }

    public boolean deleteaction (JFrame jframe)
    {  
        if (selected == 1)
        {
        int a=JOptionPane.showConfirmDialog(jframe,"Are you sure?");  
        if(a==JOptionPane.YES_OPTION)
        {     
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

            String name=name_label_text.getText();
            String email=email_label_text.getText();   
            SQL sql=new SQL();
            String query = String.format("DELETE from customer where name='%s' and email='%s';",name,email);
            System.out.println(query);

            try{
                sql.updateQuery(query);

            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                return false;
            }
            name_label_text.setText("");
            number_label_text.setText("");
            address_label_text.setText("");
            email_label_text.setText("");

            ((JTextField) search_box.getEditor().getEditorComponent()).setText("");

            JOptionPane.showMessageDialog(jframe, "Customer has been deleted succesfully"); 
        }
        selected = 0;
        return true;
        }
        else{
            JOptionPane.showMessageDialog(jframe, "Select a Customer to Delete.","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public JScrollPane viewCustomer() 
    {    
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20, 20, 20, 20);

        //TITLE
        title_label = new JLabel();
        title_label.setText("VIEW YOUR CUSTOMERS");
        title_label.setForeground(Color.WHITE);
        title_label.setFont(new Font("Century",Font.BOLD,40));

        /*SEARCH FIELD
        search_label = new JLabel();
        search_label.setText("SEARCH");
        search_label.setForeground(Color.WHITE);*/

        //search_label.setFont(new Font("Century",Font.BOLD,20));
        //search_label_text = new JTextField(20);
        String check_query = "SELECT * FROM customer WHERE name LIKE '%%%s%%' OR email LIKE '%%%s%%';";
        AutoSuggest box = new AutoSuggest();
        search_box = box.create_box(check_query);

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
        name_label_text.setDisabledTextColor(Color.BLUE);
        name_label_text.setEnabled(false);

        //ADDRESS
        address_label = new JLabel();
        address_label.setText("ADDRESS :");
        address_label.setForeground(Color.WHITE);
        address_label.setFont(new Font("Century",Font.BOLD,20));
        address_label_text = new JTextArea(5,20);
        address_label_text.setDisabledTextColor(Color.BLUE);
        //address_label_text.setEnabled(false);

        //EMAIL
        email_label = new JLabel();
        email_label.setText("EMAIL :");
        email_label.setForeground(Color.WHITE);
        email_label.setFont(new Font("Century",Font.BOLD,20));
        email_label_text = new JTextField(20);
        email_label_text.setDisabledTextColor(Color.BLUE);
        email_label_text.setEnabled(false);

        // SubmitButton
        search = new JButton("SEARCH");
        edit = new JButton(" EDIT ");
        delete = new JButton("DELETE");
        save = new JButton("SAVE");
        save.setVisible(false);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(search,constraints);

        constraints.gridx = 1;
        panel.add(search_box,constraints);
        //constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(name_label,constraints);

        constraints.gridx = 1;
        panel.add(name_label_text,constraints);
        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(number_label,constraints);

        constraints.gridx = 1;
        panel.add(number_label_text,constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(address_label,constraints);

        constraints.gridx = 1;
        panel.add(address_label_text,constraints);
        constraints.gridx = 0;
        constraints.gridy = 12;
        panel.add(email_label,constraints);

        constraints.gridx = 1;
        panel.add(email_label_text,constraints);

        constraints.gridx = 1;
        constraints.gridy = 14;

        constraints.insets = new Insets(5, 20, 5, 20);
        constraints.gridwidth = 2;
        panel.add(edit,constraints);
        //constraints.gridx = 1;
        constraints.gridy = 16;
        panel.add(save,constraints);
        constraints.gridy = 18;
        constraints.gridx = 1;
        panel.add(delete,constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(40, 20, 0, 20);
        panel.add(title_label,constraints);
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 5, 5, 5);
        JSeparator s = new JSeparator();  
        s.setBackground(Color.WHITE);
        s.setForeground(Color.WHITE);
        //panel.add(s,constraints);
        panel.add(s, constraints);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        add(panel, BorderLayout.CENTER);
        setTitle("WHICH CUSTOMER DO YOU WANT TO VIEW !");
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
        ViewCustomer login = new ViewCustomer();
        login.initUI();
    }

}
