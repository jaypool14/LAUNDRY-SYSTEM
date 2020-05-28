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
{ JPanel panel;
    JLabel search_label,title_label2,name_label2,number_label2,email_label2,joindate_label,designation_label,message;
    JTextField search_label_text,name_label2_text,number_label2_text,email_label2_text,joindate_label_text;
    JTextArea address_label_text;
    JPasswordField password_text;
    JButton search,edit,delete;
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
    }

    public boolean searchemployeeaction (JFrame jframe)
    {return false;
    }

    public boolean editaction (JFrame jframe)
    {return false;
    }

    public boolean deleteaction (JFrame jframe)
    {return false;
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
        title_label2.setText("CREATE NEW EMPLOYEE");
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
        search = new JButton("SEARCH");
        edit = new JButton(" EDIT ");
        delete = new JButton("DELETE");

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
        constraints.gridwidth = 2;
        panel.add(edit,constraints);
        constraints.gridy = 16;
        panel.add(delete,constraints);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 0;
        //constraints.ipadx = 4;
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
