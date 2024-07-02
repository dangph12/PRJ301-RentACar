/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.CarDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Car {

    public ArrayList<Car> getAvailableCarsEachCategory(String categoryUID) {
        ArrayList<Car> availableCars = new ArrayList<>();
        try {
            ResultSet availableCarsEachCategory = CarDAO.getInstance().getAvailableCarsEachCategory(categoryUID);
            while (availableCarsEachCategory.next()) {
                String carNumberPlate = availableCarsEachCategory.getString("car_number_plate");
                CarStatus carStatus = CarStatus.AVAILABLE;
                Car car = new Car(carNumberPlate, carStatus, categoryUID);
                availableCars.add(car);
            }
        } catch (SQLException e) {
        }
        return availableCars;
    }
    
    public CarStatus getStatusByKey(int key) {
        for (CarStatus status: CarStatus.values()) {
            if (status.getKey() == key) {
                return status;
            }
        }
        return CarStatus.INVALID;
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

    private CarStatus carStatus;

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public CarStatus getCarStatus() {
        return carStatus;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
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

    public Car() {
    }

    public Car(String carNumberPlate, CarStatus carStatus, String categoryUID) {
        this.carNumberPlate = carNumberPlate;
        this.carStatus = carStatus;
        this.categoryUID = categoryUID;
    }

}
