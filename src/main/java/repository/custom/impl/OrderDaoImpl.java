package repository.custom.impl;

import entity.OrderEntity;
import org.hibernate.Session;
import repository.custom.OrderDao;
import util.HibernateUtil;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean placeOrder(OrderEntity orderEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(orderEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Integer getOrderId(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Integer> selectOrderIdFromOrders = session.createNativeQuery("SELECT orderId FROM orders", Integer.class).getResultList();
        if (selectOrderIdFromOrders.size()==0){
            return 1;
        }
        return selectOrderIdFromOrders.getLast();
    }
}
