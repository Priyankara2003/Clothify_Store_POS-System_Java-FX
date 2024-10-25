package repository.custom;

import entity.OrderEntity;
import repository.SuperDao;

import java.util.List;

public interface OrderDao extends SuperDao {
    boolean placeOrder(OrderEntity orderEntity);
    Integer getOrderId();
    List<OrderEntity> getAllOrders();
}
