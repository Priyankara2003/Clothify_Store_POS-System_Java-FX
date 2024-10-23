package repository.custom;

import entity.OrderEntity;
import repository.SuperDao;

public interface OrderDao extends SuperDao {
    boolean placeOrder(OrderEntity orderEntity);
    Integer getOrderId();
}
