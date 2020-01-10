package javaMailAPI;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static drawingUI.detailsPage.personalTab.*;

//javaMailAPI.jakartaMailAPI.sendMail("estun9@gmail.com");
//copy this method to send the email

public class jakartaMailAPI {

    public static void sendMail(String recepient) throws Exception {
        System.out.println("TRYING");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "estun9@gmail.com";
        String password = "!q&PY3jk";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessagae(session, myAccountEmail, recepient);

        Transport.send(message);
        System.out.println("SENT");
    }

    private static Message prepareMessagae(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            String m1 = "Warning!";
            String m2 = "Patient " + fnametext.getText() + " " + lnametext.getText() +
                    " has a low blood sugar level. Please contact emergy contact ( " + phonetext.getText() + " )and take necessary precautions.";
            String m = m1 + "\n" + m2;
            message.setText(m);

            System.out.println(m);

            return message;
        } catch (Exception e) {
            return null;
        }
    }

    public static void printmessage(){
        String m1 = "Warning!";
        String m2 = "Patient " + fnametext.getText() + " " + lnametext.getText() +
                " has a low blood sugar level. Please contact and take necessary precautions.";
        String m = m1 + "\n" + m2;

        System.out.println(m);
    }

}