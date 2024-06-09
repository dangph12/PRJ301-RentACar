/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.sql.ResultSet;
import dal.UserDAO;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class User {
    
    public int getUsersCount() {
        
        UserDAO userDAO = new UserDAO();
        int count = 0;
        try {
            ResultSet rs = userDAO.getUsersCount();
            while (rs.next()) {                
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    
    public ArrayList<User> pagingUsers(int index) {
        ArrayList<User> allUsers = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        
        try {
             ResultSet rs = userDAO.pagingUsersResultSet(index);
             while (rs.next()) {                
                String userUID = rs.getString("user_uid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                User user = new User(userUID, username, email);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }
    
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        
        try {
             ResultSet rs = userDAO.getAllUsersExceptPassword();
             while (rs.next()) {                
                String userUID = rs.getString("user_uid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                User user = new User(userUID, username, email);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }
    
    
    private String userUID;

    /**
     * Get the value of userUID
     *
     * @return the value of userUID
     */
    public String getUserUID() {
        return userUID;
    }

    /**
     * Set the value of userUID
     *
     * @param userUID new value of userUID
     */
    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    private String username;

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
        private String email;

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    private String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userUID, String username, String email, String password) {
        this.userUID = userUID;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String userUID, String username, String email) {
        this.userUID = userUID;
        this.username = username;
        this.email = email;
    }

    public User() {
    }

}
