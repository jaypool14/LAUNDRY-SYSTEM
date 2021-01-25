/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.app;

import javax.mail.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public static void main(String[] args) {
        SendMail obj = new SendMail();
        // obj.send_mail("This is the Subject Line!", "<h1>This is actual message embedded in HTML tags</h1>");
    }

    public void create_bill(int id) {
        String head = "<!doctype html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <title>A simple, clean, and responsive HTML invoice template</title>\n"
                + "    \n"
                + "    <style>\n"
                + "    .invoice-box {\n"
                + "        max-width: 800px;\n"
                + "        margin: auto;\n"
                + "        padding: 30px;\n"
                + "        border: 1px solid #eee;\n"
                + "        box-shadow: 0 0 10px rgba(0, 0, 0, .15);\n"
                + "        font-size: 16px;\n"
                + "        line-height: 24px;\n"
                + "        font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                + "        color: #555;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table {\n"
                + "        width: 100%;\n"
                + "        line-height: inherit;\n"
                + "        text-align: left;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table td {\n"
                + "        padding: 5px;\n"
                + "        vertical-align: top;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr td:nth-child(2) {\n"
                + "        text-align: right;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.top table td {\n"
                + "        padding-bottom: 20px;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.top table td.title {\n"
                + "        font-size: 45px;\n"
                + "        line-height: 45px;\n"
                + "        color: #333;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.information table td {\n"
                + "        padding-bottom: 40px;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.heading td {\n"
                + "        background: #eee;\n"
                + "        border-bottom: 1px solid #ddd;\n"
                + "        font-weight: bold;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.details td {\n"
                + "        padding-bottom: 20px;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.item td{\n"
                + "        border-bottom: 1px solid #eee;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.item.last td {\n"
                + "        border-bottom: none;\n"
                + "    }\n"
                + "    \n"
                + "    .invoice-box table tr.total td:nth-child(2) {\n"
                + "        border-top: 2px solid #eee;\n"
                + "        font-weight: bold;\n"
                + "    }\n"
                + "    \n"
                + "    @media only screen and (max-width: 600px) {\n"
                + "        .invoice-box table tr.top table td {\n"
                + "            width: 100%;\n"
                + "            display: block;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        \n"
                + "        .invoice-box table tr.information table td {\n"
                + "            width: 100%;\n"
                + "            display: block;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "    }\n"
                + "    \n"
                + "    /** RTL **/\n"
                + "    .rtl {\n"
                + "        direction: rtl;\n"
                + "        font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                + "    }\n"
                + "    \n"
                + "    .rtl table {\n"
                + "        text-align: right;\n"
                + "    }\n"
                + "    \n"
                + "    .rtl table tr td:nth-child(2) {\n"
                + "        text-align: left;\n"
                + "    }\n"
                + "    </style>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <div class=\"invoice-box\">\n"
                + "        <table cellpadding=\"0\" cellspacing=\"0\">\n"
                + "            <tr class=\"top\">\n"
                + "                <td colspan=\"3\">\n"
                + "                    <table>\n"
                + "                        <tr>\n"
                + "<td class=\"title\" style=\"text-align: left\">"
                + "<img src=https://i.postimg.cc/FzZ5dz71/logo.png style=\"width:30%; max-width:300px;\">"
                + "                            </td>\n"
                + "                            \n"
                + "                            <td style=\"text-align: right\">\n"
                + "                                Invoice #: 123<br>\n"
                + "                                Created: January 18, 2021<br>\n"
                + "                                Due: February 18, 2021\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "            \n"
                + "            <tr class=\"information\">\n"
                + "                <td colspan=\"3\">\n"
                + "                    <table>\n"
                + "                        <tr>\n"
                + "                            <td style=\"text-align: left\">\n"
                + "                                JOS LAUNDRY, Inc.<br>\n"
                + "                                12345 Sunny Road<br>\n"
                + "                                Sunnyville, CA 12345\n"
                + "                            </td>\n"
                + "                            \n"
                + "                            <td style=\"text-align: right\">\n"
                + "                                Acme Corp.<br>\n"
                + "                                {NAME}<br>\n"
                + "                                {EMAIL}\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "            \n"
                + "            <tr class=\"heading\">\n"
                + "                <td  colspan=\"2\">\n"
                + "                    Payment Method\n"
                + "                </td>\n"
                + "                \n"
                + "                <td>\n"
                + "                    \n"
                + "                </td>\n"
                + "            </tr>\n"
                + "            \n"
                + "            <tr class=\"details\" >\n"
                + "                <td colspan=\"2\">\n"
                + "                    Cash\n"
                + "                </td>\n"
                + "                \n"
                + "                <td>\n"
                + "                    \n"
                + "                </td>\n"
                + "            </tr>\n"
                + "            \n"
                + "            <tr class=\"heading\">\n"
                + "                <td>\n"
                + "                    Item\n"
                + "                </td>\n"
                + "                 <td style=\"text-align:left\">Quantity</td>\n"
                + "                \n"
                + "                <td style=\"text-align:right\">\n"
                + "                    Price\n"
                + "                </td>\n"
                + "            </tr>";

        String items = " <tr class=\"item\">\n"
                + "                <td>\n"
                + "                    {ITEM}\n"
                + "                </td>\n"
                + "                <td style=\"text-align:left\">&emsp;&nbsp;{COUNT}</td>\n"
                + "                <td style=\"text-align:right\">\n"
                + "                    ${COST}\n"
                + "                </td>\n"
                + "            </tr>";

        String end = "            <tr class=\"total\">\n"
                + "                \n"
                + "                <td colspan=\"3\" style=\"text-align:right\">\n"
                + "                   Total: <b>{TOTAL}</b>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        
        String[] CLOTHES = {"UNDERGAMRNETS", "JEANS", "SHORTS", "PANTS", "SKIRTS", "TSHIRT", "SHIRT", "BLANKETS", "SUIT", "EHTNIC"};
        String cloth_bill_data="";
        String email="";
        int[] PRICE = {30, 50, 40, 60, 50, 40, 60, 100, 100, 150};
        SQL sql = new SQL();
        String query = String.format("SELECT * FROM orders WHERE id=%s", id);
        System.out.println(query);
        try {
            ResultSet rs = sql.execute(query, true);
            if (rs.next()) {
                String cloth_data = rs.getString("cloth_data");
                 email = rs.getString("customer_email");
                String cost = rs.getString("cost");
                String[] cloths = cloth_data.split(",");
                
                cloth_bill_data += head.replace("{EMAIL}", email).replace("{TOTAL}",cost);
                for (int i=0;i<cloths.length;i++)
                {
                    String[] it = cloths[i].split("=");
                    int index = 0;
                    for (int j=0;j<CLOTHES.length;j++)
                        if (CLOTHES[j].equals(it[0]))
                                index = j;
                    int it_cost = PRICE[index];
                    cloth_bill_data += items.replace("{ITEM}",it[0]).replace("{COUNT}",it[1]).replace("{COST}",Integer.toString(it_cost * Integer.parseInt(it[1])));   
                }
                cloth_bill_data += end.replace("{TOTAL}", cost);
                sql.c.close();
                //message.setText(" Hello " + userName+ "");
                rs.close();
                query = String.format("SELECT name FROM CUSTOMER WHERE email='%s'", email);
                System.out.println(query);
                rs = sql.execute(query, true);
                if (rs.next()) {
                    String name = rs.getString("name");
                    cloth_bill_data = cloth_bill_data.replace("{NAME}",name);
                    sql.c.close();
                //message.setText(" Hello " + userName+ "");
                rs.close();
                }
                
            } else {
                rs.close();
                System.out.println("False");
                //message.setText(" Invalid user.. ");
            }
        } 
        catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
        }
        send_mail("Your INVOICE for ORDER " + Integer.toString(id),cloth_bill_data,email);
    }

    public boolean send_mail(String sub, String msg,String email) {
        final String username = "rosedrycleaners0@gmail.com";
        final String password = "jaypool2003";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(username));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            //Multipart multipart = new MimeMultipart();

            //MimeBodyPart attachmentPart = new MimeBodyPart();
            //MimeBodyPart textPart = new MimeBodyPart();
            //try {

            /*    //File f =new File("E:\\Trojan\\Blog\\LAUNDRY-SYSTEM\\PROJECT COMP-SCI Netbeans\\src\\project\\resources\\bill.html");

                //attachmentPart.attachFile(f);
                // Send the actual HTML message.
                message.setContent(
                "<h1>This is actual message embedded in HTML tags</h1>",
                "text/html");
                //multipart.addBodyPart(textPart);
                //multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            // message.setContent(multipart);
             */
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
        /*
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("febinjose5172@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }*/
    }

}
