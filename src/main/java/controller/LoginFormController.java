package controller;

import bo.EmailValidation;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFormController {

    public JFXComboBox loginMetod;
    public PasswordField paswordTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField passwordTxt;


    public void initialize(){
        ObservableList<String> loginOptions = FXCollections.observableArrayList("Admin", "User");

        loginMetod.setItems(loginOptions);
    }
    @FXML
    void siginBtnOnAction(ActionEvent event) {
        String email = emailTxt.getText();
        String password = paswordTxt.getText();

        if(loginMetod.getValue() == "Admin" && EmailValidation.isValidEmail(email)){
            Stage stage = (Stage) loginMetod.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"))));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(loginMetod.getValue() == "User"){
            Stage stage = (Stage) loginMetod.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboardForm.fxml"))));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.INFORMATION,"none !").show();
        }


    }

    public void sendOTPOBtnnAction(ActionEvent actionEvent) {

    }

}
