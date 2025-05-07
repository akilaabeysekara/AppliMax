package com.applimax.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterLayoutController implements Initializable {
    @FXML
    private ChoiceBox<String> textRole;

    @FXML
    private AnchorPane ancRegister;
    public void navigateTo(String path) {
        try {
            ancRegister.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(ancRegister.widthProperty());
            anchorPane.prefHeightProperty().bind(ancRegister.heightProperty());
            ancRegister.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> roles = FXCollections.observableArrayList("user");
        textRole.setItems(roles);
        textRole.setValue("");
    }

    public void clickBack(MouseEvent mouseEvent) {
        navigateTo("/");
    }
}
