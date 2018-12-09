package lk.ijse.payroll.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private ImageView employeeIcon;

    @FXML
    private ImageView salaryIcon;

    @FXML
    private ImageView attendenceIcon;

    @FXML
    private ImageView extrasIcon;

    @FXML
    private ImageView closeIcon;

    @FXML
    private AnchorPane loadingPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), dashboardPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    void attendenceIconClicked(MouseEvent event) {
        try {
            loadPane("/lk/ijse/payroll/view/attendence.fxml", loadingPane, attendenceIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void closeIconClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void employeeIconClicked(MouseEvent event) {
        try {
            loadPane("/lk/ijse/payroll/view/Employee.fxml", loadingPane, employeeIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void extrasIconClicked(MouseEvent event) {
        try {
            loadPane("/lk/ijse/payroll/view/extras.fxml", loadingPane, extrasIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salaryIconClicked(MouseEvent event) {
        try {
            TabPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/payroll/view/salaryTab.fxml"));
            loadingPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPane(String path, AnchorPane pane, ImageView imageView) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource(path));
        pane.getChildren().setAll(root);
    }

    @FXML
    void attendenceIconMouseEnterd(MouseEvent event) {
        mouseEnterd(attendenceIcon);
    }

    @FXML
    void attendenceIconMouseExited(MouseEvent event) {
        mouseExited(attendenceIcon);
    }

    @FXML
    void employeeIconMouseEnterd(MouseEvent event) {
        mouseEnterd(employeeIcon);
    }

    @FXML
    void employeeIconMouseExited(MouseEvent event) {
        mouseExited(employeeIcon);
    }

    @FXML
    void extrasIconMouseEnterd(MouseEvent event) {
        mouseEnterd(extrasIcon);
    }

    @FXML
    void extrasIconMouseExited(MouseEvent event) {
        mouseExited(extrasIcon);
    }

    @FXML
    void salaryIconMouseEnterd(MouseEvent event) {
        mouseEnterd(salaryIcon);
    }

    @FXML
    void salaryIconMouseExited(MouseEvent event) {
        mouseExited(salaryIcon);
    }


    private void mouseEnterd(ImageView image) {
        ScaleTransition sca = new ScaleTransition(Duration.millis(200), image);
        sca.setToX(1.2);
        sca.setToY(1.2);
        sca.play();

        DropShadow d = new DropShadow();
        d.setColor(Color.DARKORANGE);
        d.setHeight(20);
        d.setWidth(20);
        d.setRadius(20);
        image.setEffect(d);
    }

    private void mouseExited(ImageView image) {
        image.setEffect(null);

        ScaleTransition s = new ScaleTransition(Duration.millis(200), image);
        s.setToX(1.0);
        s.setToY(1.0);
        s.play();
    }

}
