package dao;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    
    // REGISTER USER

    public boolean registerUser(User user) {

        
        String normalizedUsername = user.getUsername().toLowerCase();

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, normalizedUsername);  
            ps.setString(2, user.getPassword());   

            ps.executeUpdate();
            return true;

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists. Please choose another.");
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   
    // LOGIN USER
    
    public User loginUser(String username, String password) {

       
        String normalizedUsername = username.toLowerCase();

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, normalizedUsername);  
            ps.setString(2, password);         

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
