package project.app;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Dimension;

public class AutoSuggest extends JPanel {

    JTextField tf;
    JComboBox combo = new JComboBox();
    Vector<String> v = new Vector<String>();
    String[] person = {};

    public JComboBox create_box(String check_query) {
        GetUsers users = new GetUsers();
        combo.setEditable(true);
        combo.setPreferredSize(new Dimension(250, 20));
        tf = (JTextField) combo.getEditor().getEditorComponent();
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        String text = tf.getText();
                        if (text.length() == 0) {
                            combo.hidePopup();
                            setModel(new DefaultComboBoxModel(v), "");
                        } else if (text.length() > 2) {
                            person = users.getusers_list(text, check_query);
                            System.out.println(Arrays.toString(person));
                            v.clear();
                            for (int i = 0; i < person.length; i++) {
                                v.addElement(person[i]);
                            }
                            DefaultComboBoxModel m = getSuggestedModel(v, text);
                            if (m.getSize() == 0 || hide_flag) {
                                combo.hidePopup();
                                hide_flag = false;
                            } else {
                                setModel(m, text);
                                combo.showPopup();
                            }
                        }
                    }
                });
            }

            public void keyPressed(KeyEvent e) {
                String text = tf.getText();
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    if (!v.contains(text)) {
                        v.addElement(text);
                        Collections.sort(v);
                        setModel(getSuggestedModel(v, text), text);
                    }
                    hide_flag = true;
                } else if (code == KeyEvent.VK_ESCAPE) {
                    hide_flag = true;
                } else if (code == KeyEvent.VK_RIGHT) {
                    for (int i = 0; i < v.size(); i++) {
                        String str = v.elementAt(i);
                        System.out.println(str);
                        if (str.startsWith(text)) {
                            combo.setSelectedIndex(-1);
                            tf.setText(str);
                            return;
                        }
                    }
                }
            }
        });

        setModel(new DefaultComboBoxModel(v), "");
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder("AutoSuggestion Box"));
        p.add(combo, BorderLayout.NORTH);
        add(p);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(300, 150));
        return combo;
    }
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        combo.setModel(mdl);
        combo.setSelectedIndex(-1);
        tf.setText(str);
    }

    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            m.addElement(s);
        }
        return m;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(300, 150));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AutoSuggest box = new AutoSuggest();
        String check_query = "SELECT * FROM customer WHERE name LIKE '%%%s%%' OR email LIKE '%%%s%%';";
        frame.add(box.create_box(check_query));
        frame.setVisible(true);
    }
}
