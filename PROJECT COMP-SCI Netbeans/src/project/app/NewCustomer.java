package project.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.HeadlessException;

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
import javax.swing.JScrollPane;

public class NewCustomer extends JFrame {

    JPanel panel;
    JLabel title_label, name_label, number_label, address_label, email_label, message;
    JTextField name_label_text, number_label_text, email_label_text;
    JTextArea address_label_text;
    JPasswordField password_text;
    JButton submit, cancel;

    public void initUI() {
        newCustomer();
        setVisible(true);
        submit.addActionListener((event) -> customeraddaction(this));
    }

    public JScrollPane newCustomer() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20, 20, 20, 20);

        //TITLE
        title_label = new JLabel();
        title_label.setText("CREATE NEW CUSTOMER");
        title_label.setForeground(Color.WHITE);
        title_label.setFont(new Font("Century", Font.BOLD, 40));

        // NAME
        name_label = new JLabel();
        name_label.setText("NAME :");
        name_label.setForeground(Color.WHITE);
        name_label.setFont(new Font("Century", Font.BOLD, 20));
        name_label_text = new JTextField(20);

        // NUMBER
        number_label = new JLabel();
        number_label.setText("NUMBER :");
        number_label.setForeground(Color.WHITE);
        number_label.setFont(new Font("Century", Font.BOLD, 20));
        number_label_text = new JTextField(20);

        //ADDRESS
        address_label = new JLabel();
        address_label.setText("ADDRESS :");
        address_label.setForeground(Color.WHITE);
        address_label.setFont(new Font("Century", Font.BOLD, 20));
        address_label_text = new JTextArea(5, 20);

        //EMAIL
        email_label = new JLabel();
        email_label.setText("EMAIL :");
        email_label.setForeground(Color.WHITE);
        email_label.setFont(new Font("Century", Font.BOLD, 20));
        email_label_text = new JTextField(20);

        // SubmitButton
        submit = new JButton("ADD CUSTOMER");

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(name_label, constraints);
        constraints.gridx = 1;
        panel.add(name_label_text, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(number_label, constraints);

        constraints.gridx = 1;
        panel.add(number_label_text, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(address_label, constraints);

        constraints.gridx = 1;
        panel.add(address_label_text, constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(email_label, constraints);

        constraints.gridx = 1;
        panel.add(email_label_text, constraints);

        constraints.gridx = 1;
        constraints.gridy = 12;
        panel.add(submit, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(40, 20, 0, 20);
        panel.add(title_label, constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 5, 5, 5);
        JSeparator s = new JSeparator();
        panel.add(s, constraints);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        setTitle("Enter customer details here !");
        pack();
        panel.setOpaque(false);
        JScrollPane panelPane = new JScrollPane(panel);
        panelPane.setOpaque(false);
        panelPane.getViewport().setOpaque(false);
        return panelPane;
    }

    public static void main(String[] args) {
        var login = new NewCustomer();
        login.initUI();
    }

    public boolean customeraddaction(JFrame jframe) {
        SQL sql = new SQL();
        String Name = name_label_text.getText();
        String Address = address_label_text.getText();
        String Number = number_label_text.getText();
        String Email = email_label_text.getText();
        boolean num_check = Pattern.matches("[0-9]{10}", Number);
        boolean mail_check = Pattern.matches("[a-zA-Z_0-9.]+@[a-z]{1,10}\\.[a-z]{2,3}", Email);
        if (num_check == false) {
            JOptionPane.showMessageDialog(jframe, "Invalid Number. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (mail_check == false) {
            JOptionPane.showMessageDialog(jframe, "Invalid Email. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String check_query = String.format("SELECT * FROM customer WHERE name='%s' AND email='%s';", Name, Email);
        try {
            ResultSet rs = sql.execute(check_query);
            if (rs.next()) {
                rs.close();
                JOptionPane.showMessageDialog(jframe, "Existing Customer", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        String query = String.format("INSERT INTO customer (name,number, address,email) VALUES ('%s','%s','%s','%s');", Name, Number, Address, Email);
        System.out.println(query);
        try {
            if (sql.updateQuery(query) != 0) {
                name_label_text.setText("");
                number_label_text.setText("");
                address_label_text.setText("");
                email_label_text.setText("");
                JOptionPane.showMessageDialog(jframe, "Customer added");
                return true;
            } else {
                JOptionPane.showMessageDialog(jframe, "Invalid details", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            JOptionPane.showMessageDialog(jframe, "ERROR!!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
