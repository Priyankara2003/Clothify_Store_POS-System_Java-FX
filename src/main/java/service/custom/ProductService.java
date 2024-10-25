package service.custom;

import dto.OrderDetails;
import dto.Product;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    ObservableList<Product> getAllProducts();
    boolean updateProductInfo(Product product,int productId);
    boolean deleteProduct(int productId);
    boolean updateStock(List<OrderDetails> orderDetails);
    String getProductName(Integer productId);
}
