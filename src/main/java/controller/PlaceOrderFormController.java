package controller;

import bo.custom.CustomerBo;
import bo.custom.impl.CustomerBoImpl;
import com.jfoenix.controls.JFXComboBox;
import dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderFormController {

    public TextField costTxt;
    public JFXComboBox combId;
    public TextField orderId;
    @FXML
    private GridPane pane;

    @FXML
    private TextField custnametxt;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private TextField contactTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField decsTxt;


    private List<CustomerDto> customers;
    private CustomerBo customerBo = new CustomerBoImpl();

    @FXML
    private TextField itemNameTxt;
    public void initialize(){
        ObservableList<String> options = FXCollections.observableArrayList("Electrical", "Electronic");

        category.setItems(options);

        try {
            customers = customerBo.allCustomers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadCustomerIds();

        combId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CustomerDto dto:customers) {
                if (dto.getCustId().equals(newValue.toString())){
                    custnametxt.setText(dto.getCustName());
                    emailTxt.setText(dto.getCustEmail());
                    contactTxt.setText(dto.getCustContact());
                }
            }
        });
    }

    private void loadCustomerIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getCustId());
        }

        combId.setItems(list);
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


    }

}