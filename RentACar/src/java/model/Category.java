/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.CategoryDAO;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Category {

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> allCategories = new ArrayList<>();

        try {
            ResultSet rs = CategoryDAO.getInstance().getAllCategories();
            while (rs.next()) {
                String categoryUID = rs.getString("category_uid");
                String title = rs.getString("title");
                String description = rs.getNString("description");
                int numberOfSeats = rs.getInt("number_of_seats");
                int unitPrice = rs.getInt("unit_price");
                String image = rs.getString("image");
                Category category = new Category(categoryUID, title, description, numberOfSeats, unitPrice, image);
                allCategories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allCategories;
    }

    private String categoryUID;

    /**
     * Get the value of categoryUID
     *
     * @return the value of categoryUID
     */
    public String getCategoryUID() {
        return categoryUID;
    }

    /**
     * Set the value of categoryUID
     *
     * @param categoryUID new value of categoryUID
     */
    public void setCategoryUID(String categoryUID) {
        this.categoryUID = categoryUID;
    }

    private String title;

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    private String description;

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private int numberOfSeats;

    /**
     * Get the value of numberOfSeats
     *
     * @return the value of numberOfSeats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Set the value of numberOfSeats
     *
     * @param numberOfSeats new value of numberOfSeats
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    private int unitPrice;

    /**
     * Get the value of unitPrice
     *
     * @return the value of unitPrice
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    /**
     * Set the value of unitPrice
     *
     * @param unitPrice new value of unitPrice
     */
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    private String image;

    /**
     * Get the value of image
     *
     * @return the value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the value of image
     *
     * @param image new value of image
     */
    public void setImage(String image) {
        this.image = image;
    }

    private ArrayList<Car> activeCars;

    /**
     * Get the value of activeCars
     *
     * @return the value of activeCars
     */
    public ArrayList<Car> getActiveCars() {
        return activeCars;
    }

    /**
     * Set the value of activeCars
     *
     * @param activeCars new value of activeCars
     */
    public void setActiveCars(ArrayList<Car> activeCars) {
        this.activeCars = activeCars;
    }

    public Category() {
    }

    public Category(String categoryUID, String title, String description, int numberOfSeats, int unitPrice, String image) {
        this.categoryUID = categoryUID;
        this.title = title;
        this.description = description;
        this.numberOfSeats = numberOfSeats;
        this.unitPrice = unitPrice;
        this.image = image;
    }

    public static void main(String[] args) {
        Category category = new Category();
        ArrayList<Category> categories = category.getAllCategories();
    }

}
