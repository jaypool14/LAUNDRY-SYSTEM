import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class ColumnHeader  extends JFrame{

  //CONSTRUCT
    public ColumnHeader()
    {

    //TITLE,CLOSE
        super("JTABLE TUTOR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

      //SET DATA
        Object[][] data=
          {
              {"1","Man Utd","Luis Van gaal","86"},
                  {"2","Chelsea","Jose Mourinho","84"},
                  {"3","Man City","Manuele Pelegrini","82"},
                  {"4","Arsenal","Arsene Wenger","80"},
                  {"5","Liverpool","Brendan Rodgers","78"},
          };

      //COLUMN HEADERS
        String[] columnHeaders={"Position","Team","manager","Points"};

        //CREATE TABLE
        JTable table=new JTable(data,columnHeaders);
        table.setPreferredScrollableViewportSize(new Dimension(500,80));

        //CUSTOM WIDTH
        TableColumn col0=table.getColumnModel().getColumn(0);
        TableColumn col1=table.getColumnModel().getColumn(1);
        TableColumn col2=table.getColumnModel().getColumn(2);

        col0.setPreferredWidth(5);
        col1.setPreferredWidth(300);
        col2.setPreferredWidth(150);

        //SCROLLPNAE
        JScrollPane pane=new JScrollPane(table);
        getContentPane().add(pane);

    }

    //MAIN METHOD
    public static void main(String[] args) {
      ColumnHeader c=new ColumnHeader();
      c.setVisible(true);
    }
}
