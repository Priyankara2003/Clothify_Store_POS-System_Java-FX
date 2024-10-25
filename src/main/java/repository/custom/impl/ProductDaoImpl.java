package repository.custom.impl;

import entity.OrderDetailsEntity;
import entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.ProductDao;
import util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public boolean save(ProductEntity product) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(product);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<ProductEntity> findAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<ProductEntity> productEntityList = session.createNativeQuery("SELECT * FROM productdetails", ProductEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return productEntityList;
    }

    @Override
    public boolean delete(int productId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ProductEntity productEntity = session.get(ProductEntity.class, productId);
        session.remove(productEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateStock(List<OrderDetailsEntity> orderDetailsEntity) {
        for (OrderDetailsEntity orderDetails:orderDetailsEntity){
            boolean isAdded = updateStock(orderDetails);
            if(!isAdded){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateStock(OrderDetailsEntity orderDetailsEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ProductEntity productEntity = session.get(ProductEntity.class, orderDetailsEntity.getProductId());
        if (productEntity==null){
            return false;
        }
        int qtyOnHand = Integer.parseInt(productEntity.getQtyOnHand());
        productEntity.setQtyOnHand(String.valueOf(qtyOnHand- orderDetailsEntity.getQty()));
        session.merge(productEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public String getProductName(Integer productId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ProductEntity productEntity = session.get(ProductEntity.class, productId);
        session.getTransaction().commit();
        session.close();
        if (productEntity==null){
            return null;
        }
        return productEntity.getProductName();
    }
}
