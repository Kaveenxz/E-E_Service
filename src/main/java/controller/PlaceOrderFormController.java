package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PlaceOrderFormController {

    @FXML
    private GridPane pane;

    @FXML
    private TextField custnametxt;

    @FXML
    private JFXComboBox<?> category;

    @FXML
    private TextField contactTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField decsTxt;

    @FXML
    private TextField itemNameTxt;

    @FXML
    void backButtonOnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }

}