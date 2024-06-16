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
    
    public int getUsersCountWithName(String name) {
        
        UserDAO userDAO = new UserDAO();
        int count = 0;
        try {
            ResultSet rs = userDAO.getUsersCountWithName(name);
            while (rs.next()) {                
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    
    public ArrayList<User> pagingUsersWithName(int index, String name) {
        ArrayList<User> allUsers = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        
        try {
             ResultSet rs = userDAO.pagingUsersWithNameResultSet(index, name);
             while (rs.next()) {                
                String userUID = rs.getString("user_uid");
                String fullName = rs.getString("full_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                User user = new User(userUID, fullName, phone, email, address);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }
    
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
                String fullName = rs.getString("full_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                User user = new User(userUID, fullName, phone, email, address);
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

    private String fullName;

    /**
     * Get the value of fullName
     *
     * @return the value of fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the value of fullName
     *
     * @param fullName new value of fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String phone;

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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

    private String address;

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public User(String userUID, String fullName, String phone, String email, String address) {
        this.userUID = userUID;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public User() {
    }

    public static void main(String[] args) {
        User user = new User();
            ArrayList<User> pagingUsers = user.pagingUsersWithName(1, "23");
            int allUsersCount = user.getUsersCountWithName("23");
    }
}
