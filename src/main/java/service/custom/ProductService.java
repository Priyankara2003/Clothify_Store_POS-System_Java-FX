package service.custom;

import dto.Product;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    ObservableList<Product> getAllProducts();
}
