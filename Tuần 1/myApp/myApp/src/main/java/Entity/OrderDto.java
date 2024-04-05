package Entity;

import lombok.Data;

@Data
public class OrderDto extends  Order {
    private long totalPrice;
}
