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
import java.sql.ResultSet;
import javax.swing.JSeparator;
import java.util.regex.*;

public class ViewCustomer extends JFrame
{   JPanel panel;
    JLabel search_label,title_label,name_label,number_label,address_label,email_label, message;
    JTextField search_label_text,name_label_text,number_label_text,email_label_text;
    JTextArea address_label_text;
    JPasswordField password_text;
    JButton search,edit,delete,save;
    JComboBox search_box;
    public void initUI()
    {
        viewCustomer();
        setVisible(true);
        search.addActionListener((event) -> searchcustomeraction (this));
        edit.addActionListener((event) -> editaction (this));
        delete.addActionListener((event) -> deleteaction (this));
        save.addActionListener((event) -> saveaction (this));
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
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }

    }

    public boolean editaction (JFrame jframe)
    {
        name_label_text.setEnabled(true);
        number_label_text.setEnabled(true);
        address_label_text.setEnabled(true);
        email_label_text.setEnabled(true);
        edit.setVisible(false);
        save.setVisible(true);
        return false;

    }

    public boolean saveaction(JFrame jfame)
    {

        return true;
    }

    public boolean deleteaction (JFrame jframe)
    {return false;
    }

    public JPanel viewCustomer() 
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
        search = new JButton("SEARCH");
        edit = new JButton(" EDIT ");
        delete = new JButton("DELETE");
        save = new JButton("SAVE");
        save.setVisible(false);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(search,constraints);

        constraints.gridx = 1;
        panel.add(search_box,constraints);
        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(name_label,constraints);

        constraints.gridx = 1;
        panel.add(name_label_text,constraints);
        name_label_text.setDisabledTextColor(Color.BLUE);
        name_label_text.setEnabled(false);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(number_label,constraints);

        constraints.gridx = 1;
        panel.add(number_label_text,constraints);
        number_label_text.setDisabledTextColor(Color.BLUE);
        number_label_text.setEnabled(false);
        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(address_label,constraints);

        constraints.gridx = 1;
        panel.add(address_label_text,constraints);
        address_label_text.setDisabledTextColor(Color.BLUE);
        address_label_text.setEnabled(false);
        constraints.gridx = 0;
        constraints.gridy = 12;
        panel.add(email_label,constraints);

        constraints.gridx = 1;
        panel.add(email_label_text,constraints);
        email_label_text.setDisabledTextColor(Color.BLUE);
        email_label_text.setEnabled(false);

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
        panel.add(title_label,constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        JSeparator s = new JSeparator();  
        //panel.add(s,constraints);
        panel.add(s, constraints);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        add(panel, BorderLayout.CENTER);
        setTitle("WHICH CUSTOMER DO YOU WANT TO VIEW !");
        pack();
        panel.setOpaque(false);
        return panel;
    }

    public static void main(String[] args) 
    {
        var login = new ViewCustomer();
        login.initUI();
    }

}
