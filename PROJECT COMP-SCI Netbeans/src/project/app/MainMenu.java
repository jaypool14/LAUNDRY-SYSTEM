package project.app;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.Box;


public class MainMenu extends JFrame {
    JMenuItem customer_sub, customer_sub2,customer_sub3, new_employee,manage_employee, place_orders, review_orders,all_employees,all_orders,transactions;
    
    
    public void initUI() {

        createMenuBar();
        setVisible(true);
        setLocationRelativeTo(null);
        //pack(); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simple menu");
        setSize(350, 250);
    }

    public JMenuBar createMenuBar() {
        //JFrame frame = new JFrame("JFrame Example");  

        JMenuBar menuBar = new JMenuBar();
        ImageIcon exitIcon = new ImageIcon("src/resources/exit.png");
        //THIS MENU IS FOR CUSTOMERS
        JMenu customer_menu = new JMenu("CUSTOMERS");
        
        customer_menu.setMnemonic(KeyEvent.VK_F);

        customer_sub = new JMenuItem("ADD NEW CUSTOMER", exitIcon);
        customer_sub2 = new JMenuItem("VIEW CUSTOMERS", exitIcon);
        customer_sub3 = new JMenuItem("ALL CUSTOMERS", exitIcon);
        
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        customer_sub.setToolTipText("CAN CREATE A NEW CUSOTMER");
        customer_sub2.setToolTipText("CAN VIEW EXISTING CUSTOMERS");
        
        //customer_sub.addActionListener((event) -> System.exit(0));
        //customer_sub2.addActionListener((event) -> System.exit(0));
        customer_menu.add(customer_sub);
        customer_menu.addSeparator();
        customer_menu.add(customer_sub2);
        customer_menu.addSeparator();
        customer_menu.add(customer_sub3);
        
        //THIS IS THE MENU FOR EMPLOYEES
        JMenu employee_menu = new JMenu("EMPLOYEES");
        
        employee_menu.setMnemonic(KeyEvent.VK_E);

        new_employee = new JMenuItem("ADD NEW EMPLOYEES", exitIcon);
        manage_employee = new JMenuItem("MANAGE EMPLOYEES", exitIcon);
        all_employees = new JMenuItem("ALL EMPLOYEES", exitIcon);
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        new_employee.setToolTipText("CAN CREATE A NEW EMPLOYEE");
        manage_employee.setToolTipText("CAN VIEW EXISTING EMPLOYEES");
        manage_employee.setToolTipText("CAN VIEW ALL EXISTING EMPLOYEES TOGETHER");

        //new_employee.addActionListener((event) -> System.exit(0));
     
        employee_menu.add(new_employee);
        employee_menu.addSeparator();
        employee_menu.add(manage_employee);
        employee_menu.addSeparator();
        employee_menu.add(all_employees);
         
         
        //THIS IS THE MENU FOR ORDERS
        JMenu order_menu = new JMenu("ORDERS");
        
        order_menu.setMnemonic(KeyEvent.VK_O);

        place_orders = new JMenuItem("PLACE ORDER", exitIcon);
        review_orders = new JMenuItem("REVIEW ORDERS", exitIcon);
       all_orders = new JMenuItem("ORDER HISTORY", exitIcon);
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        place_orders.setToolTipText("CAN CREATE A NEW ORDER");
        review_orders.setToolTipText("CAN REVIEW EXISTING ORDERS");
        all_orders.setToolTipText("CAN VIEW ALL ORDERS");
        
        // place_orders.addActionListener((event) -> System.exit(0));
        //review_orders.addActionListener((event) -> System.exit(0));
        //all_orders.addActionListener((event) -> System.exit(0));
        
        order_menu.add(place_orders);
        order_menu.addSeparator();
        order_menu.add(review_orders);
        order_menu.addSeparator();
        order_menu.add(all_orders);
      
        //THIS IS THE MENU FOR FINANCE
        JMenu finance = new JMenu("FINANCE");
        
        finance.setMnemonic(KeyEvent.VK_F);

                  transactions = new JMenuItem("TRANSACTIONS", exitIcon);
     
        JMenuItem cash_flow = new JMenuItem("CASH FLOW STATEMENT", exitIcon);
        
        //eMenuItem.setMnemonic(KeyEvent.VK_E);
        transactions.setToolTipText("CAN ENTER AND VIEW FINANCES");
  
        cash_flow.setToolTipText("CAN VIEW CASH FLOW STATEMENT");
        
        
        cash_flow.addActionListener((event) -> System.exit(0));
        
        finance.add(transactions);
        finance.addSeparator();
       
        finance.add(cash_flow);
        
        //THIS IS THE MENU FOR ADDITIONAL INFORMATION
        JMenu add_info = new JMenu("ADDITIONAL INFORMATION");
        
        employee_menu.setMnemonic(KeyEvent.VK_F);

        JMenuItem X = new JMenuItem("X", exitIcon);
        JMenuItem Y = new JMenuItem("Y", exitIcon);
        JMenuItem Z = new JMenuItem("Z FLOW STATEMENT", exitIcon);
        
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
        
        JMenu helpMenu = new JMenu("Help");
        
        menuBar.add(customer_menu);
        menuBar.add(employee_menu);
        menuBar.add(order_menu);
        menuBar.add(finance);
        menuBar.add(add_info);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(helpMenu);
       
        
        //setJMenuBar(menuBar);
        add(menuBar, BorderLayout.NORTH); 
        add(menuBar, BorderLayout.SOUTH); 
        
        return menuBar;
    }

    public static void main(String[] args) {
            MainMenu ex = new MainMenu();
            ex.initUI();
    }
}