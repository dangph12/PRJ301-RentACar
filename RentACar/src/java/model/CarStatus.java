/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public enum CarStatus {
    UNAVAILABLE(0, "Không có sẵn"), AVAILABLE(1, "Có sẵn"), RUNNING(2, "Đang chạy"), BOOKED(3, "Đã đặt"),INVALID(-1, "Invalid status");
    
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

    private CarStatus(int key, String description) {
        this.key = key;
        this.description = description;
    }

}
