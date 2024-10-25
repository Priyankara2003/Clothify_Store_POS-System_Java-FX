package controller;


import com.jfoenix.controls.JFXComboBox;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML
    private TableView<Product> tblProducts;

    @FXML
    private JFXComboBox<String> cmbSize;

    @FXML
    private JFXComboBox<String> cmbSupId;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private Label lblProductId;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductTblData();

        colID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        ObservableList<String> sizeList = FXCollections.observableArrayList();
        sizeList.add("Small");
        sizeList.add("Large");
        sizeList.add("Medium");
        sizeList.add("XL");
        sizeList.add("XXL");
        cmbSize.setItems(sizeList);

        tblProducts.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                addValueToText(newVal);
            }
        });
    }

    @FXML
    void btnAddProductOnAction(ActionEvent event) {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.Product);
        Product product = new Product(
                00,
                cmbSupId.getValue(),
                txtDescription.getText(),
                cmbSize.getValue(),
                txtPrice.getText(),
                txtQty.getText(),
                txtCategory.getText()
        );

        if (productService.addProduct(product)){
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
    void btnDeleteProductOnAction(ActionEvent event) {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.Product);
        if (productService.deleteProduct(Integer.parseInt(lblProductId.getText()))){
            new Alert(Alert.AlertType.INFORMATION).show();
            loadProductTblData();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }


    @FXML
    void btnUpdateProductOnAction(ActionEvent event) {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.Product);
        Product product = new Product(
                00,
                cmbSupId.getValue(),
                txtDescription.getText(),
                cmbSize.getValue(),
                txtPrice.getText(),
                txtQty.getText(),
                txtCategory.getText()
        );
        if (productService.updateProductInfo(product, Integer.parseInt(lblProductId.getText()))){
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
    void loadSupplierForm(MouseEvent event) {
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
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.Product);
        tblProducts.setItems(productService.getAllProducts());
    }

    private void clearFields(){
        cmbSupId.setValue("");
        txtDescription.setText("");
        cmbSize.setValue("");
        txtPrice.setText("");
        txtQty.setText("");
        txtCategory.setText("");
        lblProductId.setText("Auto Generated");
    }

    private void addValueToText(Product newVal) {
        cmbSupId.setValue(newVal.getSupplierId());
        txtDescription.setText(newVal.getProductName());
        cmbSize.setValue(newVal.getSize());
        txtPrice.setText(newVal.getPrice());
        txtQty.setText(newVal.getQtyOnHand());
        txtCategory.setText(newVal.getCategory());
        lblProductId.setText(String.valueOf(newVal.getProductId()));
    }
}
