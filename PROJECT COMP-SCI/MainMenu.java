import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JFrame {

    public MainMenu() {

        initUI();
    }

    private void initUI() {

        createMenuBar();

        setTitle("Simple menu");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        var menuBar = new JMenuBar();
        var exitIcon = new ImageIcon("src/resources/exit.png");
        //THIS MENU IS FOR CUSTOMERS
        var customer_menu = new JMenu("CUSTOMERS");
        
        customer_menu.setMnemonic(KeyEvent.VK_F);

        var customer_sub = new JMenuItem("ADD NEW CUSTOMER", exitIcon);
        var customer_sub2 = new JMenuItem("VIEW CUSTOMERS", exitIcon);
        
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        customer_sub.setToolTipText("CAN CREATE A NEW CUSOTMER");
        customer_sub2.setToolTipText("CAN VIEW EXISTING CUSTOMERS");
        
        customer_sub.addActionListener((event) -> System.exit(0));
        customer_sub2.addActionListener((event) -> System.exit(0));
        customer_menu.add(customer_sub);
        customer_menu.addSeparator();
        customer_menu.add(customer_sub2);
        
        //THIS IS THE MENU FOR EMPLOYEES
        var employee_menu = new JMenu("EMPLOYEES");
        
        employee_menu.setMnemonic(KeyEvent.VK_F);

        var new_employee = new JMenuItem("ADD NEW EMPLOYEES", exitIcon);
        var manage_employee = new JMenuItem("MANAGE EMPLOYEES", exitIcon);
        
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        new_employee.setToolTipText("CAN CREATE A NEW EMPLOYEE");
        manage_employee.setToolTipText("CAN VIEW EXISTING EMPLOYEES");
        
        new_employee.addActionListener((event) -> System.exit(0));
        manage_employee.addActionListener((event) -> System.exit(0));
        employee_menu.add(new_employee);
        employee_menu.addSeparator();
        employee_menu.add(manage_employee);
         
        //THIS IS THE MENU FOR ORDERS
        var order_menu = new JMenu("ORDERS");
        
        employee_menu.setMnemonic(KeyEvent.VK_O);

        var place_orders = new JMenuItem("PLACE ORDER", exitIcon);
        var review_orders = new JMenuItem("REVIEW ORDERS", exitIcon);
        var order_control = new JMenuItem("ORDER CONTROL", exitIcon);
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        place_orders.setToolTipText("CAN CREATE A NEW ORDER");
        review_orders.setToolTipText("CAN REVIEW EXISTING ORDERS");
        order_control.setToolTipText("CAN CONTROL EXISTING ORDERS");
        
        place_orders.addActionListener((event) -> System.exit(0));
        review_orders.addActionListener((event) -> System.exit(0));
        order_control.addActionListener((event) -> System.exit(0));
        
        order_menu.add(place_orders);
        order_menu.addSeparator();
        order_menu.add(review_orders);
        order_menu.addSeparator();
        order_menu.add(order_control);
      
        //THIS IS THE MENU FOR FINANCE
        var finance = new JMenu("FINANCE");
        
        finance.setMnemonic(KeyEvent.VK_F);

        var costs = new JMenuItem("COSTS", exitIcon);
        var income = new JMenuItem("INCOME", exitIcon);
        var cash_flow = new JMenuItem("CASH FLOW STATEMENT", exitIcon);
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        costs.setToolTipText("CAN ENTER COSTS");
        income.setToolTipText("CAN ENTER INCOME");
        cash_flow.setToolTipText("CAN CREATE CASH FLOW STATEMENT");
        
        costs.addActionListener((event) -> System.exit(0));
        income.addActionListener((event) -> System.exit(0));
        cash_flow.addActionListener((event) -> System.exit(0));
        
        finance.add(costs);
        finance.addSeparator();
        finance.add(income);
        finance.addSeparator();
        finance.add(cash_flow);
        
        //THIS IS THE MENU FOR ADDITIONAL INFORMATION
        var add_info = new JMenu("ADDITIONAL INFORMATION");
        
        employee_menu.setMnemonic(KeyEvent.VK_F);

        var X = new JMenuItem("X", exitIcon);
        var Y = new JMenuItem("Y", exitIcon);
        var Z = new JMenuItem("Z FLOW STATEMENT", exitIcon);
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        X.setToolTipText("CAN ENTER X");
        Y.setToolTipText("CAN ENTER Y");
        Z.setToolTipText("CAN CREATE Z");
        
        X.addActionListener((event) -> System.exit(0));
        Y.addActionListener((event) -> System.exit(0));
        Z.addActionListener((event) -> System.exit(0));
        
        add_info.add(X);
        add_info.addSeparator();
        add_info.add(Y);
        add_info.addSeparator();
        add_info.add(Z);
      
        
        menuBar.add(customer_menu);
        menuBar.add(employee_menu);
        menuBar.add(order_menu);
        menuBar.add(finance);
        menuBar.add(add_info);
        
        setJMenuBar(menuBar);
        setVisible(true);
    }

    public static void main(String[] args) {
            var ex = new MainMenu();
    }
}