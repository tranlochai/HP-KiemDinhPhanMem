package Entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private short UserId;
    private String userName;  // unique
    private String password;
    private int roleId;
    private String address;
    private String email;
    private String phone;
    private Date createDate;
}
