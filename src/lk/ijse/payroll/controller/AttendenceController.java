package lk.ijse.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.payroll.common.validation.Validator;
import lk.ijse.payroll.dao.DAOFactory;
import lk.ijse.payroll.dao.custom.impl.AttendenceDAOImpl;
import lk.ijse.payroll.dao.custom.impl.DesignationDAOImpl;
import lk.ijse.payroll.dao.custom.impl.DesignationDetailsDAOImpl;
import lk.ijse.payroll.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.payroll.entity.Attendence;
import lk.ijse.payroll.entity.Designation;
import lk.ijse.payroll.entity.DesignationDetails;
import lk.ijse.payroll.entity.Employee;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AttendenceController implements Initializable {

    @FXML
    private AnchorPane attendancePane;

    @FXML
    private Label dateLabel;

    @FXML
    private Label dateLabel1;

    @FXML
    private TitledPane eAttendenceDetailPane;

    @FXML
    private JFXTextField aInText;

    @FXML
    private JFXTextField aOutText;

    @FXML
    private Label otLabel;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton leaveButton;

    @FXML
    private JFXTextField eIdText;

    @FXML
    private JFXTextField eNameText;

    @FXML
    private JFXTextField eNICText;

    @FXML
    private JFXTextField eDesignationText;

    @FXML
    private TableView<Attendence> attendanceTable;

    @FXML
    private JFXRadioButton absentRButton;

    @FXML
    private ToggleGroup status;

    @FXML
    private JFXRadioButton presentRButton;

    private AttendenceDAOImpl attendenceDAO;
    private EmployeeDAOImpl employeeDAO;
    private DesignationDAOImpl designationDAO;
    private DesignationDetailsDAOImpl designationDetailsDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        attendenceDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ATTENDENCE);
        employeeDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
        designationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DESIGNATION);
        designationDetailsDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DESIGNATION_DETAIL);
        setTimeDate();
        loadEmployeeNames();
        loadAttendances();
        loadAttendanceTableColumn();
        eIdText.requestFocus();
        transition();
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), attendancePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void loadAttendanceTableColumn() {
        attendanceTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("attId"));
        attendanceTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("desDetId"));
        attendanceTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("attDate"));
        attendanceTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
        attendanceTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("inTime"));
        attendanceTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("outTime"));
        attendanceTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("otHours"));

    }

    private void loadAttendances() {
        try {
            ArrayList<Attendence> attendenceDAOAll = attendenceDAO.getAll();
//            ArrayList<Attendence> attendences = new ArrayList<>();
//            for (Attendence a :attendenceDAOAll) {
//                DesignationDetails search = designationDetailsDAO.search(a.getDesDetId());
//                Employee employee = employeeDAO.search(search.getEmpId());
//                attendences.add(new Attendence(a.getAttDate(),a.getStatus(),a.getInTime(),a.getOutTime(),a.getOtHours(),new Employee(employee.getEmpId(),employee.getEmpName(),employee.getEmpNIC(),employee.getEmpAddress())));
//            }

            attendanceTable.setItems(FXCollections.observableArrayList(attendenceDAOAll));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeNames() {
        try {
            ArrayList<Employee> all = employeeDAO.getAll();
            ArrayList<String> eNames = new ArrayList<>();
            ArrayList<Integer> eIds = new ArrayList<>();
            for (Employee e : all) {
                eNames.add(e.getEmpName());
                eIds.add(e.getEmpId());
            }
            TextFields.bindAutoCompletion(eNameText, eNames);
            TextFields.bindAutoCompletion(eIdText, eIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void eNameTextAction(ActionEvent event) {
        try {
            Employee employee = employeeDAO.searchByName(eNameText.getText());
            eIdText.setText(employee.getEmpId() + "");
            eNICText.setText(employee.getEmpNIC());

            Designation designation = designationDAO.searchByEmpId(employee.getEmpId());
            eDesignationText.setText(designation.getDesDescription());

            DesignationDetails designationDetails = designationDetailsDAO.searchByEmpId(employee.getEmpId());
            int desDetId = designationDetails.getDesDetId();

            Attendence attendence = attendenceDAO.searchByDesDetId(desDetId);
            if (attendence == null) {
                aInText.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                aOutText.setDisable(true);
                leaveButton.setDisable(true);
                presentRButton.requestFocus();
            } else {
                if (attendence.getAttDate().toString().equals(new SimpleDateFormat("YYYY-MM-dd").format(new Date())) && attendence.getStatus().equals("present")) {
                    aOutText.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                    aInText.setDisable(true);
                    leaveButton.setDisable(false);
                    aOutText.requestFocus();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "This employee already attend!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void eIdTextAction(ActionEvent event) {
        if(validate()) {
            try {
                Employee employee = employeeDAO.search(Integer.parseInt(eIdText.getText()));
                eNameText.setText(employee.getEmpName());
                eNICText.setText(employee.getEmpNIC());

                Designation designation = designationDAO.searchByEmpId(employee.getEmpId());
                eDesignationText.setText(designation.getDesDescription());

                DesignationDetails designationDetails = designationDetailsDAO.searchByEmpId(employee.getEmpId());
                int desDetId = designationDetails.getDesDetId();

                Attendence attendence = attendenceDAO.searchByDesDetId(desDetId);
                if (attendence == null) {
                    aInText.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                    aOutText.setDisable(true);
                    leaveButton.setDisable(true);
                    presentRButton.requestFocus();
                } else {
                    if (attendence.getAttDate().toString().equals(new SimpleDateFormat("YYYY-MM-dd").format(new Date())) && attendence.getOutTime().toString().equals("00:00:00") && attendence.getStatus().equals("present")) {
                        aOutText.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                        aInText.setDisable(true);
                        leaveButton.setDisable(false);
                        addButton.setDisable(true);
                        aOutText.requestFocus();
                    } else {
                        addButton.setDisable(true);
                        eAttendenceDetailPane.setDisable(true);
                        Alert a = new Alert(Alert.AlertType.ERROR, "This employee already attended!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void absentRButtonAction(ActionEvent event) {
        eAttendenceDetailPane.setDisable(true);
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        if(validate()) {
            try {
                String status;
                if (presentRButton.isSelected()) {
                    status = "present";
                } else {
                    status = "absent";
                }

                DesignationDetails designationDetails = designationDetailsDAO.searchByEmpId(Integer.parseInt(eIdText.getText()));
                boolean isAdded = attendenceDAO.save(new Attendence(designationDetails.getDesDetId(), status));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Attendance Done!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    loadAttendances();
                    cancelButtonAction(event);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private boolean validate() {
        if(!eIdText.getText().trim().isEmpty()){
            if(Validator.intValueValidation(eIdText.getText())){
                return true;
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR, "Employee Id is invalid!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                eIdText.requestFocus();
                eIdText.selectAll();
                return false;
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Please add a employee!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            eIdText.requestFocus();
            return false;
        }
    }

    @FXML
    void leaveButtonAction(ActionEvent event) {
        try {
            String status;
            if (presentRButton.isSelected()) {
                status = "present";
            } else {
                status = "absent";
            }

            DesignationDetails designationDetails = designationDetailsDAO.searchByEmpId(Integer.parseInt(eIdText.getText()));
            boolean isAdded = attendenceDAO.saveLeave(new Attendence(designationDetails.getDesDetId(), status));
            if (isAdded) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Leave Done!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                loadAttendances();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/lk/ijse/payroll/view/attendence.fxml"));
            attendancePane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void presentRButtonAction(ActionEvent event) {
        eAttendenceDetailPane.setDisable(false);
    }

    private void setTimeDate() {
        dateLabel.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
        // aInText.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
    }
}
