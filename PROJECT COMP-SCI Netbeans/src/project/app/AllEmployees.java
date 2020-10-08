/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.app;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class AllEmployees extends javax.swing.JPanel {
public static void main (String[]args)
{
     AllEmployees obj=new AllEmployees();
       obj.initComponents();
        obj.GetData();
               

}
    public JPanel initUI()
     {
      initComponents();
      GetData();
      return(this);
     
     }

       public void GetData() {
        setVisible(true);
        SQL sql = new SQL();
        String[] user_list = {};
        String check_query =  "select * from employee;";
        try
            
        {
            System.out.println(check_query);
            ResultSet rs =sql.execute(check_query, true);
            ArrayList<String> user_array_list = new ArrayList<>();
            int row=0;
            
                    
            while (rs.next()) {
                int col=0;
                String  username = rs.getString("name");
                jTable1.setValueAt(username, row, 0);
                String  joindate = rs.getString("joindate");  
                   jTable1.setValueAt(joindate, row, 2);
                String  designation = rs.getString("designation");
                   jTable1.setValueAt(designation, row,4 );
                String  email = rs.getString("email");  
                   jTable1.setValueAt(email, row, 3);
                String  number = rs.getString("number");  
                   jTable1.setValueAt(number, row, 1);
                    String  salary = rs.getString("salary");  
                   jTable1.setValueAt(salary, row, 5);

                   
               addrow (jTable1);
               row+=1;
               
                       
                
                
            }
            
               
            SQL.c.close();
        }
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //return false;
        }
      
    }
public boolean addrow(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int last = model.getRowCount()-1;
            model.addRow(new Object[]{"", "", "", "", ""});return true;

        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Century", 1, 36)); // NOI18N
        jLabel1.setText("ALL EMPLOYEES");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NAME", "NUMBER", "JOIN DATE", "EMAIL", "DESIGNATION", "SALARY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
