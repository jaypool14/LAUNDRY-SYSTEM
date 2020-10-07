/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.app;
    import java.util.Properties;    
    import javax.mail.*;    
    import javax.mail.internet.*;    
    class Mailer{  
        public static void send(String from,String password,String to,String sub,String msg){  
              //Get properties object    
              Properties props = new Properties();    
              props.put("mail.smtp.host", "smtp.gmail.com");    
              props.put("mail.smtp.socketFactory.port", "587");    
              //props.put("mail.smtp.socketFactory.class",    
              //          "javax.net.ssl.SSLSocketFactory");    
              props.put("mail.smtp.starttls.enable", "true");
              props.put("mail.smtp.auth", "true");    
              props.put("mail.smtp.port", "587");    
              //get Session   
              Session session = Session.getDefaultInstance(props,    
               new javax.mail.Authenticator() {    
               protected PasswordAuthentication getPasswordAuthentication() {    
               return new PasswordAuthentication(from,password);  
               }    
              });    
              //compose message    
              try {    
               MimeMessage message = new MimeMessage(session);
               message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
               message.setSubject(sub);
               message.setText(msg); 
               //send message  
               Transport.send(message);
               System.out.println("message sent successfully");
              } catch (MessagingException e) {throw new RuntimeException(e);}
                 
        }  
    }  
    public class SendEmail{    
     public static void main(String[] args) {    
         //from,password,to,subject,message  
         Mailer.send("jaypoollaundy@gmail.com","jaypool1234","febinjose5172@gmail.com","hello javatpoint","How r u?");  
         //change from, password and to  
     }    
    }    
