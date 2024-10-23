package repository.custom.impl;

import entity.OrderDetailsEntity;
import org.hibernate.Session;
import repository.custom.OrderDetailsDao;
import util.HibernateUtil;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {

    public boolean addOrderDetail(List<OrderDetailsEntity> orderDetailsEntityList) {
        for (OrderDetailsEntity entity:orderDetailsEntityList){
            boolean isAdded = addOrderDetail(entity);
            if (!isAdded){
                return false;
            }
        }
        return true;
    }

    public boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(orderDetailsEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
