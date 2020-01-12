package javaMailAPI;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static SQLDatabase.pullAzure.*;
import static drawingUI.detailsPage.personalTab.*;
import static drawingUI.emailPage.emailPanel.etext;

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
        String password = "fgj5z6YfBb4B";

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
        //set patient id
        String id = pullUserID(etext.getText());
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

            String subject = "Diabetic Patient Warning";
            message.setSubject(subject);
            System.out.println(message.getHeader("Subject")[0]);

            String name = pullPatientFirstName(id) + " " + pullPatientLastName(id);
            String phone = pullPatientPhone(id);

            String m1 = "Warning!";
            String m2 = "Patient " + name +
                    " has a low blood sugar level. Please contact patient's emergency number ("
                    + phone + ") and take the necessary precautions.";
            String m = m1 + "\n" + m2;
            message.setText(m);
            System.out.println(m);

            return message;
        } catch (Exception e) {
            return null;
        }
    }
}