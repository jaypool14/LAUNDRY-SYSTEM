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
import java.sql.ResultSet;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.util.regex.*;
public class PlaceOrder extends JFrame
{
    JPanel panel, jpanel;
    JLabel title_label,customer_email_label,num_cloth_label,cloth_type_label,image_label,order_priority_label, total_cost_label;
    JTextField num_cloth_text,total_cost_text;
    JTable jtable;
    JButton place_order,add_row;
    JComboBox search_box,cloth_type,order_priority;
    String[] CLOTHES = { "UNDERGAMRNETS", "JEANS", "SHORTS","PANTS","SKIRTS","TSHIRT","SHIRT","BLANKETS","SUIT","EHTNIC"};
    String[] PRIORITY = { "STANDARD", "EXPRESS"};

    public void initUI()
    {
        newOrder();
        setVisible(true);

    }

    public JPanel newOrder() 
    {    
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20, 20, 20, 20);

        //TITLE
        title_label = new JLabel();
        title_label.setText("PLACE ORDER");
        title_label.setForeground(Color.WHITE);
        title_label.setFont(new Font("Century",Font.BOLD,40));

        // CUSTOMER EMAIL
        customer_email_label = new JLabel();
        customer_email_label.setText("CUSTOMER EMAIL :");
        customer_email_label.setForeground(Color.WHITE);
        customer_email_label.setFont(new Font("Century",Font.BOLD,20));

        String check_query = "SELECT * FROM EMPLOYEE WHERE  email LIKE '%%%s%%';";
        AutoSuggest box = new AutoSuggest();
        search_box = box.create_box(check_query);
        // Jtable
        OrderTable order_table = new OrderTable();
        jtable = order_table.newTable();
        // PEICES OF CLOTH
        num_cloth_label = new JLabel();
        num_cloth_label.setText(" PIECES OF CLOTH");
        num_cloth_label.setForeground(Color.WHITE);
        num_cloth_label.setFont(new Font("Century",Font.BOLD,20));
        num_cloth_text = new JTextField(20);

        //TYPE OF CLOTH
        cloth_type_label = new JLabel();
        cloth_type_label.setText("TYPE OF CLOTH:");
        cloth_type_label.setForeground(Color.WHITE);
        cloth_type_label.setFont(new Font("Century",Font.BOLD,20));
        cloth_type = new JComboBox(CLOTHES);
        cloth_type.setSelectedIndex(0); 
        ((JTextField)cloth_type.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLUE);

        
        //IMAGE OF LAUNDRY 
        image_label = new JLabel();
        image_label.setText("IMAGE :");
        image_label.setForeground(Color.WHITE);
        image_label.setFont(new Font("Century",Font.BOLD,20));

        //ORDER PRIORITY
        order_priority_label = new JLabel();
        order_priority_label.setText("ORDER PRIORITY");
        order_priority_label.setForeground(Color.WHITE);
        order_priority_label.setFont(new Font("Century",Font.BOLD,20));
        order_priority = new JComboBox(PRIORITY);
        order_priority.setSelectedIndex(0); 
        ((JTextField)order_priority.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLUE);

        
        //TOTAL COST
        total_cost_label = new JLabel();
        total_cost_label.setText("TOTAL COST :");
        total_cost_label.setForeground(Color.WHITE);
        total_cost_label.setFont(new Font("Century",Font.BOLD,20));
        total_cost_text = new JTextField(20);

        // BUTTONS
        place_order = new JButton("PLACE ORDER");
        add_row = new JButton("ADD ROW");

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(customer_email_label,constraints);

        constraints.gridx = 1;
        panel.add(search_box,constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        //constraints.gridwidth = 2;
        //jpanel.setSize(new Dimension(100, 100));
        panel.add(jtable,constraints);
        //panel.add(num_cloth_label,constraints);
        /*
        constraints.gridx = 1;
        panel.add(num_cloth_text,constraints);

        constraints.gridx = 2;
        constraints.gridy = 6;
        panel.add(cloth_type_label,constraints);

        constraints.gridx = 3;
        panel.add(cloth_type,constraints);
        */
        constraints.gridx = 1;
        constraints.gridy = 8;
        panel.add(add_row,constraints);
        add_row.addActionListener((event) -> order_table.addrow (order_table.table));

        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(image_label,constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        panel.add(order_priority_label,constraints);

        constraints.gridx = 1;
        // constraints.gridy = 14;
        panel.add(order_priority,constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        panel.add(total_cost_label,constraints);

        constraints.gridx = 1;
        panel.add(total_cost_text,constraints);

        constraints.gridx = 1;
        constraints.gridy = 16;
        panel.add(place_order,constraints);

        //constraints.anchor = GridBagConstraints.NORTH;
        //constraints.weightx = 0;
        //constraints.ipadx = 4;
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
        JScrollPane panelPane = new JScrollPane(panel);
        add(panelPane, BorderLayout.CENTER);
        setTitle("CUSTOMER ORDERS !");
        pack();
        panel.setOpaque(false);
        return panel;
    }

    public static void main(String[] args) 
    {
        var login = new PlaceOrder();
        login.initUI();
    }
}
 