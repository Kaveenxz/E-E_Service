package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetaiDto;
import dto.OrderDto;
import dto.tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {

    public TextField costTxt;
    public JFXComboBox combId;
    public TextField orderId;
    public Label ordersId;
    public JFXComboBox comdItemCode;
    public Label orderIdlbl;
    public JFXTextField orderLbl;
    public Label fx;
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
    private List<ItemDto> items;
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);
    private OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);

    private ObservableList<OrderTm> tmList = FXCollections.observableArrayList();


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

        try {
            items = itemBo.allItems();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadItemIds();


        combId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CustomerDto dto:customers) {
                if (dto.getCustId().equals(newValue.toString())){
                    custnametxt.setText(dto.getCustName());
                    emailTxt.setText(dto.getCustEmail());
                    contactTxt.setText(dto.getCustContact());
                }
            }
        });
        comdItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (ItemDto dto:items) {
                if (dto.getId().equals(newValue.toString())){
                    itemNameTxt.setText(dto.getName());
                    category.setValue(dto.getCategory());
                }
            }
        });
        setOrderId();

    }

    private void setOrderId() {
        try {

                fx.setText(orderBo.generateId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getCustId());
        }

        combId.setItems(list);
    }
    private void loadItemIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (ItemDto dto:items) {
            list.add(dto.getId());
        }

        comdItemCode.setItems(list);
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
        if (comdItemCode.getValue() == null || costTxt.getText().isEmpty() || combId.getValue() == null || fx.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all required fields!").show();
            return;
        }

        JFXButton btn = new JFXButton("Delete");

        OrderTm tm = new OrderTm(
                comdItemCode.getValue().toString(),
                custnametxt.getText(),
                "Process",
                Double.parseDouble(costTxt.getText()),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                btn
        );
        tmList.add(tm);

        List<OrderDetaiDto> list = new ArrayList<>();
        for (OrderTm tmlist : tmList) {
            list.add(new OrderDetaiDto(
                    fx.getText(),
                    tmlist.getItemId(),
                    tmlist.getTotFee(),
                    0.00
            ));
        }

        OrderDto dto = new OrderDto(
                fx.getText(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                combId.getValue().toString(),
                list
        );

        try {
            boolean isSaved = orderBo.saveOrder(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
                setOrderId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}