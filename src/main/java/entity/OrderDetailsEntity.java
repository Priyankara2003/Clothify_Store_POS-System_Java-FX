package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "orderDetails")
public class OrderDetailsEntity {
    @Id
    private Integer orderId;
    @Id
    private Integer productId;
    private Integer qty;
    private Double total;
}
