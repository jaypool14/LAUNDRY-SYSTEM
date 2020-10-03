package project.app;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;


import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
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

public class WrapperClass extends JFrame 
{
    JMenuBar menuBar; 
    JPanel jpanel;
    Login login;
    MainMenu main_menu;
    JLabel mainPanel;
    JPanel details_panel = new JPanel();
    GridBagConstraints constraints = new GridBagConstraints();

    public void get_element()
    {
        login = new Login();
        main_menu = new MainMenu();
        menuBar = main_menu.createMenuBar();
        jpanel = login.createLogin();
        set_background();
        login.submit.addActionListener((event) -> login_action());
        main_menu.customer_sub.addActionListener((event) -> newcustomer_action());
        main_menu.customer_sub2.addActionListener((event) -> viewcustomer_action());
        main_menu.new_employee.addActionListener((event) -> newemployee_action());
        main_menu.manage_employee.addActionListener((event) -> viewemployee_action());
        main_menu.place_orders.addActionListener((event) -> placeorder_action());
        //setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //menuBar.add(Box.createRigidArea(new Dimension(1000,0)));
        
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 0;
        constraints.gridy = 0;   
        mainPanel.add(jpanel,constraints);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;  
        constraints.gridx = 0;
        constraints.gridy = 0;  
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        mainPanel.add(menuBar,constraints);

        //constraints.anchor = GridBagConstraints.WEST;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        //constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        //constraints.weighty = 0;
        //constraints.weightx = 0;
        //constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        //mainPanel.setDefaultGridheight(constraints);
        mainPanel.add(details_panel,constraints);
        //mainPanel.add(menuBar,constraints);
        //setSize(800, 675);

        //asetUndecorated(true);
        menuBar.setVisible(false);
        details_panel.setLayout(new GridBagLayout());
        //details_panel.setPreferredSize(new Dimension(300,500));
        details_panel.setVisible(true);
        details_panel.setOpaque(false);
        setTitle("LAUNDRY MANAGEMENT SYSTEM");

        JScrollPane panelPane = new JScrollPane(mainPanel);
        //add(panelPane, BorderLayout.CENTER);
        add(panelPane);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void login_action()
    {
        if (login.submit_action()==true)
        {
            jpanel.setVisible(false);
            menuBar.setVisible(true);
            
        }
        else
            JOptionPane.showMessageDialog(this, "InvalidLogin.","Error",JOptionPane.ERROR_MESSAGE); 
    }

    public void newcustomer_action()
    {
        NewCustomer new_customer = new NewCustomer();
        JScrollPane newcustomer = new_customer.newCustomer();
        new_customer.submit.addActionListener((event) -> new_customer.customeraddaction(this));

        //constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.gridx = 0;
        //constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;  
        constraints.gridx = 0;
        constraints.gridy = 0;   

        details_panel.removeAll(); 
        details_panel.add(newcustomer,constraints);
        details_panel.updateUI();
        details_panel.setVisible(true); 
    }

    public void viewcustomer_action()
    {
        ViewCustomer view_customer = new ViewCustomer();
        JScrollPane viewcustomer = view_customer.viewCustomer();
        view_customer.search.addActionListener((event) -> view_customer.searchcustomeraction (this));
        view_customer.edit.addActionListener((event) -> view_customer.editaction (this));
        view_customer.delete.addActionListener((event) -> view_customer.deleteaction (this));
        view_customer.save.addActionListener((event) -> view_customer.saveaction (this));
        //view_customer.submit.addActionListener((event) -> new_customer.customeraddaction(this));

        //constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.gridx = 0;
        //constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;   
        constraints.gridx = 0;
        constraints.gridy = 0;   

        details_panel.removeAll(); 
        details_panel.add(viewcustomer,constraints);
        details_panel.updateUI();
        details_panel.setVisible(true); 
    }

    public void newemployee_action()
    {
        NewEmployee new_employee = new NewEmployee();
        JScrollPane newemployee = new_employee.newEmployee();
        new_employee.submit.addActionListener((event) -> new_employee.employeeaddaction(this)); 
        //constraints.anchor = GridBagConstraints.SOUTH;
        //constraints.gridx = 0;
        //constraints.gridy = 0;   
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;  
        constraints.gridx = 0;
        constraints.gridy = 0;   
        details_panel.removeAll();
        details_panel.add(newemployee, constraints);
        details_panel.updateUI();
        details_panel.setVisible(true);

    }
    public void viewemployee_action()
    {
        ViewEmployee view_employee = new ViewEmployee();
        JScrollPane viewemployee = view_employee.viewEmployee();
        view_employee.search.addActionListener((event) -> view_employee.searchemployeeaction (this));
        view_employee.edit.addActionListener((event) -> view_employee.editaction (this));
        view_employee.delete.addActionListener((event) -> view_employee.deleteaction (this));
        view_employee.save.addActionListener((event) -> view_employee.saveaction (this));
        //view_customer.submit.addActionListener((event) -> new_customer.customeraddaction(this));

        //constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.gridx = 0;
        //constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;   
        constraints.gridx = 0;
        constraints.gridy = 0;   

        details_panel.removeAll(); 
        details_panel.add(viewemployee,constraints);
        details_panel.updateUI();
        details_panel.setVisible(true); 
    }
    
    public void placeorder_action()
    {
        PlaceOrder place_order = new PlaceOrder();
        JPanel placeorder = place_order.newOrder();
        // place_order.search.addActionListener((event) -> view_employee.searchemployeeaction (this));
        // view_employee.edit.addActionListener((event) -> view_employee.editaction (this));
        // view_employee.delete.addActionListener((event) -> view_employee.deleteaction (this));
        // view_employee.save.addActionListener((event) -> view_employee.saveaction (this));
        //view_customer.submit.addActionListener((event) -> new_customer.customeraddaction(this));

        //constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.gridx = 0;
        //constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;   
        constraints.gridx = 0;
        constraints.gridy = 0;   

        details_panel.removeAll(); 
        details_panel.add(placeorder,constraints);
        details_panel.updateUI();
        details_panel.setVisible(true); 
    }


    public void set_background()
    {
        // ######## Setting background
        String BACKHGROUND_IMAGE_URL = "./src/project/resources/background3.jpeg";
        final ImageIcon backgroundImage = new ImageIcon(BACKHGROUND_IMAGE_URL);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(BACKHGROUND_IMAGE_URL));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(780, 612, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        mainPanel = new JLabel(imageIcon) 
        {
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
        //constraints.weightx = 1.0;
        //constraints.anchor = GridBagConstraints.WEST;
        //constraints.gridwidth = GridBagConstraints.REMAINDER;
        // Let's put a filler bottom component that will push the rest to the top
        //constraints.weighty = 1.0;
        mainPanel.add(Box.createGlue());
        //mainPanel.setLayout(new BoxLayout (mainPanel, BoxLayout.Y_AXIS));
    }

    public static void main(String[] args) 
    {
        WrapperClass wrapper = new WrapperClass();
        wrapper.get_element();
    }
}