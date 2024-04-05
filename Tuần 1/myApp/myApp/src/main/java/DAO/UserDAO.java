package DAO;

import Entity.User;
import Mapping.UserMapping;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = ConnectSql.getConnect();
    }

    public User checkUser(String username, String password) {
        try (PreparedStatement statement = connection.prepareStatement("select * from user where user_name = ? and password = ? ");) {
            statement.setString(1, username);
            statement.setString(2, password);
            return new UserMapping().mapping(statement.executeQuery());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public boolean save(User user) {
        try (PreparedStatement statement = connection.prepareStatement("insert into user(user_name,password,role_id,phone,email,address) values(?,?,?,?,?,?)");) {
            ConnectSql.setAutocommit(connection, false);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRoleId());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            if (statement.executeUpdate() != 0) {
                connection.commit();

                return true;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectSql.setAutocommit(connection, true);
        }
        return false;
    }

    public boolean delete(int userId) {
        try (PreparedStatement statement = connection.prepareStatement("delete from user where user_id = ?");) {
            statement.setInt(1, userId);
            if (statement.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công.");
                return true;
            }
            JOptionPane.showMessageDialog(null, "Xóa tài khoản không thành công.", "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean update(User user) {
        try (PreparedStatement statement = connection.prepareStatement("update user set user_name = ? , password = ? ,role_id =? ,phone = ? , email = ? , address = ? where user_id = ?");) {
            ConnectSql.setAutocommit(connection, false);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRoleId());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setShort(7, user.getUserId());
            if (statement.executeUpdate() != 0) {
                connection.commit();
                return true;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectSql.setAutocommit(connection, true);
        }
        return false;
    }

    public User findByUserName(String username) {
        try (PreparedStatement statement = connection.prepareStatement("select * from user where user_name = ?");) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return new UserMapping().mapping(statement.executeQuery());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public User findByUserId(short id) {
        try (PreparedStatement statement = connection.prepareStatement("select * from user where user_id= ? ");) {
            statement.setShort(1, id);
            ResultSet resultSet = statement.executeQuery();
            return new UserMapping().mapping(statement.executeQuery());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
