package project.app;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendInvoice {

    public static void main(String[] args) {
        SendMail obj = new SendMail();
        obj.send_mail("This is the Subject Line!", "<h1>This is actual message embedded in HTML tags</h1>", "");
    }

    public boolean send_mail(String sub, String msg) {
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("febinjose5172@gmail.com"));

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
