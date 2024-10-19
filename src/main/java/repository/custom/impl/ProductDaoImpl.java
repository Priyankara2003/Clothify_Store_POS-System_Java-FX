package repository.custom.impl;

import entity.ProductEntity;
import org.hibernate.Session;
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
        return false;
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
}
