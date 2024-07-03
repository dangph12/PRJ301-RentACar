/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public enum OrderStatus {
    CANCELLED(0, "Cancelled"), OPENED(1, "Open"), CLOSED(2, "Closed"), INVALID(3, "Invalid order's status");
    private int key;

    /**
     * Get the value of key
     *
     * @return the value of key
     */
    public int getKey() {
        return key;
    }

    /**
     * Set the value of key
     *
     * @param key new value of key
     */
    public void setKey(int key) {
        this.key = key;
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

    private OrderStatus(int key, String description) {
        this.key = key;
        this.description = description;
    }
}
