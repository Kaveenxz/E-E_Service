package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.CustomerDto;
import dto.ItemDto;
import dto.tm.CustomerTm;
import dto.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemManagementFormController {

    public TextField qtyTxt;
    public TreeTableColumn colqty;
    public JFXComboBox changeSttus;
    public TreeTableColumn colStatus;
    public JFXComboBox statusCombBox;
    public JFXComboBox itemTypeComBox;
    public TextField txtId;
    @FXML
    private GridPane pane;

    @FXML
    private TextField nametxt;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXTreeTableView<ItemTm> tblItem;

    @FXML
    private TreeTableColumn<?, ?> colId;

    @FXML
    private TreeTableColumn<?, ?> colName;

    @FXML
    private TreeTableColumn<?, ?> colCategory;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    public void initialize(){
        ObservableList<String> userType= FXCollections.observableArrayList("Electrical", "Electronic");
        itemTypeComBox.setItems(userType);

        ObservableList<String> status= FXCollections.observableArrayList("Available", "Sold");
        statusCombBox.setItems(status);

        colId.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        colName.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        colqty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colStatus.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        allItems();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(TreeItem<ItemTm> newValue) {
        if (newValue != null) {
            ItemTm selectedItem = newValue.getValue();
            qtyTxt.setText(String.valueOf(selectedItem.getQty()));
            nametxt.setText(selectedItem.getName());
        }
    }

    private void clearFields() {
        tblItem.refresh();
        nametxt.clear();
        qtyTxt.clear();
    }
    private void allItems() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList = itemBo.allItems();
            for (ItemDto dto: dtoList) {
                Button btn = new Button("Delete");
                ItemTm tm = new ItemTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getCategory(),
                        dto.getQty(),
                        dto.getStatus(),
                        btn
                );
                btn.setOnAction(actionEvent -> {
                    deleteItem(tm.getId());
                });
                tmList.add(tm);
            }
            RecursiveTreeItem<ItemTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(String id) {
        try {
            boolean isDeleted = itemBo.deleteItem(id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                allItems();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        ItemDto dto = new ItemDto(
                txtId.getText(),
                nametxt.getText(),
                itemTypeComBox.getValue().toString(),
                Integer.parseInt(qtyTxt.getText()),
                statusCombBox.getValue().toString()
        );

        try {
            boolean isSaved = itemBo.saveItem(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                allItems();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        ItemDto dto = new ItemDto(
                txtId.getText(),
                nametxt.getText(),
                itemTypeComBox.getValue().toString(),
                Integer.parseInt(qtyTxt.getText()),
                statusCombBox.getValue().toString()
        );

        try {
            boolean isSaved = itemBo.updateItem(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                allItems();
                clearFields();
            }
        }catch (NullPointerException e){
            new Alert(Alert.AlertType.WARNING,"Fill All forms!").show();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
