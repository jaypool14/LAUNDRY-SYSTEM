import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.imageio.*;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.Box;
    
    public class WrapperClass extends JFrame {
        JMenuBar menuBar; 
        JPanel jpanel;
        Login login;
        
        public void get_element(){
            login = new Login();
            var main_menu = new MainMenu();
            menuBar = main_menu.createMenuBar();
            jpanel = login.createLogin();
            login.submit.addActionListener((event) -> login_action());
            
            // ######## Setting background
            String BACKHGROUND_IMAGE_URL = ".\\background.jpeg";
            final ImageIcon backgroundImage = new ImageIcon(BACKHGROUND_IMAGE_URL);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(BACKHGROUND_IMAGE_URL));
            } 
            catch (IOException e) {
            e.printStackTrace();
            }
            Image dimg = img.getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            JLabel mainPanel = new JLabel(imageIcon) 
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
            // ##############
            mainPanel.setLayout(new GridBagLayout());
            setExtendedState(JFrame.MAXIMIZED_BOTH); 
            //menuBar.add(Box.createRigidArea(new Dimension(1000,0)));
         
            //setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            //constraints.insets = new Insets(10, 10, 10, 10);
            constraints.weightx = 1.0;
            constraints.anchor = GridBagConstraints.WEST;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            // Let's put a filler bottom component that will push the rest to the top
            constraints.weighty = 1.0;
            mainPanel.add(Box.createGlue(), constraints);
        
               
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.gridx = 0;
            constraints.gridy = 0;   
            mainPanel.add(jpanel,constraints);
       
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.fill = GridBagConstraints.HORIZONTAL;  
            constraints.gridx = 0;
            constraints.gridy = 0;   
            mainPanel.add(menuBar,constraints);
 
            //jpanel.setSize(250, 125);
            
            //asetUndecorated(true);
            menuBar.setVisible(false);
            setTitle("LAUNDRY MANAGEMENT SYSTEM");
        
            add(mainPanel);
            pack();
            setVisible(true);
    }
    
    public void login_action(){
        if (login.submit_action()==true)
        {
            menuBar.setVisible(true);
            jpanel.setVisible(false);
        }
        else{
        JOptionPane.showMessageDialog(this, "InvalidLogin.","Error",JOptionPane.ERROR_MESSAGE); 
        }
    
    }
    public static void main(String[] args) {
          var wrapper = new WrapperClass();
          wrapper.get_element();
    }
}