package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.GridPane;

public class UserManagementFormController {

    @FXML
    private GridPane pane;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField password;

    @FXML
    private JFXComboBox<?> loginMetod;

    @FXML
    private JFXTreeTableView<?> tbluser;

    @FXML
    private TreeTableColumn<?, ?> colId;

    @FXML
    private TreeTableColumn<?, ?> colName;

    @FXML
    private TreeTableColumn<?, ?> colEmail;

    @FXML
    private TreeTableColumn<?, ?> colType;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    void backButtonOnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

}
