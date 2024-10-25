package service.custom;

import dto.OrderDetails;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean addOrderDetail(List<OrderDetails> orderDetails);
    boolean addOrderDetail(OrderDetails orderDetails);
    ObservableList<OrderDetails> getAllDedtails(Integer orderId);
}
