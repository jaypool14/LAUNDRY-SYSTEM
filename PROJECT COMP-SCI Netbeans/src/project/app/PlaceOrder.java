package project.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.event.TableModelEvent;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class PlaceOrder extends JFrame {

    JPanel panel, jpanel;
    JLabel title_label, customer_email_label, num_cloth_label, cloth_type_label, image_label, order_priority_label, total_cost_label, total_cost_text;
    JTextField num_cloth_text;
    JTable table = new JTable(0, 2);
    JButton place_order, add_row, delete_row;

    JComboBox search_box, cloth_type, order_priority;
    String[] CLOTHES = {"UNDERGAMRNETS", "JEANS", "SHORTS", "PANTS", "SKIRTS", "TSHIRT", "SHIRT", "BLANKETS", "SUIT", "EHTNIC"};
    int[] PRICE = {30, 50, 40, 60, 50, 40, 60, 100, 100, 150};
    double priority = 1.0;
    String[] PRIORITY = {"STANDARD", "EXPRESS"};

    public void initUI() {
        newOrder();
        setVisible(true);
    }

    public void setlistners(JFrame jframe) {
        place_order.addActionListener((event) -> placeorderaction(jframe));
    }

    public boolean placeorderaction(JFrame jframe) {
        SQL sql = new SQL();
        String typedText = ((JTextField) search_box.getEditor().getEditorComponent()).getText();
        System.out.println(typedText);
        String[] Details = typedText.split(" : ");
        String cloth, number, data = "";
        int index;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            cloth = String.valueOf(model.getValueAt(i, 0));
            number = String.valueOf(model.getValueAt(i, 1));
            if (data.length() > 0) {
                data += ",";
            }
            data += cloth + "=" + number;
        }
        String image = "";
        String priority = (String) order_priority.getSelectedItem();
        String total_cost = total_cost_text.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String query = String.format("INSERT INTO orders (customer_email,customer_name, cloth_data,image, priority, cost, date) VALUES ('%s','%s','%s','%s','%s','%s','%s');", Details[1], Details[0], data, image, priority, total_cost, date);
        System.out.println(query);

        try {
            int id = sql.updateQuery(query);
            if (id != 0) {
                JOptionPane.showMessageDialog(jframe, "Your Order has been placed.\nYour order ID is: " + id + "\nPlease check your Email for Invoice", "THANK YOU!", JOptionPane.INFORMATION_MESSAGE);
                // Create a send class
                SendMail obj = new SendMail();
                // create and send the Invoice
                obj.create_bill(id);
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

    public JPanel newOrder() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        //TITLE
        title_label = new JLabel();
        title_label.setText("PLACE ORDER");
        title_label.setForeground(Color.WHITE);
        title_label.setFont(new Font("Century", Font.BOLD, 40));

        // CUSTOMER EMAIL
        customer_email_label = new JLabel();
        customer_email_label.setText("CUSTOMER EMAIL :");
        customer_email_label.setForeground(Color.WHITE);
        customer_email_label.setFont(new Font("Century", Font.BOLD, 20));

        String check_query = "SELECT * FROM customer WHERE  email LIKE '%%%s%%';";
        AutoSuggest box = new AutoSuggest();
        search_box = box.create_box(check_query);

        // Jtable
        Font font = new Font("Verdana", Font.PLAIN, 12);
        table.setFont(font);
        table.setRowHeight(30);
        table.setTableHeader(null);
        table.setBackground(Color.orange);
        table.setForeground(Color.white);

        TableColumn clothesColumn = table.getColumnModel().getColumn(0);
        TableColumn numberColumn = table.getColumnModel().getColumn(1);
        JComboBox<String> comboBox = new JComboBox<>();
        JComboBox<String> numberBox = new JComboBox<>();
        for (String CLOTHES1 : CLOTHES) {
            comboBox.addItem(CLOTHES1);
        }
        for (int i = 1; i <= 100; i++) {
            numberBox.addItem(Integer.toString(i));
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        numberColumn.setCellEditor(new DefaultCellEditor(numberBox));
        clothesColumn.setCellEditor(new DefaultCellEditor(comboBox));
        model.addRow(new Object[]{"Select Item", "Enter Pieces of Cloths"});
        model.addTableModelListener((TableModelEvent e) -> {
            totalcost(table);
        });

        // PEICES OF CLOTH
        num_cloth_label = new JLabel();
        num_cloth_label.setText(" PIECES OF CLOTH");
        num_cloth_label.setForeground(Color.WHITE);
        num_cloth_label.setFont(new Font("Century", Font.BOLD, 20));
        num_cloth_text = new JTextField(20);

        //TYPE OF CLOTH
        cloth_type_label = new JLabel();
        cloth_type_label.setText("TYPE OF CLOTH");
        cloth_type_label.setForeground(Color.WHITE);
        cloth_type_label.setFont(new Font("Century", Font.BOLD, 20));
        cloth_type = new JComboBox(CLOTHES);
        cloth_type.setSelectedIndex(0);
        ((JTextField) cloth_type.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLUE);

        //TOTAL COST
        total_cost_label = new JLabel();
        total_cost_label.setText("TOTAL COST :");
        total_cost_label.setForeground(Color.WHITE);
        total_cost_label.setFont(new Font("Century", Font.BOLD, 20));
        total_cost_text = new JLabel();
        total_cost_text.setText("0");
        total_cost_text.setForeground(Color.BLACK);
        total_cost_text.setFont(new Font("Century", Font.BOLD, 15));

        //ORDER PRIORITY
        order_priority_label = new JLabel();
        order_priority_label.setText("ORDER PRIORITY");
        order_priority_label.setForeground(Color.WHITE);
        order_priority_label.setFont(new Font("Century", Font.BOLD, 20));
        order_priority = new JComboBox(PRIORITY);
        order_priority.addActionListener((var e) -> {
            String value = ((JTextField) order_priority.getEditor().getEditorComponent()).getText();
            if (value.equals("STANDARD")) {
                priority = 1;
            }
            if (value.equals("EXPRESS")) {
                priority = 1.5;
            }
            totalcost(table);
        });
        order_priority.setSelectedIndex(0);
        ((JTextField) order_priority.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLUE);

        // BUTTONS
        place_order = new JButton("PLACE ORDER");
        add_row = new JButton("ADD ROW");
        delete_row = new JButton("DELETE ROW");

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(customer_email_label, constraints);

        constraints.gridx = 1;
        panel.add(search_box, constraints);

        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        JScrollPane tableSP = new JScrollPane(table);
        tableSP.setMaximumSize(new Dimension(100, 153));
        tableSP.setPreferredSize(new Dimension(100, 153));
        panel.add(tableSP, constraints);
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(cloth_type_label, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(num_cloth_label, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 8;
        panel.add(delete_row, constraints);
        delete_row.addActionListener((event) -> deleterow(table));

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(add_row, constraints);
        add_row.addActionListener((event) -> addrow(table));
        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(order_priority_label, constraints);

        constraints.gridx = 1;
        panel.add(order_priority, constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        panel.add(total_cost_label, constraints);

        constraints.gridx = 1;
        panel.add(total_cost_text, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 14;
        panel.add(place_order, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(title_label, constraints);
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 5, 5);
        JSeparator s = new JSeparator();
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

    public static void main(String[] args) {
        var login = new PlaceOrder();
        login.initUI();
    }

    public boolean addrow(JTable table) {
        String cloth, number;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int last = model.getRowCount() - 1;
        cloth = String.valueOf(model.getValueAt(last, 0));
        number = String.valueOf(model.getValueAt(last, 1));
        if (!cloth.contains("Item") && !number.contains("Cloths")) {
            model.addRow(new Object[]{"Select Item", "Enter Pieces of Cloths"});
        } else {
            JOptionPane.showMessageDialog(this, "Fill the last row", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public boolean deleterow(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(model.getRowCount() - 1);
        if (model.getRowCount() == 0) {
            model.addRow(new Object[]{"Select Item", "Enter Pieces of Cloths"});
        }
        return true;
    }

    public boolean totalcost(JTable table) {
        String cloth, number;
        int index;
        Double cost = 0.0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            cloth = String.valueOf(model.getValueAt(i, 0));
            number = String.valueOf(model.getValueAt(i, 1));
            if (!cloth.contains("Item") && !number.contains("Cloths")) {
                index = Arrays.asList(CLOTHES).indexOf(cloth);
                cost += PRICE[index] * Integer.parseInt(number) * priority;
            }
        }
        total_cost_text.setText(Double.toString(cost));
        return true;
    }
}
