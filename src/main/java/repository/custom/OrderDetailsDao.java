package repository.custom;

import entity.OrderDetailsEntity;
import repository.SuperDao;

import java.util.List;

public interface OrderDetailsDao extends SuperDao {
    boolean addOrderDetail(List<OrderDetailsEntity> orderDetailsEntityList);
    boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity);
    List<OrderDetailsEntity> getAllDetails(Integer orderId);
}
