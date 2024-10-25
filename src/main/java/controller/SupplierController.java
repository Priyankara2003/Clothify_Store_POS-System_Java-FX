package controller;

import dto.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.SupplierService;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<Supplier> tblSupplierDetails;

    @FXML
    private Label lblSupplierId;

    @FXML
    private TextField txtSupplierCompany;

    @FXML
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplierName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductTblData();

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tblSupplierDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                addValueToText(newVal);
            }
        });
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        SupplierServiceImpl supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.Supplier);
        Supplier supplier = new Supplier(
                00,
                txtSupplierName.getText(),
                txtSupplierCompany.getText(),
                txtSupplierEmail.getText(),
                new Date().toString()
        );
        if (supplierService.addSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION).show();
            loadProductTblData();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnClearFieldsOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.Supplier);
        if (supplierService.deleteSupplier(Integer.parseInt(lblSupplierId.getText()))){
            new Alert(Alert.AlertType.INFORMATION).show();
            loadProductTblData();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        SupplierServiceImpl supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.Supplier);
        Supplier supplier = new Supplier(
                00,
                txtSupplierName.getText(),
                txtSupplierCompany.getText(),
                txtSupplierEmail.getText(),
                new Date().toString()
        );
        if (supplierService.updateSupplierInfo(supplier, Integer.parseInt(lblSupplierId.getText()))){
            new Alert(Alert.AlertType.INFORMATION).show();
            loadProductTblData();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void loadDashBoardForm(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cashier/DashBoard.fxml"));
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

    @FXML
    void loadOrderHistoryForm(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cashier/OrderHistory.fxml"));
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

    @FXML
    void loadReportForm(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cashier/Reports.fxml"));
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

    @FXML
    void loadSalesForm(MouseEvent event) {
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

    @FXML
    void loadInventoryForm(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cashier/Supplier.fxml"));
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

    private void loadProductTblData() {
        SupplierServiceImpl supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.Supplier);
        tblSupplierDetails.setItems(supplierService.getAllSuppliers());
    }

    private void clearFields(){
        txtSupplierCompany.setText("");
        txtSupplierEmail.setText("");
        txtSupplierName.setText("");
        lblSupplierId.setText("Auto Generated");
    }

    private void addValueToText(Supplier newVal) {
        txtSupplierCompany.setText(newVal.getCompany());
        txtSupplierEmail.setText(newVal.getEmail());
        txtSupplierName.setText(newVal.getName());
        lblSupplierId.setText(String.valueOf(newVal.getSupplierId()));
    }
}
