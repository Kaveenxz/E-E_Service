package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.UserBo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.util.BoType;
import dto.ItemDto;
import dto.UserDto;
import dto.tm.ItemTm;
import dto.tm.UserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserManagementFormController {

    public TextField idTxt;
    public JFXPasswordField passwordtxt;

    @FXML
    private JFXTreeTableView<UserTm> tblusers;
    @FXML
    private GridPane pane;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField password;

    @FXML
    private JFXComboBox<String> loginMetod;

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

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);


    public void initialize(){
        ObservableList<String> userType= FXCollections.observableArrayList("Admin", "User");

        loginMetod.setItems(userType);

        colId.setCellValueFactory(new TreeItemPropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new TreeItemPropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        colType.setCellValueFactory(new TreeItemPropertyValueFactory<>("userType"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        allItems();

        tblusers.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(TreeItem<UserTm> newValue) {
        if (newValue != null) {
            idTxt.setEditable(false);
            UserTm selectedItem = newValue.getValue();
            idTxt.setText(String.valueOf(selectedItem.getUserId()));
            nametxt.setText(selectedItem.getUserName());
            emailTxt.setText(selectedItem.getEmail());
            loginMetod.setValue(selectedItem.getUserType());
            passwordtxt.setEditable(false);
            passwordtxt.setText(selectedItem.getPassWord());

        }
    }

    private void clearFields() {
        tblusers.refresh();
        idTxt.clear();
        nametxt.clear();
        passwordtxt.clear();
        emailTxt.clear();
        idTxt.setEditable(true);
        passwordtxt.setEditable(true);
    }

    private void allItems() {
        ObservableList<UserTm> tmList = FXCollections.observableArrayList();

        try {
            List<UserDto> dtoList = userBo.allUsers();
            for (UserDto dto: dtoList) {
                Button btn = new Button("Delete");
                UserTm tm = new UserTm(
                        dto.getUserId(),
                        dto.getUserName(),
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getUserType(),
                        btn
                );
                btn.setOnAction(actionEvent -> {
                    deleteuser(tm.getUserId());
                });
                tmList.add(tm);
            }
            RecursiveTreeItem<UserTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            tblusers.setRoot(treeItem);
            tblusers.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteuser(String userId) {
        try {
            boolean isDeleted = userBo.deleteUser(userId);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"User Deleted!").show();
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
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        UserDto dto = new UserDto(
                idTxt.getText(),
                nametxt.getText(),
                emailTxt.getText(),
                passwordtxt.getText(),
                loginMetod.getValue().toString()
        );

        try {
            boolean isSaved = userBo.saveUser(dto);
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
        UserDto dto = new UserDto(
                idTxt.getText(),
                nametxt.getText(),
                emailTxt.getText(),
                passwordtxt.getText(),
                loginMetod.getValue().toString()
        );

        try {
            boolean isSaved = userBo.updateUser(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"User Updated!").show();
                allItems();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
