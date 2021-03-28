package project.app;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JScrollPane;

public class WrapperClass extends JFrame {

    JMenuBar menuBar;
    JPanel jpanel;
    Login login;
    MainMenu main_menu;
    JLabel mainPanel;
    JPanel details_panel = new JPanel();
    GridBagConstraints constraints = new GridBagConstraints();

    public void get_element() {
        login = new Login();
        main_menu = new MainMenu();
        menuBar = main_menu.createMenuBar();
        jpanel = login.createLogin();
        // Change the background image of JFrame
        set_background();
        // Link the buttons to actions to be performed
        login.submit.addActionListener((event) -> login_action());
        main_menu.customer_sub.addActionListener((event) -> newcustomer_action());
        main_menu.customer_sub2.addActionListener((event) -> viewcustomer_action());
        main_menu.customer_sub3.addActionListener((event) -> allcustomer_action());
        main_menu.new_employee.addActionListener((event) -> newemployee_action());
        main_menu.manage_employee.addActionListener((event) -> viewemployee_action());
        main_menu.all_employees.addActionListener((event) -> allemployees_action());
        main_menu.place_orders.addActionListener((event) -> placeorder_action());
        main_menu.review_orders.addActionListener((event) -> revieworder_action());
        main_menu.all_orders.addActionListener((event) -> allorders_action());
        main_menu.transactions.addActionListener((event) -> transactions_action());
        main_menu.mail_menu.addActionListener((event) -> mail_action());
        main_menu.cash_flow.addActionListener((event) -> cashflow_action());

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(jpanel, constraints);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        mainPanel.add(menuBar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(details_panel, constraints);

        menuBar.setVisible(false);
        details_panel.setLayout(new GridBagLayout());
        details_panel.setVisible(true);
        details_panel.setOpaque(false);
        setTitle("EazyLaundry");

        JScrollPane panelPane = new JScrollPane(mainPanel);
        add(panelPane);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void login_action() {
        if (login.submit_action() == true) {
            jpanel.setVisible(false);
            menuBar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "InvalidLogin.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void newcustomer_action() {
        NewCustomer new_customer = new NewCustomer();
        JScrollPane newcustomer = new_customer.newCustomer();
        new_customer.submit.addActionListener((event) -> new_customer.customeraddaction(this));
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(newcustomer, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void viewcustomer_action() {
        ViewCustomer view_customer = new ViewCustomer();
        JScrollPane viewcustomer = view_customer.viewCustomer();
        view_customer.setlistners(this);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(viewcustomer, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void allcustomer_action() {
        AllCustomers all_customers = new AllCustomers();
        JPanel allcustomers = all_customers.initUI();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(allcustomers, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void newemployee_action() {
        NewEmployee new_employee = new NewEmployee();
        JScrollPane newemployee = new_employee.newEmployee();
        new_employee.submit.addActionListener((event) -> new_employee.employeeaddaction(this));
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        details_panel.removeAll();
        details_panel.add(newemployee, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);

    }

    public void viewemployee_action() {
        ViewEmployee view_employee = new ViewEmployee();
        JScrollPane viewemployee = view_employee.viewEmployee();
        view_employee.search.addActionListener((event) -> view_employee.searchemployeeaction(this));
        view_employee.edit.addActionListener((event) -> view_employee.editaction(this));
        view_employee.delete.addActionListener((event) -> view_employee.deleteaction(this));
        view_employee.save.addActionListener((event) -> view_employee.saveaction(this));
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(viewemployee, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);

    }

    public void allemployees_action() {
        AllEmployees all_employees = new AllEmployees();
        JPanel allemployees = all_employees.initUI();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(allemployees, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void placeorder_action() {
        PlaceOrder place_order = new PlaceOrder();
        JPanel placeorder = place_order.newOrder();
        place_order.setlistners(this);
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(placeorder, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void revieworder_action() {
        ReviewOrder review_order = new ReviewOrder();
        JPanel revieworder = review_order.initUI();
        review_order.setlistners(this);
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(revieworder, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void allorders_action() {
        AllOrder all_orders = new AllOrder();
        JPanel allorders = all_orders.initUI();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(allorders, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void transactions_action() {
        InflowsOutflows transactions = new InflowsOutflows();
        JPanel transaction = transactions.initUI();
        transactions.setlistners(this);
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(transactions, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void cashflow_action() {
        CashFlow cashflow = new CashFlow();
        JPanel cash_flow = cashflow.initUI();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(cash_flow, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void mail_action() {
        Mail mail = new Mail();
        JPanel mail_panel = mail.initUI();
        mail.setlistners(this);
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        details_panel.removeAll();
        details_panel.add(mail_panel, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);
    }

    public void set_background() {
        // ######## Setting background
        String BACKHGROUND_IMAGE_URL = "./src/project/resources/background2.jpeg";
        final ImageIcon backgroundImage = new ImageIcon(BACKHGROUND_IMAGE_URL);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(BACKHGROUND_IMAGE_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(780, 612, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        mainPanel = new JLabel(imageIcon) {
            @Override
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                Dimension lmPrefSize = getLayout().preferredLayoutSize(this);
                size.width = Math.max(size.width, lmPrefSize.width);
                size.height = Math.max(size.height, lmPrefSize.height);
                return size;
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(Box.createGlue());
    }

    public static void main(String[] args) {
        WrapperClass wrapper = new WrapperClass();
        wrapper.get_element();
    }
}
