package Mapping;

import Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapping {
    public User mapping(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getShort("user_id"));
            user.setUserName(resultSet.getNString("user_name"));
            user.setPassword(resultSet.getNString("password"));
            user.setRoleId(resultSet.getInt("role_id"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setAddress(resultSet.getString("address"));
            user.setCreateDate(resultSet.getDate("createDate"));
            return user;
        }
        return null;
    }
}
