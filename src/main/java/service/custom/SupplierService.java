package service.custom;

import dto.Supplier;
import javafx.collections.ObservableList;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    ObservableList<Supplier> getAllSuppliers();
    boolean updateSupplierInfo(Supplier supplier,int supplierId);
    boolean deleteSupplier(int supplierId);
}
