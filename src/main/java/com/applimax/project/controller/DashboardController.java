package com.applimax.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public AnchorPane ancMainContainerPlus;
    @FXML
    private AnchorPane ancMainContainer;

    @FXML
    private Button btnCudtomer;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnExpenses;

    @FXML
    private Button btnInventory;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnSales;

    @FXML
    private Button btnSupplier;

    @FXML
    private Button btnUser;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        navigateTo("/view/CustomerPage.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        navigateTo("/view/OpenPage.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        navigateTo("/view/EmployeePage.fxml");

    }

    @FXML
    void btnExpensesOnAction(ActionEvent event) {
        navigateTo("/view/ExpensesPage.fxml");

    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        navigateTo("/view/InventoryPage.fxml");


    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        navigateTo("/view/ItemPage.fxml");

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        navigateTo("/view/OrderPage.fxml");

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        navigateTo("/view/PaymentPage.fxml");
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        navigateTo("/view/ReportPage.fxml");

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) {
        navigateTo("/view/SalesPage.fxml");

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        navigateTo("/view/SupplierPage.fxml");

    }

    @FXML
    void btnUserOnAction(ActionEvent event) {
        navigateTo("/view/UserPage.fxml");

    }

    @FXML
    public void btnLogoutOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure to Logout ?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
                    navigateToLogout("/view/LoginPage.fxml");
                }

    }


    private void navigateTo(String path) {
        try {
            ancMainContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancMainContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancMainContainer.heightProperty());

            ancMainContainer.getChildren().add(anchorPane);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    private void navigateToLogout(String path) {
        try {
            ancMainContainerPlus.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancMainContainerPlus.widthProperty());
            anchorPane.prefHeightProperty().bind(ancMainContainerPlus.heightProperty());

            ancMainContainerPlus.getChildren().add(anchorPane);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigateTo("/view/OpenPage.fxml");
    }


}
