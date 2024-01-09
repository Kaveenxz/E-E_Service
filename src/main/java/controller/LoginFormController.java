package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public JFXComboBox loginMetod;

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
        String password = passwordTxt.getText();

        if(loginMetod.getValue() == "Admin"){
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
