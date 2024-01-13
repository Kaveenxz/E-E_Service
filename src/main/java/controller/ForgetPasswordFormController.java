package controller;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import bo.BoFactory;
import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import com.sun.javafx.stage.EmbeddedWindow;
import dao.util.BoType;
import dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.SecureRandom;
public class ForgetPasswordFormController {
    public TextField otptxt;
    @FXML
    private TextField emailField;

    private String generatedOTP;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @FXML
    void sendOTP(ActionEvent event) throws SQLException, ClassNotFoundException {
        String email = emailField.getText();

        if (isValidEmail(email)) {

            // Check if the email exists in the database
            UserDto userDto = userBo.getUserByEmail(email);

            if (userDto != null) {
                // Email exists, generate and send OTP
                generatedOTP = generateOTP();
                sendOTPEmail(email, generatedOTP);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("OTP Sent");
                alert.setHeaderText(null);
                alert.setContentText("An OTP has been sent to your email. Check your inbox.");
                alert.showAndWait();
            } else {
                // Show an error message for a non-existing email
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email Not Found");
                alert.setHeaderText(null);
                alert.setContentText("The entered email does not exist in our records.");
                alert.showAndWait();
            }
        } else {
            // Show an error message for an invalid email
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
            // OTP verified. Allow password reset.
            showPasswordResetWindow();
        } else {
            // Show an error message for incorrect OTP
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid OTP");
            alert.setHeaderText(null);
            alert.setContentText("The entered OTP is incorrect.");
            alert.showAndWait();
        }
    }

    // Add a new method to show the password reset window
    private void showPasswordResetWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ResetPasswordForm.fxml"));
            Parent root = loader.load();

            // Access the controller and set the user email
            ResetPasswordFormController resetPasswordController = loader.getController();
            resetPasswordController.setUserEmail(emailField.getText());

            Stage stage = (Stage) otptxt.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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