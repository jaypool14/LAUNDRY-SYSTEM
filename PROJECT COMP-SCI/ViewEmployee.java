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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.sql.ResultSet;
import java.util.regex.*;

public class ViewEmployee extends JFrame
{   JPanel panel;
    JLabel search_label,title_label2,name_label2,number_label2,email_label2,joindate_label,designation_label,message;
    JTextField search_label_text,name_label2_text,number_label2_text,email_label2_text,joindate_label_text;
    JTextArea address_label_text;
    JPasswordField password_text;
    JButton search,edit,delete,save;
    JComboBox desiglist;
    JComboBox search_box;
    String[] designation = { "DRIVER", "CLEANER", "MANAGER"};
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
    {String typedText = ((JTextField)search_box.getEditor().getEditorComponent()).getText();      
        System.out.println(typedText);
        String[] Details=typedText.split(" : ");
        SQL sql=new SQL();
        String query = String.format("SELECT * FROM EMPLOYEE WHERE name='%s' AND email='%s'",Details[0],Details[1]);
        System.out.println(query);
        System.out.println(Details[0]+":"+Details[1]);
        try{
            ResultSet rs =sql.execute(query,true);
            if (rs.next()) {
                String name = rs.getString("name");
                name_label2_text.setText(name);
                String number = rs.getString("number");
                number_label2_text.setText(number);
                String email = rs.getString("email");
                email_label2_text.setText(email);
                String joindate = rs.getString("joindate");
                joindate_label_text.setText(joindate);
                String designation=rs.getString("designation");
                desiglist.setSelectedItem(designation);
                
                name_label2_text.setEnabled(false);
                number_label2_text.setEnabled(false);
                email_label2_text.setEnabled(false);
                joindate_label_text.setEnabled(false);
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
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }

    }
    

    public boolean editaction (JFrame jframe)
    {
        name_label2_text.setEnabled(true);
        number_label2_text.setEnabled(true);
        email_label2_text.setEnabled(true);
        joindate_label_text.setEnabled(true);
        desiglist.setEnabled(true);
        edit.setVisible(false);
        save.setVisible(true);
        return false;
    }
    
    public boolean saveaction(JFrame jfame)
    {

        return true;
    }

    public boolean deleteaction (JFrame jframe)
    {int a=JOptionPane.showConfirmDialog(jframe,"Are you sure?");  
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
        joindate_label_text.setText("");
        
      ((JTextField) search_box.getEditor().getEditorComponent()).setText("");
    
        JOptionPane.showMessageDialog(jframe, "Employee has been deleted succesfully"); 
    }
        return true;
    }


    public JPanel viewEmployee() 
    {    
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20, 20, 20, 20);

        desiglist = new JComboBox(designation);
        desiglist.setSelectedIndex(0); 
        //TITLE
        title_label2 = new JLabel();
        title_label2.setText("MANAGE EMPLOYEES");
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
        joindate_label_text = new JTextField(20);
        joindate_label_text.setDisabledTextColor(Color.BLUE);

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

        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
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
        constraints.gridy = 10;
        panel.add(email_label2,constraints);

        constraints.gridx = 1;
        panel.add(email_label2_text,constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        panel.add(number_label2,constraints);

        constraints.gridx = 1;
        panel.add(number_label2_text,constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(designation_label,constraints);

        constraints.gridx = 1;
        panel.add(desiglist,constraints);

        constraints.gridx = 1;
        constraints.gridy = 14;

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
        panel.add(title_label2,constraints);
        constraints.gridy = 1;             
        JSeparator s = new JSeparator();  
        panel.add(s,constraints);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        add(panel, BorderLayout.CENTER);
        setTitle("WHICH EMPLOYEE DO YOU WANT TO VIEW !");
        pack();
        panel.setOpaque(false);
        return panel;
    }

    public static void main(String[] args) 
    {
        var login = new ViewEmployee();
        login.initUI();
    }

}
