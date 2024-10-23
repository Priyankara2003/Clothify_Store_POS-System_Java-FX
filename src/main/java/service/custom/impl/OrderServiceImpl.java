package service.custom.impl;

import dto.Order;
import entity.OrderEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDao;
import service.ServiceFactory;
import service.custom.OrderDetailsService;
import service.custom.OrderService;
import service.custom.ProductService;
import util.DaoType;
import util.ServiceType;

public class OrderServiceImpl implements OrderService {

    ModelMapper mapper = new ModelMapper();

    @Override
    public boolean placeOrder(Order order) {
        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.Order);
        OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.OrderDetails);
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.Product);
        OrderEntity orderEntity = mapper.map(order, OrderEntity.class);

        boolean isAdded = orderDao.placeOrder(orderEntity);

        if (isAdded){
           isAdded = orderDetailsService.addOrderDetail(order.getOrderDetails());
           if (isAdded){
               isAdded = productService.updateStock(order.getOrderDetails());
           }
        }
        return isAdded;
    }

    @Override
    public Integer getOrderId() {
        OrderDao daoType = DaoFactory.getInstance().getDaoType(DaoType.Order);
        return daoType.getOrderId();
    }
}
