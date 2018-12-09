package lk.ijse.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.payroll.common.validation.Validator;
import lk.ijse.payroll.dao.DAOFactory;
import lk.ijse.payroll.dao.custom.impl.DesignationDAOImpl;
import lk.ijse.payroll.dao.custom.impl.DesignationDetailsDAOImpl;
import lk.ijse.payroll.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.payroll.entity.Designation;
import lk.ijse.payroll.entity.DesignationDetails;
import lk.ijse.payroll.entity.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private AnchorPane empPane;

    @FXML
    private JFXTextField eNameText;

    @FXML
    private JFXTextField eNICText;

    @FXML
    private JFXTextField eTelNoText;

    @FXML
    private JFXComboBox<String> designationCombo;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTextField eIdText;

    @FXML
    private JFXTextField designationIdText;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton updateButton;

    private DesignationDAOImpl designationDAO;
    private EmployeeDAOImpl employeeDAO;
    private DesignationDetailsDAOImpl designationDetailsDAO;

    public EmployeeController() {
        designationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DESIGNATION);
        employeeDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
        designationDetailsDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DESIGNATION_DETAIL);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTimeDate();
        loadDesignations();
        loadEmployeeId();
        loadEmployees();
        loadEmployeeTableColumns();
        eNameText.requestFocus();
        transition();
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), empPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    private void loadEmployeeTableColumns() {
        employeeTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("empId"));
        employeeTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("empName"));
        employeeTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("empNIC"));
        employeeTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        employeeTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("designation"));

    }

    private void loadEmployees() {
        try {
            ArrayList<Employee> employeeDAOAll = employeeDAO.getAll();
            employeeTable.setItems(FXCollections.observableArrayList(employeeDAOAll));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeId() {
        try {
            ArrayList<Employee> employees = employeeDAO.getAll();
            ArrayList<Integer> employeeIds = new ArrayList<>();
            for (Employee employee : employees) {
                employeeIds.add(employee.getEmpId());
            }
            if (employeeIds.isEmpty()) {
                eIdText.setText(1 + "");
            } else {
                for (Integer employeeId : employeeIds) {
                    eIdText.setText((employeeId + 1) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadDesignations() {
        try {
            ArrayList<Designation> designationDAOAll = designationDAO.getAll();
            ObservableList<String> designationDescriptions = FXCollections.observableArrayList();
            for (Designation s : designationDAOAll) {
                designationDescriptions.add(s.getDesDescription());
            }
            designationCombo.setItems(designationDescriptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void designationAction(ActionEvent event) {
        try {
            Designation designation = designationDAO.searchByDescription(designationCombo.getValue());
            designationIdText.setText(designation.getDesId() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addButtonAction(ActionEvent event) {
        if(validate()) {
            try {
                LocalDate ld = datePicker.getValue();
                Date date = localDateToDate(ld);
                boolean save = employeeDAO.save(new Employee(Integer.parseInt(eIdText.getText()), eNameText.getText(), eNICText.getText(), eTelNoText.getText(), new DesignationDetails(0, Integer.parseInt(eIdText.getText()), Integer.parseInt(designationIdText.getText()), date)));
                System.out.println(save);
                if (save) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Employee Added!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    loadEmployees();
                    cancelButtonAction(event);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validate() {
        if(Validator.nameValidation(eNameText.getText())){
            if(!eNameText.getText().trim().isEmpty()) {
                if (Validator.nicValidattion(eNICText.getText())) {
                    if(!eNICText.getText().trim().isEmpty()) {
                        if(!eTelNoText.getText().trim().isEmpty()) {
                            if(!designationIdText.getText().trim().isEmpty()) {
                                return true;
                            }else{
                                alertMethod("Select a designation!", designationIdText);
                                return false;
                            }
                        }else{
                            alertMethod("Address is empty!", eTelNoText);
                            return false;
                        }
                    }else{
                        alertMethod("Nic is empty!", eNICText);
                        return false;
                    }
                } else {
                    alertMethod("Nic is invalid!", eNICText);
                    return false;
                }
            }else{
                alertMethod("Name is empty!",eNameText);
                return false;
            }
        }else {
            alertMethod("Name is invalid!",eNameText);
            return false;
        }
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/lk/ijse/payroll/view/Employee.fxml"));
            empPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void employeeTableClicked(MouseEvent event) {
        Employee selectedItem = employeeTable.getSelectionModel().getSelectedItem();
        eIdText.setText(selectedItem.getEmpId()+"");
        eNameText.setText(selectedItem.getEmpName());
        eNICText.setText(selectedItem.getEmpNIC());
        eTelNoText.setText(selectedItem.getEmpAddress());

        try {
            Designation designation = designationDAO.searchByEmpId(selectedItem.getEmpId());
            designationIdText.setText(designation.getDesId()+"");
            designationCombo.setValue(designation.getDesDescription());
            addButton.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        if(validate()) {
            LocalDate ld = datePicker.getValue();
            Date date = localDateToDate(ld);
            try {
                boolean isUpdated = employeeDAO.update(new Employee(Integer.parseInt(eIdText.getText()), eNameText.getText(), eNICText.getText(), eTelNoText.getText(), new DesignationDetails(0, Integer.parseInt(eIdText.getText()), Integer.parseInt(designationIdText.getText()), date)));
                if (isUpdated) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Employee Updated!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    loadEmployees();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void setTimeDate() {
        datePicker.setValue(LocalDate.parse(new SimpleDateFormat("YYYY-MM-dd").format(new java.util.Date())));
    }

    private Date localDateToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    private void alertMethod(String msg, JFXTextField textField) {
        Alert error = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        error.show();
        textField.requestFocus();
        textField.selectAll();
    }
}
