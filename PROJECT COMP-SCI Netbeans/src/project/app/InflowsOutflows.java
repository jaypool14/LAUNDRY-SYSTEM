/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.app;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InflowsOutflows extends javax.swing.JPanel {

    /**
     * Creates new form AllCashFlow
     */
    public JPanel initUI() {
        initComponents();
        save.setVisible(false);
        //GetData();
        return (this);

    }

    public void setlistners(JFrame jframe) {

        edit.addActionListener((event) -> editaction());
        month.addActionListener((event) -> viewinflowoutflow());
        save.addActionListener((event) -> saveaction(jframe));
    }

    public InflowsOutflows() {
        initComponents();
    }

    public void viewinflowoutflow() {
        String month2 = (String) month.getSelectedItem();
        String year2 = (String) year.getSelectedItem();

        String date = month2 + "-" + year2;

        SQL sql = new SQL();
        String query = String.format("SELECT cost FROM orders WHERE date like '___%s'", date);
        System.out.println(query);
        int revenue_num=0,wages_num=0;
        try {
            ResultSet rs = sql.execute(query, true);
            while (rs.next()) {
                if (rs.getString("cost") != null)
                revenue_num += (int)Float.parseFloat(rs.getString("cost"));
            }
            sql.c.close();
            rs.close();
            query = String.format("select salary from employee");
            rs = sql.execute(query, true);
            while (rs.next()) {
                if (rs.getString("salary") != null)
                wages_num += (int)Float.parseFloat(rs.getString("salary"));
            }
            sql.c.close();
                rs.close();
        } catch (NumberFormatException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        revenue.setText(Integer.toString(revenue_num));
        employee_wages.setText(Integer.toString(wages_num));
        query = String.format("SELECT * FROM INFLOW WHERE date='%s'", date);

        System.out.println(query);
        String totalinflow = "0",totaloutflow = "0";
        try {
            ResultSet rs = sql.execute(query, true);
            if (rs.next()) {
                String loans2 = rs.getString("loans");
                loans.setText(loans2);
                String customer_gift2 = rs.getString("customer_gift");
                customer_gifts.setText(customer_gift2);
                String other_revenue2 = rs.getString("other_revenue");
                other_revenue.setText(other_revenue2);
                totalinflow = Integer.toString(revenue_num + Integer.parseInt(loans2) + 
                        Integer.parseInt(customer_gift2) + Integer.parseInt(other_revenue2));
                total_inflows.setText(totalinflow);
                sql.c.close();
                rs.close();

            } else {
                loans.setText("");
                customer_gifts.setText("");
                other_revenue.setText("");
                totalinflow = Integer.toString(revenue_num);
                sql.c.close();
                rs.close();
                System.out.println("False");
                //message.setText(" Invalid user.. ");

            }
        } catch (NumberFormatException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        query = String.format("SELECT * FROM OUTFLOW WHERE date='%s'", date);
        System.out.println(query);
        try {
            ResultSet rs = sql.execute(query, true);
            if (rs.next()) {

                String electricity_costs2 = rs.getString("electricity_costs");
                electricity_costs.setText(electricity_costs2);
                String rent_costs2 = rs.getString("rent_costs");
                rent_costs.setText(rent_costs2);
                String loan_payments2 = rs.getString("loan_payments");
                loan_payment.setText(loan_payments2);
                String other_costs2 = rs.getString("other_costs");
                other_costs.setText(other_costs2);

                totaloutflow = Integer.toString(wages_num + Integer.parseInt(electricity_costs2)
                        + Integer.parseInt(rent_costs2) + Integer.parseInt(loan_payments2) + Integer.parseInt(other_costs2));
                total_outflows.setText(totaloutflow);
                sql.c.close();
                rs.close();

            } else {

                electricity_costs.setText("");
                rent_costs.setText("");
                loan_payment.setText("");
                other_costs.setText("");
                totaloutflow = Integer.toString(wages_num);
                sql.c.close();
                rs.close();
                System.out.println("False");
                //message.setText(" Invalid user.. ");
            }
        } catch (NumberFormatException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        total_inflows.setText(totalinflow);
        total_outflows.setText(totaloutflow);
                netcashflow.setText("");
        String netcash = Integer.toString(Integer.parseInt(totalinflow)
                        - Integer.parseInt(totaloutflow));
                netcashflow.setText(netcash);

    }

    public boolean editaction() {
        int selected = 1;
        if (selected == 1) {

            other_costs.setEnabled(true);
            other_revenue.setEnabled(true);
            rent_costs.setEnabled(true);
            loan_payment.setEnabled(true);
            loans.setEnabled(true);
            customer_gifts.setEnabled(true);
            electricity_costs.setEnabled(true);

            save.setVisible(true);
            return true;
        } else {
            //JOptionPane.showMessageDialog( "Select a date to Edit.","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean saveaction(JFrame jframe) {
        SQL sql = new SQL();

        String other_revenue2 = other_revenue.getText();
        String loans2 = loans.getText();
        String customer_gifts2 = customer_gifts.getText();

        String electricity_costs2 = electricity_costs.getText();
        String rent_costs2 = rent_costs.getText();
        String loan_payment2 = loan_payment.getText();
        String other_costs2 = other_costs.getText();

        // ------------
        String month2 = (String) month.getSelectedItem();
        String year2 = (String) year.getSelectedItem();

        String date = month2 + "-" + year2;

        System.out.println(date);
        //-----------------
        boolean other_revenue3 = Pattern.matches("[0-9]+", other_revenue2);
        boolean loans3 = Pattern.matches("[0-9]+", loans2);
        boolean customer_gifts3 = Pattern.matches("[0-9]+", customer_gifts2);

        boolean electricity_costs3 = Pattern.matches("[0-9]+", electricity_costs2);
        boolean loan_payment3 = Pattern.matches("[0-9]+", loan_payment2);
        boolean other_costs3 = Pattern.matches("[0-9]+", other_costs2);
        boolean rent_costs3 = Pattern.matches("[0-9]+", rent_costs2);

        if (loans3 == false || other_revenue3 == false || customer_gifts3 == false) {
            JOptionPane.showMessageDialog(jframe, "Invalid Inflow value. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (electricity_costs3 == false || loan_payment3 == false || other_costs3 == false || rent_costs3 == false) {
            JOptionPane.showMessageDialog(jframe, "Invalid Outflow value. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String query = String.format("UPDATE inflow set loans = '%s', customer_gift= '%s',other_revenue ='%s' where date='%s'", loans2, customer_gifts2, other_revenue2, date);
        System.out.println(query);
        String query2 = String.format("UPDATE outflow set electricity_costs = '%s',rent_costs ='%s',loan_payments='%s',other_costs='%s' where date='%s'", electricity_costs2, rent_costs2, loan_payment2, other_costs2, date);
        System.out.println(query);
        try {
            int val = sql.updateQuery(query);
            int val2 = sql.updateQuery(query2);
            System.out.println(val);
            if (0 == val || 0 == val2) {
                //message.setText(" Hello " + userName+ "");
                /*electricity_costs.setText("");
                rent_costs.setText("");
                loan_payment.setText("");
                other_costs.setText("");       
   loans.setText("");
               
                customer_gifts.setText("");
               
                other_revenue.setText("");
              
                total_inflows.setText("");
                 netcashflow.setText("");  

   
           total_outflows.setText("");*/

                edit.setVisible(true);
                save.setVisible(false);
                // selected = 0;
                JOptionPane.showMessageDialog(jframe, "Transactions Updated");
                return true;
            } else {
                JOptionPane.showMessageDialog(jframe, "Invalid details", "Error", JOptionPane.ERROR_MESSAGE);
                //System.out.println("False");
                //message.setText(" Invalid user.. ");
                return false;
            }
        } catch (HeadlessException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            JOptionPane.showMessageDialog(jframe, "ERROR!!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        revenue = new javax.swing.JTextField();
        other_revenue = new javax.swing.JTextField();
        loans = new javax.swing.JTextField();
        customer_gifts = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        other_costs = new javax.swing.JTextField();
        loan_payment = new javax.swing.JTextField();
        rent_costs = new javax.swing.JTextField();
        electricity_costs = new javax.swing.JTextField();
        employee_wages = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        total_inflows = new javax.swing.JTextField();
        total_outflows = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        netcashflow = new javax.swing.JTextField();
        edit = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox<>();
        month = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century", 1, 48)); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 11, -1, 79));

        jLabel3.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel3.setText("REVENUE ");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 160, -1, 25));

        jLabel4.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel4.setText("OTHER REVENUE");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 191, -1, 35));

        jLabel5.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel5.setText("LOANS");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 232, -1, 25));

        jLabel6.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel6.setText("CUSTOMER GIFTS");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 275, -1, 26));

        revenue.setDisabledTextColor(new java.awt.Color(51, 51, 255));
        revenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revenueActionPerformed(evt);
            }
        });
        add(revenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 164, 89, -1));

        other_revenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                other_revenueActionPerformed(evt);
            }
        });
        add(other_revenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 200, 89, -1));

        loans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loansActionPerformed(evt);
            }
        });
        add(loans, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 236, 89, -1));
        add(customer_gifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 280, 89, -1));

        jLabel8.setFont(new java.awt.Font("Century", 1, 36)); // NOI18N
        jLabel8.setText("INFLOWS");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 96, -1, 53));

        jLabel9.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel9.setText("EMPLOYEE WAGES");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, 28));

        jLabel10.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel10.setText("ELCETRICITY COSTS");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, 14));

        jLabel11.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel11.setText("RENT COSTS");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, 22));

        jLabel12.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel12.setText("OTHER COSTS");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, -1, -1));
        add(other_costs, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, 80, -1));
        add(loan_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 80, -1));
        add(rent_costs, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 80, -1));

        electricity_costs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                electricity_costsActionPerformed(evt);
            }
        });
        add(electricity_costs, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 80, -1));

        employee_wages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_wagesActionPerformed(evt);
            }
        });
        add(employee_wages, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 80, -1));

        jLabel13.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel13.setText("TOTAL INFLOWS");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 314, -1, 29));

        total_inflows.setEditable(false);
        total_inflows.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        total_inflows.setDisabledTextColor(new java.awt.Color(0, 204, 0));
        total_inflows.setEnabled(false);
        add(total_inflows, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 320, 89, -1));

        total_outflows.setDisabledTextColor(new java.awt.Color(51, 204, 0));
        total_outflows.setEnabled(false);
        add(total_outflows, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 80, -1));

        jLabel14.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel14.setText("TOTAL OUTFLOWS");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        jLabel15.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel15.setText("NET CASH FLOW");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 374, -1, 20));

        netcashflow.setEnabled(false);
        add(netcashflow, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 376, 114, -1));

        edit.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 439, 101, -1));

        save.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 439, 100, -1));

        jLabel16.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel16.setText("YEAR");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 37, -1, 53));

        jLabel17.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel17.setText("MONTH");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 37, -1, 53));

        year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020" }));
        year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearActionPerformed(evt);
            }
        });
        add(year, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 55, -1, -1));

        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", " " }));
        add(month, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 55, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel7.setText("LOAN PAYMENT");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, -1, 32));

        jLabel18.setFont(new java.awt.Font("Century", 1, 36)); // NOI18N
        jLabel18.setText("OUTFLOWS");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, -1, 53));
    }// </editor-fold>//GEN-END:initComponents

    private void revenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revenueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueActionPerformed

    private void electricity_costsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_electricity_costsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_electricity_costsActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearActionPerformed

    private void loansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loansActionPerformed

    private void other_revenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_other_revenueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_other_revenueActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveActionPerformed

    private void employee_wagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_wagesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_wagesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField customer_gifts;
    private javax.swing.JButton edit;
    private javax.swing.JTextField electricity_costs;
    private javax.swing.JTextField employee_wages;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField loan_payment;
    private javax.swing.JTextField loans;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JTextField netcashflow;
    private javax.swing.JTextField other_costs;
    private javax.swing.JTextField other_revenue;
    private javax.swing.JTextField rent_costs;
    private javax.swing.JTextField revenue;
    private javax.swing.JButton save;
    private javax.swing.JTextField total_inflows;
    private javax.swing.JTextField total_outflows;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
