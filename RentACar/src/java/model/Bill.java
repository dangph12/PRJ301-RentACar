/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.BillDAO;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class Bill {
    
    public void insertBillToDatabases(Bill bill) {
        BillDAO.getInstance().insertBill(bill);
    }

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

    private int totalAmount;

    /**
     * Get the value of totalAmount
     *
     * @return the value of totalAmount
     */
    public int getTotalAmount() {
        return totalAmount;
    }

    /**
     * Set the value of totalAmount
     *
     * @param totalAmount new value of totalAmount
     */
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    private int paymentMethod;

    /**
     * Get the value of paymentMethod
     *
     * @return the value of paymentMethod
     */
    public int getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set the value of paymentMethod
     *
     * @param paymentMethod new value of paymentMethod
     */
    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private boolean paid;

    /**
     * Get the value of paid
     *
     * @return the value of paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Set the value of paid
     *
     * @param paid new value of paid
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    private Date createdDate;

    /**
     * Get the value of createdDate
     *
     * @return the value of createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Set the value of createdDate
     *
     * @param createdDate new value of createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    private Date cancelledDate;

    /**
     * Get the value of cancelledDate
     *
     * @return the value of cancelledDate
     */
    public Date getCancelledDate() {
        return cancelledDate;
    }

    /**
     * Set the value of cancelledDate
     *
     * @param cancelledDate new value of cancelledDate
     */
    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public Bill() {
    }

    public Bill(String orderUID, int totalAmount, int paymentMethod, boolean paid, Date createdDate) {
        this.orderUID = orderUID;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paid = paid;
        this.createdDate = createdDate;
    }

}
