package controller;

import com.jfoenix.controls.JFXCheckBox;
import dto.CartItem;
import dto.Order;
import dto.OrderDetails;
import dto.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import service.ServiceFactory;
import service.SuperService;
import service.custom.OrderService;
import service.custom.ProductService;
import service.custom.impl.OrderServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SalesController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCartItmDescription;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colCartItemQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colCartItemTotal;

    @FXML
    private Label lblChange;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<CartItem> tblCartItems;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TextField txtAmount;

    @FXML
    private JFXCheckBox chkCard;

    @FXML
    private JFXCheckBox chkCash;

    @FXML
    private TextField txtQty;

    private final ObservableList<CartItem> cartItemList = FXCollections.observableArrayList();

    private CartItem cartItem;

    private int orderId = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductTblData();

        colDescription.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        colCartItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCartItemTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colCartItmDescription.setCellValueFactory(new PropertyValueFactory<>("productName"));

        tblProduct.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount()>=1) {
                Product newVal = tblProduct.getSelectionModel().getSelectedItem();
                if (newVal != null) {
                    addItemToCart(newVal);
                    calNetTotal();
                }
            }
        });

        tblCartItems.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                changeQtyFunc(newVal);
                cartItem = newVal;
            }
        });

        addOrderId();
        calcBalance();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearOrder();
    }

    @FXML
    void btnPaidOnAction(ActionEvent event) {
        Date date1 = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormatted = f.format(date1);
        List<OrderDetails> orderDetails = FXCollections.observableArrayList();
        String paymentType = "";

        if(chkCard.isSelected() && chkCash.isSelected()){
            new Alert(Alert.AlertType.ERROR,"PLese Select Only one Method").show();
            return;
        } else if (chkCash.isSelected()) {
            paymentType = "Cash";
        } else if (chkCard.isSelected()) {
            paymentType = "Card";
        }else {
            new Alert(Alert.AlertType.ERROR,"PLese Select payment Method").show();
        }

        for (CartItem cartItem1:cartItemList){
            orderDetails.add(new OrderDetails(
                    orderId,
                    cartItem1.getProductId(),
                    cartItem1.getQty(),
                    cartItem1.getTotal()
            ));
        }

        Order order = new Order(orderId,dateFormatted,calNetTotal(),paymentType,orderDetails);

        if (new OrderServiceImpl ().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION,"Order Placed Successfully").show();
            addOrderId();
            clearOrder();
        }else{
            new Alert(Alert.AlertType.ERROR,"An Error Occurred").show();
        }
    }

    @FXML
    void btnRemoveCartItemOnAction(ActionEvent event) {
        cartItemList.remove(cartItem);
        calNetTotal();
        loadCartItemTable();
    }

    private void loadProductTblData() {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.Product);
        tblProduct.setItems(productService.getAllProducts());
    }

    private void addItemToCart(Product newVal) {
        int qty = 1;
        boolean isExists = false;

        if (cartItemList != null) {
            for (CartItem cartItem : cartItemList) {
                if (cartItem.getProductId() == newVal.getProductId()) {
                    qty = cartItem.getQty();
                    cartItem.setQty(++qty);
                    cartItem.setTotal(calcCartItemTotal(cartItem.getQty(), Double.parseDouble(cartItem.getPrice())));

                    isExists = true;
                }
            }
        }

        if (!isExists) {
            this.cartItemList.add(new CartItem(
                    newVal.getProductId(),
                    newVal.getProductName(),
                    newVal.getSize(),
                    newVal.getCategory(),
                    newVal.getPrice(),
                    new SimpleDoubleProperty(calcCartItemTotal(1, Double.parseDouble(newVal.getPrice()))),
                    new SimpleIntegerProperty(qty)
            ));
        }

        loadCartItemTable();
    }

    private void loadCartItemTable() {
        tblCartItems.setItems(cartItemList);
    }

    private Double calcCartItemTotal(Integer qty, double v) {
        return qty*v;
    }

    private void changeQtyFunc(CartItem newVal) {
        txtQty.setText(String.valueOf(newVal.getQty()));
        txtQty.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                newVal.setQty(Integer.parseInt(txtQty.getText()));
                newVal.setTotal(calcCartItemTotal(newVal.getQty(), Double.parseDouble(newVal.getPrice())));
                calNetTotal();
                loadCartItemTable();
            }
        });
    }

    private Double calNetTotal(){
        Double netTotal = 0.0;
        if(!cartItemList.isEmpty()) {
            for (CartItem cartItem1 : cartItemList) {
                netTotal += cartItem1.getTotal();
            }
            lblNetTotal.setText(String.valueOf(netTotal));
        }else {
            lblNetTotal.setText("0000.00");
        }
        return netTotal;
    }

    private void calcBalance(){
        txtAmount.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                Double change = Integer.parseInt(txtAmount.getText()) - Double.parseDouble(lblNetTotal.getText());
                lblChange.setText(String.valueOf(change));
            }
        });
    }

    private void addOrderId() {
        OrderService serviceType = ServiceFactory.getInstance().getServiceType(ServiceType.Order);
        Integer orderId1 = serviceType.getOrderId();
        if (orderId1 == null) {
            orderId = 1;
        }else {
            orderId = orderId1+1;
        }
    }

    private void clearOrder(){
        cartItemList.clear();
        txtQty.setText("");
        txtAmount.setText("");
        lblChange.setText("0000.00");
        chkCard.setSelected(false);
        chkCash.setSelected(false);
        calNetTotal();
        loadCartItemTable();
    }
}