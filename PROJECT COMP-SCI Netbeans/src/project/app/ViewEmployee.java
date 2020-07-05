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

public class ViewEmployee extends JFrame
{   JPanel panel;
    JLabel search_label,title_label2,name_label2,number_label2,email_label2,joindate_label,designation_label,message;
    JTextField search_label_text,name_label2_text,number_label2_text,email_label2_text,joindate_label_text;
    JTextArea address_label_text;
    JPasswordField password_text;
    JButton search,edit,delete,save;
    JComboBox desiglist;
    JComboBox search_box;
    JDatePickerImpl joindate;
    String ID="";
    String[] designation = { "DRIVER", "CLEANER", "MANAGER"};
    int selected = 0;
    public void initUI()
    {
        viewEmployee();
        setVisible(true);
        search.addActionListener((event) -> searchemployeeaction (this));
        edit.addActionListener((event) -> editaction (this));
        delete.addActionListener((event) -> deleteaction (this));
        save.addActionListener((event) -> saveaction (this));
    }

    public boolean searchemployeeaction (JFrame jframe)
    {
        String typedText = ((JTextField)search_box.getEditor().getEditorComponent()).getText();      
        System.out.println(typedText);
        String[] Details=typedText.split(" : ");
        SQL sql=new SQL();
        String query = String.format("SELECT * FROM EMPLOYEE WHERE name='%s' AND email='%s'",Details[0],Details[1]);
        System.out.println(query);
        System.out.println(Details[0]+":"+Details[1]);
        try{
            ResultSet rs =sql.execute(query,true);
            if (rs.next()) {
                selected = 1;
                ID=rs.getString("ID");
                String name = rs.getString("name");
                name_label2_text.setText(name);
                String number = rs.getString("number");
                number_label2_text.setText(number);
                String email = rs.getString("email");
                email_label2_text.setText(email);

                String joindate_value = rs.getString("joindate");
                System.out.println("join date" +joindate_value);
                String[] date=joindate_value.split("-");
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);                
                joindate.getModel().setDate(year,month-1,day);
                joindate.getModel().setSelected(false);
                joindate.getModel().setSelected(true);
                String designation_value=rs.getString("designation");
                desiglist.setSelectedItem(designation_value);

                name_label2_text.setEnabled(false);
                number_label2_text.setEnabled(false);
                email_label2_text.setEnabled(false);
                //joindate_label_text.setEnabled(false);
                desiglist.setEnabled(false);

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
        catch ( NumberFormatException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }

    }

    public boolean editaction (JFrame jframe)
    {
        if (selected ==1)
        {
            name_label2_text.setEnabled(true);
            number_label2_text.setEnabled(true);
            email_label2_text.setEnabled(true);
            //joindate_label_text.setEnabled(true);
            desiglist.setEnabled(true);
            edit.setVisible(false);
            save.setVisible(true);
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(jframe, "Select an Employee to Edit.","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean saveaction(JFrame jframe)
    {   SQL sql = new SQL();

        String Name = name_label2_text.getText();
        String Number =  number_label2_text.getText();
        String Email =  email_label2_text.getText();
        // ------------
        Date selectedDate = (Date) joindate.getModel().getValue();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String datestr = formatter.format(selectedDate);
        System.out.println("Date is : "+datestr);
        String desiglist2=((JTextField) desiglist.getEditor().getEditorComponent()).getText();
        //-----------------
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

        String query = String.format("UPDATE EMPLOYEE set name = '%s', number= '%s',email ='%s',joindate='%s',designation='%s' where ID=%s", Name, Number,Email,datestr,desiglist2,ID);
        System.out.println(query);
        try
        {
            if (0!=sql.updateQuery(query)) 
            {
                //message.setText(" Hello " + userName+ "");
                name_label2_text.setText("");
                number_label2_text.setText("");
                email_label2_text.setText("");
                //joindate_label_text.setText("");
                ((JTextField) desiglist.getEditor().getEditorComponent()).setText("");
                joindate.getModel().setSelected(false);
                ((JTextField) search_box.getEditor().getEditorComponent()).setText("");
                joindate = (new JDatePicker()).datepanel();
                edit.setVisible(true);
                save.setVisible(false);
                selected = 0;
                JOptionPane.showMessageDialog(jframe, "Employee Updated"); 
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

                String name=name_label2_text.getText();
                String email=email_label2_text.getText();   
                SQL sql=new SQL();
                String query = String.format("DELETE from EMPLOYEE where name='%s' and email='%s';",name,email);
                System.out.println(query);

                try{
                    sql.updateQuery(query);

                }
                catch ( Exception e ) {
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                    return false;
                }
                name_label2_text.setText("");
                number_label2_text.setText("");
                email_label2_text.setText("");
                //joindate_label_text.setText("");

                ((JTextField) search_box.getEditor().getEditorComponent()).setText("");

                JOptionPane.showMessageDialog(jframe, "Employee has been deleted succesfully"); 
            }
            selected = 0;
            return true;
        }
        else{
            JOptionPane.showMessageDialog(jframe, "Select a Customer to Delete.","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public JScrollPane viewEmployee() 
    {    
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(15, 20, 15, 20);

        desiglist = new JComboBox(designation);
        desiglist.setSelectedIndex(0); 
        //TITLE
        title_label2 = new JLabel();
        title_label2.setText("MANAGE YOUR EMPLOYEES");
        title_label2.setForeground(Color.WHITE);
        title_label2.setFont(new Font("Century",Font.BOLD,40));

        String check_query = "SELECT * FROM EMPLOYEE WHERE name LIKE '%%%s%%' OR email LIKE '%%%s%%';";
        AutoSuggest box = new AutoSuggest();
        search_box = box.create_box(check_query);

        // NAME
        name_label2 = new JLabel();
        name_label2.setText("NAME :");
        name_label2.setForeground(Color.WHITE);
        name_label2.setFont(new Font("Century",Font.BOLD,20));
        name_label2_text = new JTextField(20);
        name_label2_text.setDisabledTextColor(Color.BLUE);
        // NUMBER
        number_label2 = new JLabel();
        number_label2.setText("NUMBER :");
        number_label2.setForeground(Color.WHITE);
        number_label2.setFont(new Font("Century",Font.BOLD,20));
        number_label2_text = new JTextField(20);
        number_label2_text.setDisabledTextColor(Color.BLUE);

        //EMAIL
        email_label2 = new JLabel();
        email_label2.setText("EMAIL :");
        email_label2.setForeground(Color.WHITE);
        email_label2.setFont(new Font("Century",Font.BOLD,20));
        email_label2_text = new JTextField(20);
        email_label2_text.setDisabledTextColor(Color.BLUE);

        //JOIN DATE
        joindate_label = new JLabel();
        joindate_label.setText("JOIN DATE:");
        joindate_label.setForeground(Color.WHITE);
        joindate_label.setFont(new Font("Century",Font.BOLD,20));
        joindate = (new JDatePicker()).datepanel();

        //DESIGNATION
        designation_label = new JLabel();
        designation_label.setText("DESIGNATION:");
        designation_label.setForeground(Color.WHITE);
        designation_label.setFont(new Font("Century",Font.BOLD,20));
        ((JTextField)desiglist.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLUE);

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

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(name_label2,constraints);

        constraints.gridx = 1;
        panel.add(name_label2_text,constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(number_label2,constraints);

        constraints.gridx = 1;
        panel.add(number_label2_text,constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        panel.add(email_label2,constraints);

        constraints.gridx = 1;
        panel.add(email_label2_text,constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(designation_label,constraints);

        constraints.gridx = 1;
        panel.add(desiglist,constraints);
        constraints.gridx = 0;
        constraints.gridy = 12;
        panel.add(joindate_label,constraints);

        constraints.gridx = 1;
        panel.add(joindate,constraints);

        constraints.gridx = 1;
        constraints.gridy = 16;

        constraints.insets = new Insets(5, 20, 5, 20);
        constraints.gridwidth = 2;
        panel.add(edit,constraints);
        constraints.gridy = 16;
        panel.add(save,constraints);
        constraints.gridy = 18;
        constraints.gridx = 1;
        panel.add(delete,constraints);

        //constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(40, 20, 0, 20);
        panel.add(title_label2,constraints);
        constraints.gridy = 2;             
        constraints.insets = new Insets(0, 5, 5, 5);
        JSeparator s = new JSeparator();  
        panel.add(s,constraints);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        add(panel, BorderLayout.CENTER);
        setTitle("WHICH EMPLOYEE DO YOU WANT TO VIEW !");
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
        ViewEmployee login = new ViewEmployee();
        login.initUI();
    }

}
