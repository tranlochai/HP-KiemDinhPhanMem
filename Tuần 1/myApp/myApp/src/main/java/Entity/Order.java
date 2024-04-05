package Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private long orderId;
    private Date createDate;
    private short userId;
    private String description;
    private double discount;
    private long cost_incurred;
}
