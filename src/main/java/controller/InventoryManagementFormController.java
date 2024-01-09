package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.GridPane;

public class InventoryManagementFormController {@FXML
private GridPane pane;

    @FXML
    private JFXComboBox<?> category;

    @FXML
    private JFXTreeTableView<?> tblItem;

    @FXML
    private TreeTableColumn<?, ?> colName;

    @FXML
    private TreeTableColumn<?, ?> colCategory;

    @FXML
    private TreeTableColumn<?, ?> colQty;

    @FXML
    private TreeTableColumn<?, ?> colStatus;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    void backButtonOnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }

}
