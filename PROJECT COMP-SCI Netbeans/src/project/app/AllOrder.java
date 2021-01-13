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


public class AllOrder extends javax.swing.JPanel {

    
    public JPanel initUI()
     {
      initComponents();
      GetData();
      return(this);
     
     }
    
    public AllOrder() {
        initComponents();
    }
public void GetData() {
        setVisible(true);
        SQL sql = new SQL();
        String[] user_list = {};
        String check_query =  "select * from orders;";
        try
            
        {
            System.out.println(check_query);
            ResultSet rs =sql.execute(check_query, true);
            ArrayList<String> user_array_list = new ArrayList<>();
            int row=0;
            
                    
            while (rs.next()) {
                int col=0;
                String  id = rs.getString("id");
                jTable1.setValueAt(id, row, 0);
                String  customer_name = rs.getString("customer_name");  
                   jTable1.setValueAt(customer_name, row, 1);
                String  cloth_data = rs.getString("cloth_data");
                   jTable1.setValueAt(cloth_data, row,2 );
                String  cost = rs.getString("cost");  
                   jTable1.setValueAt(cost, row, 3);
                String  priority = rs.getString("priority");  
                   jTable1.setValueAt(priority, row, 4);
                    String  date = rs.getString("date");  
                   jTable1.setValueAt(date, row, 5);
                   String  status = rs.getString("status");  
                   jTable1.setValueAt(status, row, 6);

                   
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

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jTextField1.setText("ORDER HISTORY");
        jTextField1.setOpaque(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ORDER ID", "CUSTOMER", "ORDER", "COST", "PRIORITY", "DATE", "STATUS"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 710, 325));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
