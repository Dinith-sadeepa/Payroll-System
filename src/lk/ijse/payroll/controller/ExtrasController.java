package lk.ijse.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.payroll.common.validation.Validator;
import lk.ijse.payroll.dao.DAOFactory;
import lk.ijse.payroll.dao.custom.impl.AllowencesDAOImpl;
import lk.ijse.payroll.dao.custom.impl.ConfigTableDAOImpl;
import lk.ijse.payroll.dao.custom.impl.DesignationDAOImpl;
import lk.ijse.payroll.entity.Allowences;
import lk.ijse.payroll.entity.ConfigTable;
import lk.ijse.payroll.entity.Designation;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ExtrasController implements Initializable {

    @FXML
    private AnchorPane extrasPane;

    @FXML
    private JFXTextField DDescriptionText;

    @FXML
    private JFXTextField basicSalaryText;

    @FXML
    private JFXButton addDesignationButton;

    @FXML
    private JFXButton cancelDesignationButton;

    @FXML
    private JFXButton updateDesignationButton;

    @FXML
    private JFXTextField ADescriptionText;

    @FXML
    private JFXTextField ARateText;

    @FXML
    private JFXButton addAllowencesButton;

    @FXML
    private JFXButton cancelAllowencesButton;

    @FXML
    private JFXButton updateAllowencesButton;

    @FXML
    private JFXTextField CDescriptionText;

    @FXML
    private JFXTextField CRateText;

    @FXML
    private JFXButton addConfigButton;

    @FXML
    private JFXButton cancelConfigButton;

    @FXML
    private JFXButton updateConfigButton;

    @FXML
    private Label dateLabel;

    @FXML
    private Label dateLabel1;

    @FXML
    private TableView<Designation> designationTable;

    @FXML
    private TableView<ConfigTable> configTable;

    @FXML
    private TableView<Allowences> allowencesTable;

    private AllowencesDAOImpl allowencesDAO;
    private ConfigTableDAOImpl configTableDAO;
    private DesignationDAOImpl designationDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTimeDate();
        allowencesDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ALLOWENCES);
        designationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DESIGNATION);
        configTableDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CONFIG_TABLE);
        loadDesignations();
        loadDesignationsColumns();
        loadAllowences();
        loadAllowencesColumns();
        loadConfig();
        loadConfigColumns();
        transition();
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), extrasPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void loadConfigColumns() {
        configTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("configDescription"));
        configTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("configRate"));
    }

    private void loadConfig() {
        try {
            ArrayList<ConfigTable> configTableDAOAll = configTableDAO.getAll();
            configTable.setItems(FXCollections.observableArrayList(configTableDAOAll));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllowencesColumns() {
        allowencesTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("allowencesDescription"));
        allowencesTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("allowencesRate"));
    }

    private void loadAllowences() {
        try {
            ArrayList<Allowences> allowencesDAOAll = allowencesDAO.getAll();
            allowencesTable.setItems(FXCollections.observableArrayList(allowencesDAOAll));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDesignationsColumns() {
        designationTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("desDescription"));
        designationTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("desBasicSalary"));
    }

    private void loadDesignations() {
        try {
            ArrayList<Designation> designationDAOAll = designationDAO.getAll();
            designationTable.setItems(FXCollections.observableArrayList(designationDAOAll));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addAllowencesButtonAction(ActionEvent event) {
        if(validateAllowences()) {
            try {
                if (isEmpty(ADescriptionText, ARateText)) {
                    boolean isAdded = allowencesDAO.save(new Allowences(ADescriptionText.getText(), Integer.parseInt(ARateText.getText())));
                    if (isAdded) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Allowence Added!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        clean(ADescriptionText, ARateText);
                        loadAllowences();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateAllowences() {
        if(!ADescriptionText.getText().trim().isEmpty()){
            if(!ARateText.getText().trim().isEmpty()){
                if(Validator.intValueValidation(ARateText.getText())){
                    return true;
                }else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Allowences Rate is invalid!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    ARateText.requestFocus();
                    ARateText.selectAll();
                    return false;
                }
            }else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Allowences Rate is empty!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                ARateText.requestFocus();
                return false;
            }
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Allowences Description is empty!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            ADescriptionText.requestFocus();
            return false;
        }
    }


    @FXML
    void addConfigButtonAction(ActionEvent event) {
        if(validateConfig()) {
            try {
                if (isEmpty(CDescriptionText, CRateText)) {
                    boolean isAdded = configTableDAO.save(new ConfigTable(CDescriptionText.getText(), Integer.parseInt(CRateText.getText())));
                    if (isAdded) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Config Added!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        clean(CDescriptionText, CRateText);
                        loadConfig();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void addDesignationButtonAction(ActionEvent event) {
        if(validateDesignation()) {
            try {
                if (isEmpty(DDescriptionText, basicSalaryText)) {
                    boolean isAdded = designationDAO.save(new Designation(0, DDescriptionText.getText(), Double.parseDouble(basicSalaryText.getText())));
                    if (isAdded) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Designation Added!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        clean(DDescriptionText, basicSalaryText);
                        loadDesignations();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateDesignation() {
        if(!DDescriptionText.getText().trim().isEmpty()){
            if(!basicSalaryText.getText().trim().isEmpty()){
                if(Validator.intValueValidation(basicSalaryText.getText())){
                    return true;
                }else{
                    Alert a = new Alert(Alert.AlertType.ERROR, "Basic salary invalid!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    basicSalaryText.requestFocus();
                    basicSalaryText.selectAll();
                    return false;
                }
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR, "Basic salary is empty!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                basicSalaryText.requestFocus();
                return false;
            }
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Description is empty!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            DDescriptionText.requestFocus();
            return false;
        }
    }

    private boolean validateConfig() {
        if(!CDescriptionText.getText().trim().isEmpty()){
            if(!CRateText.getText().trim().isEmpty()){
                if(Validator.intValueValidation(CRateText.getText())){
                    return true;
                }else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Config Rate is invalid!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    CRateText.requestFocus();
                    CRateText.selectAll();
                    return false;
                }
            }else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Config Rate is empty!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                CRateText.requestFocus();
                return false;
            }
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Config Description is empty!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            CDescriptionText.requestFocus();
            return false;
        }
    }

    @FXML
    void configTableClicked(MouseEvent event) {
        ConfigTable selectedItem = configTable.getSelectionModel().getSelectedItem();
        CDescriptionText.setText(selectedItem.getConfigDescription());
        CRateText.setText(selectedItem.getConfigRate() + "");
        addConfigButton.setDisable(true);
        updateConfigButton.setDisable(false);
    }

    @FXML
    void designationTableClicked(MouseEvent event) {
        Designation selectedItem = designationTable.getSelectionModel().getSelectedItem();
        DDescriptionText.setText(selectedItem.getDesDescription());
        basicSalaryText.setText(selectedItem.getDesBasicSalary() + "");
        addDesignationButton.setDisable(true);
        updateDesignationButton.setDisable(false);
    }

    @FXML
    void allowencesTableClicked(MouseEvent event) {
        Allowences selectedItem = allowencesTable.getSelectionModel().getSelectedItem();
        ADescriptionText.setText(selectedItem.getAllowencesDescription());
        ARateText.setText(selectedItem.getAllowencesRate() + "");
        addAllowencesButton.setDisable(true);
        updateAllowencesButton.setDisable(false);
    }

    @FXML
    void updateAllowencesButtonAction(ActionEvent event) {
        boolean isUpdated = false;
        try {
            isUpdated = allowencesDAO.update(new Allowences(ADescriptionText.getText(), Integer.parseInt(ARateText.getText())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isUpdated) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Allowence Updated!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            clean(ADescriptionText, ARateText);
            loadAllowences();
            updateAllowencesButton.setDisable(true);
            addAllowencesButton.setDisable(false);
        }
    }

    @FXML
    void updateConfigButtonAction(ActionEvent event) {
        try {
                boolean isUpdated = configTableDAO.update(new ConfigTable(CDescriptionText.getText(), Integer.parseInt(CRateText.getText())));
                if (isUpdated) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Config Updated!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    clean(CDescriptionText, CRateText);
                    loadConfig();
                    updateConfigButton.setDisable(true);
                    addConfigButton.setDisable(false);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateDesignationButtonAction(ActionEvent event) {
        try {
            boolean isUpdated = designationDAO.update(new Designation(0, DDescriptionText.getText(), Double.parseDouble(basicSalaryText.getText())));
            if (isUpdated) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Designation Updated!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                clean(DDescriptionText, basicSalaryText);
                loadDesignations();
                updateDesignationButton.setDisable(true);
                addDesignationButton.setDisable(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelAllowencesButtonAction(ActionEvent event) {
        clean(ADescriptionText, ARateText);
        updateAllowencesButton.setDisable(true);
        addAllowencesButton.setDisable(false);
    }

    @FXML
    void cancelConfigButtonAction(ActionEvent event) {
        clean(CDescriptionText, CRateText);
        updateConfigButton.setDisable(true);
        addConfigButton.setDisable(false);
    }

    @FXML
    void cancelDesignationButtonAction(ActionEvent event) {
        clean(DDescriptionText, basicSalaryText);
        updateDesignationButton.setDisable(true);
        addDesignationButton.setDisable(false);
    }


    private void setTimeDate() {
        dateLabel.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
    }

    private void clean(JFXTextField textField1, JFXTextField textField2) {
        textField1.setText("");
        textField2.setText("");
        textField1.requestFocus();
    }

    private boolean isEmpty(JFXTextField text, JFXTextField text1) {
        if (!text.getText().trim().isEmpty() && !text1.getText().trim().isEmpty()) {
            return true;
        } else {
            if (text.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, text.getId().substring(1, 12) + " can't empty!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                text.requestFocus();
            } else if (text1.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, text1.getId().substring(1, 5) + " can't empty!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                text1.requestFocus();
            }
            return false;
        }
    }
}
