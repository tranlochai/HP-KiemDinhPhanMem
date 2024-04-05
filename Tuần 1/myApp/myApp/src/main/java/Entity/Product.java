package Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product {

    private String productId;
    private String productName;
    private String productImage;
    private long productPrice;
    private long productCost;
    private String productUnit;
    private Date createDate;
    private Date modifiedDate;
}
