package project.app;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mail extends javax.swing.JPanel {

    public JPanel initUI() {
        initComponents();
        return (this);
    }

    public Mail() {
        initComponents();
    }

    public void setlistners(JFrame jframe) {
        sendmail.addActionListener((event) -> sendmailaction());
    }

    public void sendmailaction() {
        String message_to_send = message.getText();
        String m_to = option.getSelectedItem().toString();
        SendMail obj = new SendMail();
        if (m_to.equals("CUSTOMERS")) {
            obj.send_mail("IMPORTANT INFO FOR ROSE LAUNDRY CUSTOMER", message_to_send, "joseph.parel@pathways.in");
        } else if (m_to.equals("EMPLOYEES")) {
            obj.send_mail("IMPORTANT INFO FOR ROSE LAUNDRY EMPLOYEE", message_to_send, "joseph.parel@pathways.in");
        } else {
            obj.send_mail("ROSE LAUNDRY", message_to_send, "joseph.parel@pathways.in");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        message = new javax.swing.JTextArea();
        option = new javax.swing.JComboBox<>();
        sendmail = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Century", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MAIL MANAGEMENT");

        setMinimumSize(new java.awt.Dimension(583, 464));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TYPE THE MESSAGE YOU WANT TO SEND HERE");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 400, 22));

        message.setColumns(20);
        message.setRows(5);
        add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 570, -1));

        option.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CUSTOMERS", "EMPLOYEES", "SEND TO ALL" }));
        option.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionActionPerformed(evt);
            }
        });
        add(option, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 140, 40));

        sendmail.setText("SEND MAIL");
        sendmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendmailActionPerformed(evt);
            }
        });
        add(sendmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 160, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void optionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optionActionPerformed

    private void sendmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextArea message;
    private javax.swing.JComboBox<String> option;
    private javax.swing.JButton sendmail;
    // End of variables declaration//GEN-END:variables
}
