package com.citytech.global.common.sendmail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailService {
    public static void sendEmail(String toEmail,
                                 String subject,
                                 String body) {
        final String fromEmail = "satishsubedi18@gmail.com";
        final String password = "xebk vmpj dksc vpxi";

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtils.sendEmail(session, toEmail,subject, body);
    }
}
