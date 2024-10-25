package service.custom;

import dto.Order;
import javafx.collections.ObservableList;
import service.SuperService;

public interface OrderService extends SuperService {
    boolean placeOrder(Order order);
    Integer getOrderId();
    ObservableList<Order>  getAllOrders();
}
