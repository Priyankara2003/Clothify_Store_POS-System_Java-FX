package dto;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {
    private Integer productId;
    private String productName;
    private String size;
    private String category;
    private String price;
    private SimpleDoubleProperty total = new SimpleDoubleProperty();
    private SimpleIntegerProperty qty = new SimpleIntegerProperty();

    public int getQty() {
        return qty.get();
    }

    public void setQty(int quantity) {
        this.qty.set(quantity);
    }

    public SimpleIntegerProperty qtyProperty() {
        return qty;
    }

    public double getTotal() {
        return total.get();
    }

    public void setTotal(double price) {
        this.total.set(price);
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }
}
