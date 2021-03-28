package project.app;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.Box;

public class MainMenu extends JFrame {

    JMenuItem customer_sub, customer_sub2, customer_sub3, new_employee,
            manage_employee, place_orders, review_orders, all_employees,
            all_orders, transactions, mail_menu, cash_flow;

    public void initUI() {

        createMenuBar();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simple menu");
        setSize(350, 250);
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        ImageIcon exitIcon = new ImageIcon("src/resources/exit.png");
        //THIS MENU IS FOR CUSTOMERS
        JMenu customer_menu = new JMenu("CUSTOMERS");

        customer_menu.setMnemonic(KeyEvent.VK_F);

        customer_sub = new JMenuItem("ADD NEW CUSTOMER", exitIcon);
        customer_sub2 = new JMenuItem("VIEW CUSTOMERS", exitIcon);
        customer_sub3 = new JMenuItem("ALL CUSTOMERS", exitIcon);

        customer_sub.setToolTipText("CAN CREATE A NEW CUSOTMER");
        customer_sub2.setToolTipText("CAN VIEW EXISTING CUSTOMERS");
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

        new_employee.setToolTipText("CAN CREATE A NEW EMPLOYEE");
        manage_employee.setToolTipText("CAN VIEW EXISTING EMPLOYEES");
        manage_employee.setToolTipText("CAN VIEW ALL EXISTING EMPLOYEES TOGETHER");

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

        place_orders.setToolTipText("CAN CREATE A NEW ORDER");
        review_orders.setToolTipText("CAN REVIEW EXISTING ORDERS");
        all_orders.setToolTipText("CAN VIEW ALL ORDERS");

        order_menu.add(place_orders);
        order_menu.addSeparator();
        order_menu.add(review_orders);
        order_menu.addSeparator();
        order_menu.add(all_orders);

        //THIS IS THE MENU FOR FINANCE
        JMenu finance = new JMenu("FINANCE");
        finance.setMnemonic(KeyEvent.VK_F);
        transactions = new JMenuItem("TRANSACTIONS", exitIcon);
        cash_flow = new JMenuItem("CASH FLOW STATEMENT", exitIcon);
        transactions.setToolTipText("CAN ENTER AND VIEW FINANCES");
        cash_flow.setToolTipText("CAN VIEW CASH FLOW STATEMENT");

        finance.add(transactions);
        finance.addSeparator();

        finance.add(cash_flow);

        //THIS IS THE MENU FOR ADDITIONAL INFORMATION
        JMenu add_info = new JMenu("MAIL");
        mail_menu = new JMenuItem("MAIL", exitIcon);
        add_info.add(mail_menu);
        JMenu exit = new JMenu("EXIT");
        JMenuItem exit_m= new JMenuItem("Exit", exitIcon);
        exit.add(exit_m);
        exit_m.addActionListener((event) -> System.exit(0));
        
        menuBar.add(customer_menu);
        menuBar.add(employee_menu);
        menuBar.add(order_menu);
        menuBar.add(finance);
        menuBar.add(add_info);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(exit);
        add(menuBar, BorderLayout.NORTH);
        add(menuBar, BorderLayout.SOUTH);
        return menuBar;
    }

    public static void main(String[] args) {
        MainMenu ex = new MainMenu();
        ex.initUI();
    }
}
