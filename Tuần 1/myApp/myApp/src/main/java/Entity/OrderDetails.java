package Entity;

import lombok.Data;

@Data
public class OrderDetails {
    private long orderDetailsId;
    private long salePrice;
    private long productCost;
    private int quantity;
    private String productId;
    private long orderId;
}
