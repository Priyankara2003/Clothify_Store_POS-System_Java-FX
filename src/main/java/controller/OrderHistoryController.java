package controller;

import dto.Order;
import dto.OrderDetails;
import dto.OrderedProducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.SuperService;
import service.custom.OrderDetailsService;
import service.custom.OrderService;
import service.custom.ProductService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderHistoryController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCashierName;

    @FXML
    private TableColumn<?, ?> colDescriptionProduct;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colProductQty;

    @FXML
    private TableColumn<?, ?> colProductTotal;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableView<Order> tblOrderHistory;

    @FXML
    private Label lblCashierName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPaymentType;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<OrderedProducts> tblOrderedProducts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderHistorytbl();

        colCashierName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("netTotal"));

        colDescriptionProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colProductTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblOrderHistory.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                loadOrderDetails(newVal);
            }
        });
    }

    @FXML
    void loadDashboardForm(MouseEvent event) {
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

    @FXML
    void loadReportsForm(MouseEvent event) {
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cashier/Inventory.fxml"));
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

    private void loadOrderHistorytbl() {
        OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.Order);
        tblOrderHistory.setItems(orderService.getAllOrders());
    }

    private void loadOrderDetails(Order newVal) {
        lblCashierName.setText(newVal.getEmployeeName());
        lblOrderId.setText(String.valueOf(newVal.getOrderId()));
        lblOrderDate.setText(newVal.getDate());
        lblPaymentType.setText(newVal.getPaymentMethod());
        lblTotal.setText(String.valueOf(newVal.getNetTotal()));

        OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.OrderDetails);
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.Product);

        ObservableList<OrderDetails> allDedtails = orderDetailsService.getAllDedtails(newVal.getOrderId());
        ObservableList<OrderedProducts> orderProductDetails = FXCollections.observableArrayList();

        for (OrderDetails orderDetails:allDedtails){
            orderProductDetails.add(new OrderedProducts(
                    productService.getProductName(orderDetails.getProductId()),
                    orderDetails.getQty(),
                    orderDetails.getTotal()
            ));
        }
        tblOrderedProducts.setItems(orderProductDetails);
    }
}
