/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Car {
    
    private String carUID;

    /**
     * Get the value of carUID
     *
     * @return the value of carUID
     */
    public String getCarUID() {
        return carUID;
    }

    /**
     * Set the value of carUID
     *
     * @param carUID new value of carUID
     */
    public void setCarUID(String carUID) {
        this.carUID = carUID;
    }

    private String numberPlate;

    /**
     * Get the value of numberPlate
     *
     * @return the value of numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * Set the value of numberPlate
     *
     * @param numberPlate new value of numberPlate
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    private Status status;

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
