package controller;

import bo.BoFactory;
import bo.custom.UserBo;
import com.jfoenix.controls.JFXComboBox;
import dao.util.BoType;
import dto.UserDto;
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
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {

    public JFXComboBox loginMetod;
    public PasswordField paswordTxt;

    @FXML
    private TextField emailTxt;


    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    ObservableList<UserDto> userList = FXCollections.observableArrayList();

    public void initialize(){
        ObservableList<String> loginOptions = FXCollections.observableArrayList("Admin", "User");

        loginMetod.setItems(loginOptions);
        try {
            getUsers();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userList);
    }
    @FXML
    public void siginBtnOnAction(ActionEvent event) {
        String email = emailTxt.getText();
        String password = paswordTxt.getText();

        try {
            UserDto user = getUserByEmail(email);

            if(loginMetod.getValue() == "Admin" && email.equals("eneservice@sample.com") && password.equals("12345pqrs")){
                Stage stage = (Stage) loginMetod.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"))));
                stage.show();
            } else {


                if (user != null && verifyPassword(password, user.getPassword())) {
                    if (user.getUserType().equals("Admin")) {
                        Stage stage = (Stage) loginMetod.getScene().getWindow();
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"))));
                        stage.show();
                    } else if (user.getUserType().equals("User")) {
                        Stage stage = (Stage) loginMetod.getScene().getWindow();
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboardForm.fxml"))));
                        stage.show();
                    }
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Wrong Information!").show();
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    private UserDto getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        List<UserDto> users = userBo.allUsers();
        for (UserDto dto : users) {
            if (dto.getEmail().equals(email)) {
                return dto;
            }
        }
        return null;
    }

    private boolean verifyPassword(String enteredPassword, String hashedPassword) {
        String hashedEnteredPassword = userBo.hashPassword(enteredPassword);
        return hashedEnteredPassword.equals(hashedPassword);
    }


    public void getUsers() throws SQLException, ClassNotFoundException {
        List<UserDto> users = userBo.allUsers();
        for (UserDto dto: users) {
            UserDto user = new UserDto(
                    dto.getUserId(),
                    dto.getUserName(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getUserType()
            );
            userList.add(user);
        }

    }

    public void sendOTPOBtnnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) loginMetod.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ForgetPasswordForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.centerOnScreen();
        stage.show();
    }

}