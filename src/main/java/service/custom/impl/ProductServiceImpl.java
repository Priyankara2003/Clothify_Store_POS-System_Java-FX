package service.custom.impl;

import dto.Product;
import entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.custom.ProductService;
import util.DaoType;

import java.util.List;

public class ProductServiceImpl implements ProductService {


    @Override
    public boolean addProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao daoType = DaoFactory.getInstance().getDaoType(DaoType.Product);
        return daoType.save(entity);
    }

    @Override
    public ObservableList<Product> getAllProducts() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.Product);
        List<ProductEntity> all = productDao.findAll();
        for (ProductEntity productEntity:all){
            productList.add(new ModelMapper().map(productEntity, Product.class));
        }
        return productList;
    }

    @Override
    public boolean updateProductInfo(Product product,int productId) {
        product.setProductId(productId);
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao daoType = DaoFactory.getInstance().getDaoType(DaoType.Product);
        return daoType.update(entity);
    }

    @Override
    public boolean deleteProduct(int productId) {
        ProductDao daoType = DaoFactory.getInstance().getDaoType(DaoType.Product);
        return daoType.delete(productId);
    }
}
