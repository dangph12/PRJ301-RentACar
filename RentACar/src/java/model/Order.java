/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Order {

    private String orderUID;

    /**
     * Get the value of orderUID
     *
     * @return the value of orderUID
     */
    public String getOrderUID() {
        return orderUID;
    }

    /**
     * Set the value of orderUID
     *
     * @param orderUID new value of orderUID
     */
    public void setOrderUID(String orderUID) {
        this.orderUID = orderUID;
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

    private String carNumberPlate;

    /**
     * Get the value of carNumberPlate
     *
     * @return the value of carNumberPlate
     */
    public String getCarNumberPlate() {
        return carNumberPlate;
    }

    /**
     * Set the value of carNumberPlate
     *
     * @param carNumberPlate new value of carNumberPlate
     */
    public void setCarNumberPlate(String carNumberPlate) {
        this.carNumberPlate = carNumberPlate;
    }

    private OrderStatus orderStatus;

    /**
     * Get the value of orderStatus
     *
     * @return the value of orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Set the value of orderStatus
     *
     * @param orderStatus new value of orderStatus
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private String receivedDate;

    /**
     * Get the value of receivedDate
     *
     * @return the value of receivedDate
     */
    public String getReceivedDate() {
        return receivedDate;
    }

    /**
     * Set the value of receivedDate
     *
     * @param receivedDate new value of receivedDate
     */
    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    private String returnedDate;

    /**
     * Get the value of returnedDate
     *
     * @return the value of returnedDate
     */
    public String getReturnedDate() {
        return returnedDate;
    }

    /**
     * Set the value of returnedDate
     *
     * @param returnedDate new value of returnedDate
     */
    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    private String createdDate;

    /**
     * Get the value of createdDate
     *
     * @return the value of createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Set the value of createdDate
     *
     * @param createdDate new value of createdDate
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Order() {
    }

    public Order(String orderUID, String userUID, String carNumberPlate, OrderStatus orderStatus, String receivedDate, String returnedDate, String createdDate) {
        this.orderUID = orderUID;
        this.userUID = userUID;
        this.carNumberPlate = carNumberPlate;
        this.orderStatus = orderStatus;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.createdDate = createdDate;
    }
}
