package com.schoolms.school.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.schoolms.school.model.User;
import com.schoolms.school.util.DBConnection;

public class UserDao {
 
 public boolean addUser(User user) {
     String sql = "INSERT INTO users (username, password, email, role, first_name, last_name, phone, address, date_of_birth) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
     
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setString(1, user.getUsername());
         stmt.setString(2, user.getPassword());
         stmt.setString(3, user.getEmail());
         stmt.setString(4, user.getRole());
         stmt.setString(5, user.getFirstName());
         stmt.setString(6, user.getLastName());
         stmt.setString(7, user.getPhone());
         stmt.setString(8, user.getAddress());
         stmt.setDate(9, user.getDateOfBirth() != null ? new java.sql.Date(user.getDateOfBirth().getTime()) : null);
         
         return stmt.executeUpdate() > 0;
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }
 
 public List<User> getAllUsers() {
     List<User> users = new ArrayList<>();
     String sql = "SELECT * FROM users WHERE is_active = TRUE";
     
     try (Connection conn = DBConnection.getConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(sql)) {
         
         while (rs.next()) {
             User user = new User();
             user.setId(rs.getInt("id"));
             user.setUsername(rs.getString("username"));
             user.setEmail(rs.getString("email"));
             user.setRole(rs.getString("role"));
             user.setFirstName(rs.getString("first_name"));
             user.setLastName(rs.getString("last_name"));
             user.setPhone(rs.getString("phone"));
             user.setAddress(rs.getString("address"));
             user.setDateOfBirth(rs.getDate("date_of_birth"));
             user.setActive(rs.getBoolean("is_active"));
             users.add(user);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return users;
 }
 
 public User getUserById(int id) {
     String sql = "SELECT * FROM users WHERE id = ? AND is_active = TRUE";
     
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setInt(1, id);
         ResultSet rs = stmt.executeQuery();
         
         if (rs.next()) {
             User user = new User();
             user.setId(rs.getInt("id"));
             user.setUsername(rs.getString("username"));
             user.setEmail(rs.getString("email"));
             user.setRole(rs.getString("role"));
             user.setFirstName(rs.getString("first_name"));
             user.setLastName(rs.getString("last_name"));
             user.setPhone(rs.getString("phone"));
             user.setAddress(rs.getString("address"));
             user.setDateOfBirth(rs.getDate("date_of_birth"));
             user.setActive(rs.getBoolean("is_active"));
             return user;
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return null;
 }
 
 public boolean updateUser(User user) {
     String sql = "UPDATE users SET username=?, email=?, role=?, first_name=?, last_name=?, " +
                 "phone=?, address=?, date_of_birth=? WHERE id=?";
     
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setString(1, user.getUsername());
         stmt.setString(2, user.getEmail());
         stmt.setString(3, user.getRole());
         stmt.setString(4, user.getFirstName());
         stmt.setString(5, user.getLastName());
         stmt.setString(6, user.getPhone());
         stmt.setString(7, user.getAddress());
         stmt.setDate(8, user.getDateOfBirth() != null ? new java.sql.Date(user.getDateOfBirth().getTime()) : null);
         stmt.setInt(9, user.getId());
         
         return stmt.executeUpdate() > 0;
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }
 
 public boolean deleteUser(int id) {
     String sql = "UPDATE users SET is_active = FALSE WHERE id = ?";
     
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setInt(1, id);
         return stmt.executeUpdate() > 0;
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }
 
 public User authenticate(String username, String password) {
     String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND is_active = TRUE";
     
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setString(1, username);
         stmt.setString(2, password);
         ResultSet rs = stmt.executeQuery();
         
         if (rs.next()) {
             User user = new User();
             user.setId(rs.getInt("id"));
             user.setUsername(rs.getString("username"));
             user.setEmail(rs.getString("email"));
             user.setRole(rs.getString("role"));
             user.setFirstName(rs.getString("first_name"));
             user.setLastName(rs.getString("last_name"));
             return user;
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return null;
 }
}