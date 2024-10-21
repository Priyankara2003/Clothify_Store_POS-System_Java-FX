package repository;

import repository.custom.impl.ProductDaoImpl;
import repository.custom.impl.SupplierDaoImpl;
import util.DaoType;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case Product: return (T) new ProductDaoImpl();
            case Supplier: return (T) new SupplierDaoImpl();
        }
        return null;
    }
}
