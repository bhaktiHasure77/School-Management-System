package com.schoolms.school.service;

import java.util.List;

import com.schoolms.school.dao.UserDao;
import com.schoolms.school.model.User;

public class UserService {
    private UserDao userDAO;
    
    public UserService() {
        this.userDAO = new UserDao();
    }
    
    public boolean registerUser(User user) {
        return userDAO.addUser(user);
    }
    
    public User loginUser(String username, String password) {
        return userDAO.authenticate(username, password);
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
    
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
    
    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }
}
