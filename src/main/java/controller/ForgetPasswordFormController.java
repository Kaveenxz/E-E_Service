package controller;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.SecureRandom;
public class ForgetPasswordFormController {
    public TextField otptxt;
    @FXML
    private TextField emailField;

    private String generatedOTP;

    @FXML
    void sendOTP(ActionEvent event) {
        String email = emailField.getText();

        if (isValidEmail(email)) {
            generatedOTP = generateOTP();

            sendOTPEmail(email, generatedOTP);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OTP Sent");
            alert.setHeaderText(null);
            alert.setContentText("An OTP has been sent to your email. Check your inbox.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
        }
    }
    private void sendOTPEmail(String toEmail, String otp) {
        final String username = "kaveenhansithx@gmail.com";
        final String password = "ilqo nfes iqzp liqb";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            message.setSubject("Password Reset OTP");
            message.setText("Your OTP for password reset is: " + otp);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void verifyOTP(ActionEvent event) {
        String enteredOTP = otptxt.getText();

        if (enteredOTP != null && enteredOTP.equals(generatedOTP)) {

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid OTP");
            alert.setHeaderText(null);
            alert.setContentText("The entered OTP is incorrect.");
            alert.showAndWait();
        }
    }


    private String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
    }
}