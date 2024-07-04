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
    
    public void setAvailableCars(ArrayList<Car> cars) {
        try {
            CarDAO.getInstance().setAvailableCars(cars);
        } catch (SQLException e) {
        }
    }
    
    public void setBookedCars(ArrayList<Car> cars) {
        try {
            CarDAO.getInstance().setBookedCars(cars);
        } catch (SQLException e) {
        }
    }
    
    public void insertCarsByOrderUID(String orderUID, ArrayList<Car> cars) {
        try {
            CarDAO.getInstance().insertCarsByOrderUID(orderUID, cars);
        } catch (SQLException e) {
        }
    }

    public ArrayList<Car> getCarsByOrderUID(String orderUID) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            ResultSet carsResultSet = CarDAO.getInstance().getCarsByOrderUID(orderUID);
            while (carsResultSet.next()) {
                String carNumberPlate = carsResultSet.getString("car_number_plate");

                int status = carsResultSet.getInt("status");
                CarStatus carStatus = getCarStatusByKey(status);

                String categoryUID = carsResultSet.getString("category_uid");
                Car car = new Car(carNumberPlate, carStatus, categoryUID);
                cars.add(car);
            }
        } catch (SQLException e) {
        }
        return cars;
    }

    public CarStatus getCarStatusByKey(int key) {
        return switch (key) {
            case 0 ->
                CarStatus.UNAVAILABLE;
            case 1 ->
                CarStatus.AVAILABLE;
            case 2 ->
                CarStatus.RUNNING;
            case 3 ->
                CarStatus.BOOKED;
            default ->
                CarStatus.INVALID;
        };
    }

    public ArrayList<Car> getAvailableCarsForOrder(String categoryUID, int carCount) {
        ArrayList<Car> availableCars = new ArrayList<>();
        try {
            ResultSet availableCarsEachCategory = CarDAO.getInstance().getAvailableCarsEachCategory(categoryUID, carCount);
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

    public int getAvailableCarCountEachCategory(String categoryUID) {
        int availableCarCount = 0;
        try {
            ResultSet availableCarCountEachCategory = CarDAO.getInstance().getAvailableCarCountEachCategory(categoryUID);
            while (availableCarCountEachCategory.next()) {
                availableCarCount = availableCarCountEachCategory.getInt("car_count");
            }
        } catch (SQLException e) {
        }
        return availableCarCount;
    }

    public CarStatus getStatusByKey(int key) {
        for (CarStatus status : CarStatus.values()) {
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
     * @param carStatus new value of status
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
