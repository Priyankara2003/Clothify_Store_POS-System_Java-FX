package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.UserDetailsService;
import util.ServiceType;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblMassage;

    @FXML
    private Button btnCancel;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        UserDetailsService userDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.UserDetails);
        String username = txtUsername.getText();
        if (!txtUsername.getText().isBlank() && !txtPassword.getText().isBlank()){
            String role = userDetailsService.isExistsUser(username,txtPassword.getText());
            if (role!=null){
                if (role.equalsIgnoreCase("admin")){
                    lblMassage.setText("Welcome "+role+" "+username);
                } else {
                    lblMassage.setText("Welcome "+role+" "+username);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cashier/Sales.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));

                        // Get the current window
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        // Close the previous window
                        currentStage.close();
                        stage.setResizable(false);
                        stage.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                lblMassage.setText("Invalid Login Details.");
            }
        }else {
            lblMassage.setText("Please fill email and password");
        }
    }
}
