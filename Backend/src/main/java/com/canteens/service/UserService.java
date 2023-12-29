package main.java.com.canteens.service;

import main.java.com.canteens.dao.UserDAO;
import main.java.com.canteens.model.User;
import java.util.List;


public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean createUser(User user) {
        return userDAO.insertUser(user);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}
