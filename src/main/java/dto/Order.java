package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Integer orderId;
    private String date;
    private String employeeName;
    private Double netTotal;
    private String paymentMethod;
    private List<OrderDetails> orderDetails;
}
