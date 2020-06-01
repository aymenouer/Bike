/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Properties;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Asus
 */
public class ServiceMail {

    public static void send(String to, String subject, String text) {

        final String username = "rzouga20003@gmail.com";
        final String password = "rzouganeme20003720.fr";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
                                        System.out.println("hahahahahahah));)");

            message.setSubject(subject);
            message.setText("" + " " + text);
            Transport.send(message);
            System.out.println("Mail sent ");
        } catch (MessagingException ex) {
            // pretend no connexion
            System.out.println("errrrreuuuuuuur");
            // prompt error message
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setContentText("Mail couldn't be sent , please check your internet connexion");
            //alert.showAndWait();
            //Navigator.LoadScene(Navigator.authentification);
        }
    }

}
