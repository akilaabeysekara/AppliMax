package com.applimax.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginLayoutController {

    @FXML
    private AnchorPane ancLoginLayout;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void navigateTo(String path) {
        try {
            ancLoginLayout.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/" + path));
            anchorPane.prefWidthProperty().bind(ancLoginLayout.widthProperty());
            anchorPane.prefHeightProperty().bind(ancLoginLayout.heightProperty());
            ancLoginLayout.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    public void onActionRegister(ActionEvent actionEvent) {
        navigateTo("view/RegisterLayout.fxml");
    }

    public void onLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("admin")) {
            new Alert(Alert.AlertType.INFORMATION, "Login Successful!", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid credentials!", ButtonType.OK).show();
        }
    }

    public void forgotPasswordOnAction(MouseEvent mouseEvent) {

    }
}
