package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SalesReportsFormController {
    @FXML
    private GridPane pane;

    @FXML
    private BarChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    void annualReportBtnOnAction(ActionEvent event) {

    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReportsForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void dailyReportBtnOnAction(ActionEvent event) {

    }

    @FXML
    void monthlyReportBtnOnAction(ActionEvent event) {

    }

}
