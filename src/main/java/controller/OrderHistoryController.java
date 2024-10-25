package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class OrderHistoryController {

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
    void loadDashboardForm(MouseEvent event) {

    }

    @FXML
    void loadInventoryForm(MouseEvent event) {

    }

    @FXML
    void loadReportsForm(MouseEvent event) {

    }

    @FXML
    void loadSalesForm(MouseEvent event) {

    }

    @FXML
    void loadSupplierForm(MouseEvent event) {

    }

}
