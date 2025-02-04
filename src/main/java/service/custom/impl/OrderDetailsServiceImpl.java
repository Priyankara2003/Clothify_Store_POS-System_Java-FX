package service.custom.impl;

import dto.OrderDetails;
import entity.OrderDetailsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.OrderDetailsDao;
import service.custom.OrderDetailsService;
import util.DaoType;

import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    ModelMapper mapper = new ModelMapper();

    @Override
    public boolean addOrderDetail(List<OrderDetails> orderDetails) {
        for (OrderDetails orderDetails1: orderDetails){
            boolean isAdded = addOrderDetail(orderDetails1);
            if (!isAdded){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addOrderDetail(OrderDetails orderDetails) {
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.OrderDetails);
        OrderDetailsEntity orderDetailsEntity = mapper.map(orderDetails, OrderDetailsEntity.class);
        return orderDetailsDao.addOrderDetail(orderDetailsEntity);
    }

    @Override
    public ObservableList<OrderDetails> getAllDedtails(Integer orderId) {
        ObservableList<OrderDetails> orderDetailsObservableList = FXCollections.observableArrayList();
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.OrderDetails);
        List<OrderDetailsEntity> allDetails = orderDetailsDao.getAllDetails(orderId);
        for (OrderDetailsEntity orderDetailsEntity : allDetails){
            orderDetailsObservableList.add(mapper.map(orderDetailsEntity,OrderDetails.class));
        }
        return orderDetailsObservableList;
    }
}
