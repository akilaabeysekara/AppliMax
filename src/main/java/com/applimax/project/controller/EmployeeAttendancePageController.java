package com.applimax.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EmployeeAttendancePageController {

    @FXML
    private Button btnDeleteAttendance;

    @FXML
    private Button btnMarkAttendance;

    @FXML
    private Button btnReportAttendance;

    @FXML
    private Button btnResetAttendance;

    @FXML
    private Button btnUpdateAttendance;

    @FXML
    private ComboBox<?> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colInTime;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colOutTime;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colWorkHours;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblAttendance;

    @FXML
    private TextField txtInTime;

    @FXML
    private TextField txtOutTime;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtWorkHours;

    @FXML
    void btnDeleteAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnMarkAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void setTotal(KeyEvent event) {

    }

    public void cmbStatusOnAction(ActionEvent actionEvent) {
    }
}
