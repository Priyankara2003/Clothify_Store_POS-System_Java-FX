package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int productId;
    private String supplierId;
    private String productName;
    private String size;
    private String price;
    private String qtyOnHand;
    private String category;
}
