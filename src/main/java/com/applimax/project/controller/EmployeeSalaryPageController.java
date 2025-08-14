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
import javafx.scene.layout.AnchorPane;

public class EmployeeSalaryPageController {

    @FXML
    private AnchorPane ancSalaryTableView;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbAttendanceId;

    @FXML
    private ComboBox<?> cmbEmployeeId;

    @FXML
    private ComboBox<?> cmbSelectMonth;

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
    private TextField txtBasicSalary;

    @FXML
    private TextField txtTotalSalary;

    @FXML
    private TextField txtallowances;

    @FXML
    void btnEmployeeDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbAttendanceIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSelectMonthOnAction(ActionEvent event) {

    }

    @FXML
    void generateAllCustomerReport(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void setTotal(KeyEvent event) {

    }

}
