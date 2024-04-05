package Service;

import DAO.UserDAO;
import Entity.User;

import javax.swing.*;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public User checkUser(String username, String password) {
        return userDAO.checkUser(username, password);
    }

    public boolean save(User user) {
        if (user.getUserName() == null ||
                user.getUserName().equals("") ||
                user.getPassword() == null ||
                user.getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên hoặc Mật khẩu không được để trống", "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return userDAO.save(user);
    }

    public User findByName(String name) {
        return userDAO.findByUserName(name);
    }
    public User findById(short id ){ return userDAO.findByUserId(id);}
}
