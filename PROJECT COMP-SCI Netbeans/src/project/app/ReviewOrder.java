package project.app;

import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReviewOrder extends javax.swing.JPanel {

    public JPanel initUI() {
        initComponents();
        return (this);
    }

    public void setlistners(JFrame jframe) {
        view.addActionListener((event) -> GetData(id_text.getText()));
    }

    public boolean GetData(String order) {
        clearTable(jTable1);
        setVisible(true);
        SQL sql = new SQL();
        String check_query = String.format("select * from orders where id='%s';", order);
        try {
            System.out.println(check_query);
            ResultSet rs = sql.execute(check_query, true);
            int row = 0;
            int err = 0;
            while (rs.next()) {
                err = 1;
                String username = rs.getString("customer_name");
                String email = rs.getString("customer_email");
                cus_text.setText(username + ":" + email);
                String id = rs.getString("id");
                String priority = rs.getString("priority");
                prio_text.setText(priority);
                String cost = rs.getString("cost");
                cost_text.setText(cost);
                String date = rs.getString("date");
                date_text.setText(date);
                String cloth_data = rs.getString("cloth_data");
                String[] Details = cloth_data.split(",");
                System.out.print(Arrays.toString(Details));
                row = 0;
                for (int i = 0; i < Details.length; i++) {
                    addrow(jTable1);
                    System.out.println(Details[i]);
                    String[] data = Details[i].split("=");
                    jTable1.setValueAt(data[0], row, 0);
                    jTable1.setValueAt(data[1], row, 1);
                    row += 1;
                }
            }
            SQL.c.close();
            if (err == 0) {
                JOptionPane.showMessageDialog(this, "No Such Order", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return true;
    }

    public boolean addrow(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int last = model.getRowCount() - 1;
        model.addRow(new Object[]{"", ""});
        return true;
    }

    public void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().removeAllElements();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datelabel1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        priolabel = new javax.swing.JLabel();
        id_text = new javax.swing.JTextField();
        prio_text = new javax.swing.JTextField();
        cost_text = new javax.swing.JTextField();
        date_text = new javax.swing.JTextField();
        customerlabel = new javax.swing.JLabel();
        cus_text = new javax.swing.JTextField();
        orderlabel = new javax.swing.JLabel();
        datelabel = new javax.swing.JLabel();
        costlabel = new javax.swing.JLabel();
        datelabel2 = new javax.swing.JLabel();
        view = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        status_text = new javax.swing.JTextField();

        datelabel1.setFont(new java.awt.Font("Century", 1, 20)); // NOI18N
        datelabel1.setForeground(new java.awt.Color(255, 255, 255));
        datelabel1.setText("ORDER DATE");

        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Century", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REVIEW ORDER");

        priolabel.setFont(new java.awt.Font("Century", 1, 20)); // NOI18N
        priolabel.setForeground(new java.awt.Color(255, 255, 255));
        priolabel.setText("ORDER PRIORITY");

        id_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_textActionPerformed(evt);
            }
        });

        prio_text.setEditable(false);
        prio_text.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        prio_text.setBorder(null);
        prio_text.setOpaque(false);

        cost_text.setEditable(false);
        cost_text.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        cost_text.setBorder(null);
        cost_text.setOpaque(false);
        cost_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cost_textActionPerformed(evt);
            }
        });

        date_text.setEditable(false);
        date_text.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        date_text.setBorder(null);
        date_text.setOpaque(false);

        customerlabel.setFont(new java.awt.Font("Century", 1, 20)); // NOI18N
        customerlabel.setForeground(new java.awt.Color(255, 255, 255));
        customerlabel.setText("CUSTOMER");

        cus_text.setEditable(false);

        orderlabel.setFont(new java.awt.Font("Century", 1, 20)); // NOI18N
        orderlabel.setForeground(new java.awt.Color(255, 255, 255));
        orderlabel.setText("ORDER ID");

        datelabel.setFont(new java.awt.Font("Century", 1, 20)); // NOI18N
        datelabel.setForeground(new java.awt.Color(255, 255, 255));
        datelabel.setText("ORDER DATE");

        costlabel.setFont(new java.awt.Font("Century", 1, 20)); // NOI18N
        costlabel.setForeground(new java.awt.Color(255, 255, 255));
        costlabel.setText("TOTAL COST");

        datelabel2.setFont(new java.awt.Font("Century", 1, 20)); // NOI18N
        datelabel2.setForeground(new java.awt.Color(255, 255, 255));
        datelabel2.setText("ORDER STATUS");

        view.setText("VIEW ORDER");

        jScrollPane2.setOpaque(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TYPE OF CLOTH", "UNITS OF CLOTH"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setOpaque(false);
        jScrollPane2.setViewportView(jTable1);

        status_text.setEditable(false);
        status_text.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        status_text.setBorder(null);
        status_text.setOpaque(false);
        status_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_textActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(orderlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(customerlabel))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cus_text, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(id_text))
                                .addGap(18, 18, 18)
                                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(datelabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(status_text))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(costlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priolabel))
                                    .addGap(70, 70, 70)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cost_text)
                                        .addComponent(date_text)
                                        .addComponent(prio_text, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(orderlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(view)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cus_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prio_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cost_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datelabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void id_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_textActionPerformed

    private void cost_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cost_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cost_textActionPerformed

    private void status_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_status_textActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cost_text;
    private javax.swing.JLabel costlabel;
    private javax.swing.JTextField cus_text;
    private javax.swing.JLabel customerlabel;
    private javax.swing.JTextField date_text;
    private javax.swing.JLabel datelabel;
    private javax.swing.JLabel datelabel1;
    private javax.swing.JLabel datelabel2;
    private javax.swing.JTextField id_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel orderlabel;
    private javax.swing.JTextField prio_text;
    private javax.swing.JLabel priolabel;
    private javax.swing.JTextField status_text;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
