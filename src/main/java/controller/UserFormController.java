package controller;

import bo.custom.UserBo;
import bo.custom.impl.UserBoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserFormController {
    public GridPane pane;
    public TextField txtName;
    public TextField txtPassword;
    public TextField txtemail;

    public void initialize() {

    }


    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveChangesBtnOnAction(ActionEvent actionEvent) {
    }
}
