package lk.ijse.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.payroll.common.validation.Validator;
import lk.ijse.payroll.dao.DAOFactory;
import lk.ijse.payroll.dao.custom.impl.*;
import lk.ijse.payroll.entity.*;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {

    @FXML
    private TabPane salaryPane;

    @FXML
    private Label dateLabel1;

    @FXML
    private JFXTextField eNameNetText;

    @FXML
    private JFXTextField eNetNICText;

    @FXML
    private JFXTextField eNetDesignationText;

    @FXML
    private JFXTextField eIdTNetext;

    @FXML
    private JFXTextField eDayMustWorkedText;

    @FXML
    private JFXTextField basicSalaryText;

    @FXML
    private JFXTextField epfRateText;

    @FXML
    private JFXTextField etfRateText;

    @FXML
    private JFXTextField salaryAdvancedText;

    @FXML
    private JFXTextField noPayText;

    @FXML
    private JFXTextField allowencesTotalText;

    @FXML
    private JFXTextField otPayText;

    @FXML
    private Label netSalaryLabel;

    @FXML
    private JFXButton netSalaryAddButton;

    @FXML
    private JFXButton netSalaryCancelButton;

    @FXML
    private Label dateLabel;

    @FXML
    private JFXTextField eNameText;

    @FXML
    private JFXTextField eNICText;

    @FXML
    private JFXTextField eDesignationText;

    @FXML
    private JFXTextField eBasicSalaryText;

    @FXML
    private JFXTextField eIdText;

    @FXML
    private JFXTextField amountText;

    @FXML
    private TableView<MonthlyFinalSalary> salaryTable;


    @FXML
    private JFXButton advancedAddButton;

    @FXML
    private JFXButton advancedCancelButton;

    private SalaryAdvancedDAOImpl salaryAdvancedDAO;
    private DesignationDetailsDAOImpl designationDetailsDAO;
    private EmployeeDAOImpl employeeDAO;
    private DesignationDAOImpl designationDAO;
    private MonthlyWorkDetailsDAOImpl monthlyWorkDetailsDAO;
    private MonthlyFinalSalaryDAOImpl monthlyFinalSalaryDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salaryAdvancedDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SALARY_ADVANCED);
        designationDetailsDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DESIGNATION_DETAIL);
        employeeDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
        designationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DESIGNATION);
        monthlyWorkDetailsDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MONTHLY_WORK_DETAILS);
        monthlyFinalSalaryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MONTHLYFINALSALARY);
        setTimeDate();
        loadEmployeeNames();
        eIdTNetext.requestFocus();
        loadSalaryDetails();
        loadSalaryTableColumn();
        transition();
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), salaryPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void loadSalaryTableColumn() {
        salaryTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("monthlyFinalID"));
        salaryTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("monthlyWorkID"));
        salaryTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("totalAllowencesRate"));
        salaryTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noPay"));
        salaryTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("otPay"));
        salaryTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("SalaryAdvanced"));
        salaryTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("EPFPay"));
        salaryTable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("ETFPay"));
        salaryTable.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("netSalary"));
    }

    private void loadSalaryDetails() {
        ArrayList<MonthlyFinalSalary> monthlyFinalSalaryArrayList = null;
        try {
            monthlyFinalSalaryArrayList = monthlyFinalSalaryDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        salaryTable.setItems(FXCollections.observableArrayList(monthlyFinalSalaryArrayList));
    }

    private void loadEmployeeNames() {
        try {
            ArrayList<Employee> all = employeeDAO.getAll();
            ArrayList<String> eNames = new ArrayList<>();
            ArrayList<Integer> eIds = new ArrayList<>();
            for (Employee e : all) {
                eIds.add(e.getEmpId());
                eNames.add(e.getEmpName());
            }
            TextFields.bindAutoCompletion(eNameText, eNames);
            TextFields.bindAutoCompletion(eNameNetText, eNames);
            TextFields.bindAutoCompletion(eIdTNetext, eIds);
            TextFields.bindAutoCompletion(eIdText, eIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void eNameTextAction(ActionEvent event) {
        try {
            Employee employee = employeeDAO.searchByName(eNameText.getText());
            eNICText.setText(employee.getEmpNIC());
            eIdText.setText(employee.getEmpId() + "");
            amountText.requestFocus();
            Designation designation = designationDAO.searchByEmpId(employee.getEmpId());
            eDesignationText.setText(designation.getDesDescription());
            eBasicSalaryText.setText(designation.getDesBasicSalary() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void advancedAddButtonAction(ActionEvent event) {

        try {
            DesignationDetails designationDetails = designationDetailsDAO.searchByEmpId(Integer.parseInt(eIdText.getText()));
            boolean isAdded = salaryAdvancedDAO.save(new SalaryAdvanced(designationDetails.getDesDetId(), Double.parseDouble(amountText.getText())));
            if (isAdded) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Salary Advanced Added!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void advancedCancelButtonAction(ActionEvent event) {
        eIdText.setText("");
        eNameText.setText("");
        eNICText.setText("");
        eDesignationText.setText("");
        eBasicSalaryText.setText("");
        amountText.setText("");
        eIdText.requestFocus();
    }

    @FXML
    void netSalaryAddButtonAction(ActionEvent event) {
        try {
            DesignationDetails designationDetails = designationDetailsDAO.searchByEmpId(Integer.parseInt(eIdTNetext.getText()));
            MonthlyWorkDetails mon = monthlyWorkDetailsDAO.search(designationDetails.getDesDetId());
            if (mon != null && mon.getToMonth().substring(0, 3).equals(new SimpleDateFormat("MMM").format(new Date()))) {
                Alert a = new Alert(Alert.AlertType.ERROR, "This employee was paid!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
            } else {
                boolean isAdded = monthlyWorkDetailsDAO.save(new MonthlyWorkDetails(designationDetails.getDesDetId(), Integer.parseInt(eDayMustWorkedText.getText())));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Net Salary Done!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    MonthlyFinalSalary search = monthlyFinalSalaryDAO.search(designationDetails.getDesDetId());
                    netSalaryLabel.setText(search.getNetSalary() + "");
                    epfRateText.setText(search.getEPFPay() + "");
                    etfRateText.setText(search.getETFPay() + "");
                    salaryAdvancedText.setText(search.getSalaryAdvanced() + "");
                    noPayText.setText(search.getNoPay() + "");
                    allowencesTotalText.setText(search.getTotalAllowencesRate() + "");
                    otPayText.setText(search.getOtPay() + "");
                    loadSalaryDetails();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void netSalaryCancelButtonAction(ActionEvent event) {
        eIdTNetext.setText("");
        eNameNetText.setText("");
        eNetNICText.setText("");
        eNetDesignationText.setText("");
        eDayMustWorkedText.setText("");
        basicSalaryText.setText("");
        epfRateText.setText("");
        etfRateText.setText("");
        salaryAdvancedText.setText("");
        noPayText.setText("");
        allowencesTotalText.setText("");
        otPayText.setText("");
        netSalaryLabel.setText("Amount");
        eIdTNetext.requestFocus();
    }

    @FXML
    void eIdTextAction(ActionEvent event) {
        try {
            Employee employee = employeeDAO.search(Integer.parseInt(eIdText.getText()));
            eNICText.setText(employee.getEmpNIC());
            eNameText.setText(employee.getEmpName());

            Designation designation = designationDAO.searchByEmpId(employee.getEmpId());
            eDesignationText.setText(designation.getDesDescription());
            eBasicSalaryText.setText(designation.getDesBasicSalary() + "");
            amountText.requestFocus();
            advancedAddButton.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void eIdTNetextAction(ActionEvent event) {
        if(validateID()) {
            try {
                Employee employee = employeeDAO.search(Integer.parseInt(eIdTNetext.getText()));
                eNetNICText.setText(employee.getEmpNIC());
                eNameNetText.setText(employee.getEmpName() + "");

                Designation designation = designationDAO.searchByEmpId(employee.getEmpId());
                eNetDesignationText.setText(designation.getDesDescription());
                basicSalaryText.setText(designation.getDesBasicSalary() + "");
                eDayMustWorkedText.requestFocus();
                netSalaryAddButton.setDisable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateID() {
        if(!eIdTNetext.getText().trim().isEmpty()){
            if(Validator.intValueValidation(eIdTNetext.getText())){
                return true;
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR, "EmployeeId is invalid!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                eIdTNetext.requestFocus();
                eIdTNetext.selectAll();
                return false;
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Please add a employee Id!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            eIdTNetext.requestFocus();
            return false;
        }
    }

    @FXML
    void eNameNetTextAction(ActionEvent event) {
        try {
            Employee employee = employeeDAO.searchByName(eNameNetText.getText());
            eNetNICText.setText(employee.getEmpNIC());
            eIdTNetext.setText(employee.getEmpId() + "");

            Designation designation = designationDAO.searchByEmpId(employee.getEmpId());
            eNetDesignationText.setText(designation.getDesDescription());
            basicSalaryText.setText(designation.getDesBasicSalary() + "");
            eDayMustWorkedText.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setTimeDate() {
        dateLabel.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
        System.out.println();
        dateLabel1.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
    }
}
