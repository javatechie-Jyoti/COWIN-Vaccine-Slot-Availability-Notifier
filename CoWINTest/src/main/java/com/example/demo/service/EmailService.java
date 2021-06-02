package com.example.demo.service;

import com.sun.mail.smtp.SMTPSaslAuthenticator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    String recipient = "jyoti.choudhary1990@gmail.com";
    String sender = "CoWINAppNotifierJyoti@gmail.com";
    String host = "192.40.165.69";

    public Properties getProperties(){
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);
        return properties;
    }

    public void sendEmail(String text){
        Session session = Session.getDefaultInstance(getProperties());

        try{
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Test Email for Notifying about Available Vaccine slots");
            message.setText(text);

            Transport.send(message);
            System.out.println("Mail Successfully Sent");
        }catch(MessagingException ex){
            System.out.println("Error while sending email:");
            ex.printStackTrace();
        }
    }

    public Properties getGmailProperties(){
        Properties properties = System.getProperties();

        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        /*props.put("mail.smtp.starttls.enable", GSTARTTLS_ENABLE);
        props.put("mail.smtp.starttls.required", GSTARTTLS_REQUIRED);
        props.put("mail.smtp.startssl.enable", GSTARTSSL_ENABLE);
        props.put("mail.smtp.startssl.required", GSTARTSSL_REQUIRED);*/
        return properties;
    }

    public void sendEmailUsingGmailServer(String text){
        Session session = Session.getDefaultInstance(getGmailProperties());
        String fromUser = "javaTechie001@gmail.com";
        String fromUserEmailPassword = "Cowin2021";

        String emailHost = "smtp.gmail.com";
        try{
        Transport transport = session.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sender));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject("Test Email for Notifying about Available Vaccine slots in South Delhi");
        message.setText(text);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
        }catch(MessagingException ex){
            System.out.println("Error while sending email:");
            ex.printStackTrace();
        }
    }
}
