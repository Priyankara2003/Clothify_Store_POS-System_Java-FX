package repository.custom;

import entity.OrderDetailsEntity;
import entity.ProductEntity;
import repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<ProductEntity> {
    boolean updateStock(List<OrderDetailsEntity> orderDetailsEntity);
    boolean updateStock(OrderDetailsEntity orderDetailsEntity);
}
