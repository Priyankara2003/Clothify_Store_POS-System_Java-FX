package repository.custom.impl;

import entity.SupplierEntity;
import org.hibernate.Session;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<SupplierEntity> findAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<SupplierEntity> supplierEntityList = session.createNativeQuery("SELECT * FROM supplierdetails", SupplierEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return supplierEntityList;
    }

    @Override
    public boolean delete(int Id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierEntity supplierEntity = session.get(SupplierEntity.class, Id);
        session.remove(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
