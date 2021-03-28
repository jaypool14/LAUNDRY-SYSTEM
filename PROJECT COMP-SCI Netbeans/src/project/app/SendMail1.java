package project.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

public class SendMail1 {

    public static void main(String[] args) {
        SendMail1 obj = new SendMail1();
        // obj.create_bill(1);
    }

    public void create_bill(int id) {
        String actual = "";
        try {
            Path fileName = Path.of("./src/project/resources/invoice_raw.txt");
            actual = Files.readString(fileName);
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("--HEAD--(.*?)--HEAD--", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(actual);
        matcher.find();
        String head = matcher.group(1);
        pattern = Pattern.compile("--ITEMS--(.*?)--ITEMS--", Pattern.DOTALL);
        matcher = pattern.matcher(actual);
        matcher.find();
        String items = matcher.group(1);
        pattern = Pattern.compile("--END--(.*?)--END--", Pattern.DOTALL);
        matcher = pattern.matcher(actual);
        matcher.find();
        String end = matcher.group(1);

        String[] CLOTHES = {"UNDERGAMRNETS", "JEANS", "SHORTS", "PANTS", "SKIRTS", "TSHIRT", "SHIRT", "BLANKETS", "SUIT", "EHTNIC"};
        String cloth_bill_data = "";
        String email = "";
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

                cloth_bill_data += head.replace("{EMAIL}", email).replace("{TOTAL}", cost);
                for (int i = 0; i < cloths.length; i++) {
                    String[] it = cloths[i].split("=");
                    int index = 0;
                    for (int j = 0; j < CLOTHES.length; j++) {
                        if (CLOTHES[j].equals(it[0])) {
                            index = j;
                        }
                    }
                    int it_cost = PRICE[index];
                    cloth_bill_data += items.replace("{ITEM}", it[0]).replace("{COUNT}", it[1]).replace("{COST}", Integer.toString(it_cost * Integer.parseInt(it[1])));
                }
                cloth_bill_data += end.replace("{TOTAL}", cost);
                sql.c.close();
                rs.close();
                query = String.format("SELECT name FROM CUSTOMER WHERE email='%s'", email);
                System.out.println(query);
                rs = sql.execute(query, true);
                if (rs.next()) {
                    String name = rs.getString("name");
                    cloth_bill_data = cloth_bill_data.replace("{NAME}", name);
                    sql.c.close();
                    rs.close();
                }
            } else {
                rs.close();
                System.out.println("False");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        send_mail("Your INVOICE for ORDER " + Integer.toString(id), cloth_bill_data, email);
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            String fileName = s + "\\src\\project\\resources\\bills\\invoice_" + Integer.toString(id) + ".html";
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            file.seek(0);
            file.writeChars(cloth_bill_data);
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SendMail1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SendMail1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean send_mail(String sub, String msg, String email) {
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
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }

}
