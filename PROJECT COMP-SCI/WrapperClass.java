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
        add(menuBar,BorderLayout.NORTH);
        add(jpanel,BorderLayout.CENTER);
        jpanel.setSize(250, 125);
        setSize(1366, 768);
        setVisible(true);
        menuBar.setVisible(false);
    }
    
    public void login_action(){
        if (login.submit_action()==true)
        {
            menuBar.setVisible(true);
            jpanel.setVisible(false);
        }
    
    }
    public static void main(String[] args) {
          var wrapper = new WrapperClass();
          wrapper.get_element();
    }
}