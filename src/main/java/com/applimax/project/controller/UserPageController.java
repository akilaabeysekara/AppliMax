package com.applimax.project.controller;

import com.applimax.project.dto.AppUserDTO;
import com.applimax.project.model.AppUserModel;
import com.applimax.project.model.EmployeeModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UserPageController implements Initializable {

    @FXML private TableColumn<AppUserDTO, String> colPassword;
    @FXML private TableColumn<AppUserDTO, String> colEmployeeId;
    @FXML private Button btnDelete;
    @FXML private Button btnReset;
    @FXML private Button btnSave;
    @FXML private Button btnUpdate;
    @FXML private ComboBox<String> cmbEmployeeId;
    @FXML private TableColumn<AppUserDTO, String> colEmail;
    @FXML private TableColumn<AppUserDTO, String> colId;
    @FXML private TableColumn<AppUserDTO, String> colName;
    @FXML private TableColumn<AppUserDTO, String> colRole;  // <-- NEW
    @FXML private Label lblEmployeeName;
    @FXML private Label lblId;
    @FXML private TableView<AppUserDTO> tblUser;
    @FXML private TextField txtUserName;
    @FXML private TextField txtUserPassword;
    @FXML private TextField txtUserEmail;
    @FXML private ComboBox<String> cmbUser; // roles

    private final AppUserModel appUserModel = new AppUserModel();
    private final EmployeeModel employeeModel = new EmployeeModel();

    private static final Pattern EMAIL_RX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern USERNAME_RX =
            Pattern.compile("^[A-Za-z0-9._-]{3,50}$"); // simple rule

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));  // <-- bind role

        try {
            loadEmployeeIds();
            setEmployeeName();
            resetPage();
            // roles for cmbUser
            cmbUser.setItems(FXCollections.observableArrayList("ADMIN", "SUPERVISOR", "STAFF"));
            cmbUser.setPromptText("Select role");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to load user data.");
        }
    }

    private void loadEmployeeIds() throws SQLException, ClassNotFoundException {
        List<String> employeeIds = employeeModel.getAllEmployeeIds();
        cmbEmployeeId.setItems(FXCollections.observableArrayList(employeeIds));
        cmbEmployeeId.setOnAction(event -> {
            try {
                setEmployeeName();
            } catch (Exception ex) {
                ex.printStackTrace();
                showError("Failed to load employee name.");
            }
        });
    }

    private void setEmployeeName() throws SQLException, ClassNotFoundException {
        String employeeId = cmbEmployeeId.getValue();
        lblEmployeeName.setText(employeeId == null ? "" : employeeModel.findNameById(employeeId));
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
        loadNextId();
        loadTableData();
        clearFields();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    private void clearFields() {
        txtUserName.clear();
        txtUserPassword.clear();
        txtUserEmail.clear();
        cmbEmployeeId.setValue(null);
        cmbUser.setValue(null);
        lblEmployeeName.setText("");
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        lblId.setText(appUserModel.getNextId());
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        tblUser.setItems(FXCollections.observableArrayList(appUserModel.getAllUsers()));
        tblUser.getSelectionModel().clearSelection();
    }

    // ----------------- Validations -----------------
    private boolean validateForm(boolean forUpdate) {
        String userId = lblId.getText();
        String userName = txtUserName.getText().trim();
        String password = txtUserPassword.getText();
        String email = txtUserEmail.getText().trim();
        String employeeId = cmbEmployeeId.getValue();
        String role = cmbUser.getValue();

        if (userId.isEmpty()) {
            showError("User ID is missing.");
            return false;
        }
        if (employeeId == null || employeeId.isEmpty()) {
            showError("Please select an Employee ID.");
            return false;
        }
        if (userName.isEmpty() || !USERNAME_RX.matcher(userName).matches()) {
            showError("Enter a valid username (3–50, letters/numbers . _ -).");
            return false;
        }
        if (password == null || password.length() < 6) {
            showError("Password must be at least 6 characters.");
            return false;
        }
        if (email.isEmpty() || !EMAIL_RX.matcher(email).matches()) {
            showError("Enter a valid email address.");
            return false;
        }
        if (role == null || role.isEmpty()) {
            showError("Please select a user role.");
            return false;
        }
        return true;
    }
    // ------------------------------------------------

    @FXML
    void btnUserDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete this user?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (appUserModel.deleteUser(lblId.getText())) {
                    resetPage();
                    showInfo("User deleted successfully.");
                } else showError("Delete failed.");
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error occurred while deleting user.");
            }
        }
    }

    @FXML
    void btnUserResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        resetPage();
    }

    @FXML
    void btnUserSaveOnAction(ActionEvent event) {
        if (!validateForm(false)) return;

        AppUserDTO dto = new AppUserDTO(
                lblId.getText(),
                cmbEmployeeId.getValue(),
                txtUserName.getText().trim(),
                txtUserPassword.getText(),
                txtUserEmail.getText().trim(),
                cmbUser.getValue()
        );

        try {
            boolean ok = appUserModel.saveUser(dto);
            if (ok) {
                resetPage();
                showInfo("User saved successfully!");
            } else showError("Failed to save user.");
        } catch (Exception e) {
            e.printStackTrace();
            // likely a unique constraint violation
            showError("Error while saving. Check if username/email/employee already exists.");
        }
    }

    @FXML
    void btnUserUpdateOnAction(ActionEvent event) {
        if (!validateForm(true)) return;

        AppUserDTO dto = new AppUserDTO(
                lblId.getText(),
                cmbEmployeeId.getValue(),
                txtUserName.getText().trim(),
                txtUserPassword.getText(),
                txtUserEmail.getText().trim(),
                cmbUser.getValue()
        );

        try {
            boolean ok = appUserModel.updateUser(dto);
            if (ok) {
                resetPage();
                showInfo("User updated successfully!");
            } else showError("Failed to update user.");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error while updating. Check unique fields.");
        }
    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {
        // handled by setOnAction in loadEmployeeIds()
    }

    @FXML
    void onClickTable(MouseEvent event) {
        AppUserDTO selected = tblUser.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblId.setText(selected.getUserId());
            txtUserName.setText(selected.getUserName());
            txtUserPassword.setText(selected.getPassword());
            cmbEmployeeId.setValue(selected.getEmployeeId());
            txtUserEmail.setText(selected.getEmail());
            cmbUser.setValue(selected.getRole());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @FXML
    void setTotal(KeyEvent event) {
        // not used
    }

    private void showInfo(String message) { new Alert(Alert.AlertType.INFORMATION, message).show(); }
    private void showError(String message) { new Alert(Alert.AlertType.ERROR, message).show(); }
}
