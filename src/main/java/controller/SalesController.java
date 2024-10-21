package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SalesController {

    @FXML
    private TableColumn<?, ?> colCartItmDescription;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblChange;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<?> tblCartItems;

    @FXML
    private TableView<?> tblProduct;

    @FXML
    private TextField txtAmount;

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaidOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveCartItemOnAction(ActionEvent event) {

    }

    @FXML
    void chkCard(ActionEvent event) {

    }

    @FXML
    void chkCash(ActionEvent event) {

    }

}