package service.custom.impl;

import dto.Product;
import dto.Supplier;
import entity.ProductEntity;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao daoType = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        return daoType.save(entity);
    }

    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        List<SupplierEntity> all = supplierDao.findAll();
        for (SupplierEntity supplierEntity:all){
            supplierList.add(new ModelMapper().map(supplierEntity, Supplier.class));
        }
        return supplierList;
    }

    @Override
    public boolean updateSupplierInfo(Supplier supplier, int supplierId) {
        supplier.setSupplierId(supplierId);
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao daoType = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        return daoType.update(entity);
    }

    @Override
    public boolean deleteSupplier(int supplierId) {
        SupplierDao daoType = DaoFactory.getInstance().getDaoType(DaoType.Supplier);
        return daoType.delete(supplierId);
    }
}
