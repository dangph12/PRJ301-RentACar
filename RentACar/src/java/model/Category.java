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
        Car carInstance = new Car();
        ArrayList<Category> allCategories = new ArrayList<>();

        try {
            ResultSet categoryResultSet = CategoryDAO.getInstance().getAllCategories();
            while (categoryResultSet.next()) {
                String categoryUID = categoryResultSet.getString("category_uid");
                String title = categoryResultSet.getString("title");
                String description = categoryResultSet.getNString("description");
                int numberOfSeats = categoryResultSet.getInt("number_of_seats");
                int unitPrice = categoryResultSet.getInt("unit_price");
                String image = categoryResultSet.getString("image");
                int availableCarCount = carInstance.getAvailableCarCountEachCategory(categoryUID);
                Category category = new Category(categoryUID, title, description, numberOfSeats, unitPrice, image, availableCarCount);
                allCategories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allCategories;
    }

    public Category getCategoryWithAvailableCarCount(String categoryUID) {
        Category category = null;
        try {
            ResultSet categoryResultSet = CategoryDAO.getInstance().getCategoryByUID(categoryUID);
            while (categoryResultSet.next()) {
                String title = categoryResultSet.getString("title");
                String description = categoryResultSet.getNString("description");
                int numberOfSeats = categoryResultSet.getInt("number_of_seats");
                int unitPrice = categoryResultSet.getInt("unit_price");
                String image = categoryResultSet.getString("image");
                category = new Category(categoryUID, title, description, numberOfSeats, unitPrice, image);
            }
        } catch (SQLException e) {
        }

        Car carInstance = new Car();
        int availableCarCount = carInstance.getAvailableCarCountEachCategory(categoryUID);
        category.setCarCount(availableCarCount);
        return category;
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

    private int carCount;

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public Category() {
    }

    public Category(String categoryUID, String title, String description, int numberOfSeats, int unitPrice, String image, int carCount) {
        this.categoryUID = categoryUID;
        this.title = title;
        this.description = description;
        this.numberOfSeats = numberOfSeats;
        this.unitPrice = unitPrice;
        this.image = image;
        this.carCount = carCount;
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
