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
     public JPanel initUI()
     {
      initComponents();
      save.setVisible(false);  
      //GetData();
      return(this);
     
     }
 
public void setlistners(JFrame jframe)
    {
        
        edit.addActionListener((event) ->editaction());
        month.addActionListener((event) ->viewinflowoutflow());
         save.addActionListener((event) ->saveaction(jframe));
    }
    public InflowsOutflows() {
        initComponents();
    }
 
    public void viewinflowoutflow() 
    {String month2=(String) month.getSelectedItem();    
     String year2=(String) year.getSelectedItem();
     
     String date=  month2+"-"+year2;
      
        SQL sql=new SQL();
        String query = String.format("SELECT * FROM INFLOW WHERE date='%s'",date);
      
        System.out.println(query);
      String totalinflow="0";
        try{
            ResultSet rs =sql.execute(query,true);
            if (rs.next()) {
                
                
                String loans2 = rs.getString("loans");
                loans.setText(loans2);
                String customer_gift2 = rs.getString("customer_gift");
                customer_gifts.setText(customer_gift2);
                String other_revenue2 = rs.getString("other_revenue");
                other_revenue.setText(other_revenue2);
                totalinflow= Integer.toString (Integer.parseInt(loans2)+Integer.parseInt(customer_gift2)+Integer.parseInt(other_revenue2));    
                total_inflows.setText(totalinflow);
                sql.c.close();
                rs.close();
               
               
            } 
            else {
                loans.setText("");
               
                customer_gifts.setText("");
               
                other_revenue.setText("");
              
                total_inflows.setText("");
                 netcashflow.setText("");  
  sql.c.close();
                rs.close();
                System.out.println("False");
                //message.setText(" Invalid user.. ");
            
            }
        }
        catch ( NumberFormatException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         
        }
            query= String.format("SELECT * FROM OUTFLOW WHERE date='%s'",date);
      
        System.out.println(query);
      
        try{
            ResultSet rs =sql.execute(query,true);
            if (rs.next()) {
                
                
              
                String electricity_costs2 = rs.getString("electricity_costs");
                electricity_costs.setText(electricity_costs2);
                String rent_costs2 = rs.getString("rent_costs");
                rent_costs.setText(rent_costs2);
                String loan_payments2 = rs.getString("loan_payments");
                loan_payment.setText(loan_payments2);
                String other_costs2 = rs.getString("other_costs");
                other_costs.setText(other_costs2);       

String totaloutflow= Integer.toString (Integer.parseInt(electricity_costs2)+
 Integer.parseInt(rent_costs2)+Integer.parseInt(loan_payments2)+Integer.parseInt(other_costs2));    
           total_outflows.setText(totaloutflow);
             String netcash=Integer.toString (Integer.parseInt(totalinflow)+
   Integer.parseInt(totaloutflow));  
             netcashflow.setText(netcash);   
             sql.c.close();
           rs.close();
  
            } 
            else {
                
                electricity_costs.setText("");
                rent_costs.setText("");
                loan_payment.setText("");
                other_costs.setText("");       

   
           total_outflows.setText("");
           
             netcashflow.setText(""); 
             sql.c.close();
                rs.close();
                System.out.println("False");
                //message.setText(" Invalid user.. ");

            }
        }
        catch ( NumberFormatException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                  }

    
    }
    
        public boolean editaction ()
    {   int selected=1;
        if (selected ==1)
        {
           
            other_costs.setEnabled(true);
            other_revenue.setEnabled(true);
            rent_costs.setEnabled(true);
            loan_payment.setEnabled(true);
            loans.setEnabled(true);
            customer_gifts.setEnabled(true);
            electricity_costs.setEnabled(true);
            

                    
            save.setVisible(true);
            return true;
        }
        else
        {
            //JOptionPane.showMessageDialog( "Select a date to Edit.","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
  
   public boolean saveaction(JFrame jframe )
    {   SQL sql = new SQL();

        String other_revenue2 =  other_revenue.getText();
        String loans2 =  loans.getText();
        String customer_gifts2 = customer_gifts.getText();
        
        String electricity_costs2 =  electricity_costs.getText();
        String rent_costs2 =  rent_costs.getText();
        String loan_payment2 = loan_payment.getText();
        String other_costs2 = other_costs.getText();
        
        // ------------
    String month2=(String) month.getSelectedItem();    
     String year2=(String) year.getSelectedItem();
     
     String date=  month2+"-"+year2;
        
        System.out.println(date);
        //-----------------
              boolean other_revenue3 = Pattern.matches("[0-9]+", other_revenue2);
              boolean loans3 = Pattern.matches("[0-9]+", loans2);
              boolean customer_gifts3 = Pattern.matches("[0-9]+", customer_gifts2);
             
              boolean electricity_costs3 = Pattern.matches("[0-9]+", electricity_costs2);
              boolean loan_payment3 = Pattern.matches("[0-9]+", loan_payment2);
              boolean other_costs3 = Pattern.matches("[0-9]+", other_costs2);
              boolean rent_costs3 = Pattern.matches("[0-9]+", rent_costs2);
              

              
              
              
              
              
              
              
              
        if (loans3 == false||other_revenue3 == false||customer_gifts3 == false)
        {
            JOptionPane.showMessageDialog(jframe, "Invalid Inflow value. Try again","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }    
        if (electricity_costs3 == false||loan_payment3 == false||other_costs3 == false||rent_costs3 == false)
        {
            JOptionPane.showMessageDialog(jframe, "Invalid Outflow value. Try again","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }    

        String query = String.format("UPDATE inflow set loans = '%s', customer_gift= '%s',other_revenue ='%s' where date='%s'", loans2, customer_gifts2,other_revenue2,date);
        System.out.println(query);
        String query2 = String.format("UPDATE outflow set electricity_costs = '%s',rent_costs ='%s',loan_payments='%s',other_costs='%s' where date='%s'", electricity_costs2, rent_costs2,loan_payment2,other_costs2,date);
        System.out.println(query);
        try
        {
            int val = sql.updateQuery(query);
            int val2 = sql.updateQuery(query2);
            System.out.println(val);
            if (0==val||0==val2) 
            {
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
            } 
            else 
            {
                JOptionPane.showMessageDialog(jframe, "Invalid details","Error",JOptionPane.ERROR_MESSAGE); 
                //System.out.println("False");
                //message.setText(" Invalid user.. ");
                return false;
            }
        }
        catch ( HeadlessException e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            JOptionPane.showMessageDialog(jframe, "ERROR!!","Error",JOptionPane.ERROR_MESSAGE); 
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
        jLabel2 = new javax.swing.JLabel();
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

        jLabel1.setFont(new java.awt.Font("Century", 1, 48)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel3.setText("REVENUE ");

        jLabel4.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel4.setText("OTHER REVENUE");

        jLabel5.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel5.setText("LOANS");

        jLabel6.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel6.setText("CUSTOMER GIFTS");

        revenue.setEnabled(false);
        revenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revenueActionPerformed(evt);
            }
        });

        other_revenue.setEnabled(false);
        other_revenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                other_revenueActionPerformed(evt);
            }
        });

        loans.setEnabled(false);
        loans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loansActionPerformed(evt);
            }
        });

        customer_gifts.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Century", 1, 36)); // NOI18N
        jLabel8.setText("OUTFLOWS");

        jLabel9.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel9.setText("EMPLOYEE WAGES");

        jLabel10.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel10.setText("ELCETRICITY COSTS");

        jLabel11.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel11.setText("RENT COSTS");

        jLabel12.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel12.setText("OTHER COSTS");

        other_costs.setEnabled(false);

        loan_payment.setEnabled(false);

        rent_costs.setEnabled(false);

        electricity_costs.setEnabled(false);
        electricity_costs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                electricity_costsActionPerformed(evt);
            }
        });

        employee_wages.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel13.setText("TOTAL INFLOWS");

        total_inflows.setEnabled(false);

        total_outflows.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel14.setText("TOTAL OUTFLOWS");

        jLabel15.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel15.setText("NET CASH FLOW");

        netcashflow.setEnabled(false);

        edit.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        save.setText("SAVE");

        jLabel16.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel16.setText("YEAR");

        jLabel17.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel17.setText("MONTH");

        year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020" }));
        year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearActionPerformed(evt);
            }
        });

        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", " " }));

        jLabel7.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel7.setText("LOAN PAYMENT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(187, 187, 187)
                                    .addComponent(jLabel3))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(customer_gifts)
                            .addComponent(revenue, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(loans, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(total_inflows, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(employee_wages)
                                    .addComponent(electricity_costs)
                                    .addComponent(rent_costs, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(loan_payment)
                                    .addComponent(other_costs)
                                    .addComponent(total_outflows)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(120, 120, 120))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(other_revenue, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(netcashflow, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employee_wages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(electricity_costs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rent_costs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loan_payment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(other_costs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(total_outflows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 77, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(revenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(other_revenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(loans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customer_gifts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_inflows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)))))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(netcashflow, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6351, 6351, 6351))
        );
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
    private javax.swing.JLabel jLabel2;
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
