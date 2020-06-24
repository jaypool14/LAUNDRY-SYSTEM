import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
public class OrderTable extends JFrame{
    JTable table = new JTable(0, 2);
    JButton addbtn;
    JPanel panel;
    public void initUI()
    {
        addbtn = new JButton("ADD");
        addbtn.addActionListener((event) -> addrow (table));
        setVisible(true);
        setSize(600, 400);
        newTable();
        add(panel);
        add(addbtn);      
    }

    public JTable newTable(){
        panel = new JPanel(new FlowLayout());
        //GridBagConstraints constraints = new GridBagConstraints();
        //constraints.insets = new Insets(20, 20, 20, 20);
        Font font = new Font("Verdana", Font.PLAIN, 12);
        table.setFont(font);
        table.setRowHeight(30);
        //table.setBackground(Color.orange);
        //table.setForeground(Color.white);
        FlowLayout experimentLayout = new FlowLayout();
        setLayout(experimentLayout);
        TableColumn testColumn = table.getColumnModel().getColumn(0);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Asia");
        comboBox.addItem("Europe");
        comboBox.addItem("North America");
        comboBox.addItem("South America");
        comboBox.addItem("Africa");
        comboBox.addItem("Antartica");
        comboBox.addItem("Australia");
        comboBox.setSelectedItem("ASIA");
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        testColumn.setCellEditor(new DefaultCellEditor(comboBox));
                
        model.addRow(new Object[]{"ASIA", "1"});
        //constraints.anchor = GridBagConstraints.NORTH;
        //constraints.fill = GridBagConstraints.HORIZONTAL;

        //constraints.gridx = 0;
        //constraints.gridy = 0;
        panel.add(new JScrollPane(table));
        return table;
    }

    public boolean addrow(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{"ASIA", "1"});
        return true;
    }

    public static void main(String[] args) 
    {
        var login = new OrderTable();
        login.initUI();
    }

}