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

    public int getCarsCountWithTitle(String title) throws Exception {

        int count = 0;
        try {
            ResultSet rs = CarDAO.getInstance().getCarsCountWithTitle(title);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return count;
    }

    public int getCarsCount() throws Exception {

        int count = 0;
        try {
            ResultSet rs = CarDAO.getInstance().getCarsCount();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return count;
    }

    public ArrayList<Car> pagingCarsWithTitle(int index, String title) throws Exception {
        Category categoryInstance = new Category();
        ArrayList<Car> cars = new ArrayList<>();
        try {
            ResultSet carsResultSet = CarDAO.getInstance().pagingCarsWithTitle(index, title);
            while (carsResultSet.next()) {
                String carNumberPlate = carsResultSet.getString("car_number_plate");

                int status = carsResultSet.getInt("status");
                CarStatus carStatus = getCarStatusByKey(status);

                String categoryUID = carsResultSet.getString("category_uid");
                Category category = categoryInstance.getCategoryByCategoryUID(categoryUID);

                Car car = new Car(carNumberPlate, carStatus, category);
                cars.add(car);
            }
        } catch (Exception e) {
            throw e;
        }
        return cars;
    }

    public ArrayList<Car> pagingCars(int index) throws Exception {
        Category categoryInstance = new Category();
        ArrayList<Car> cars = new ArrayList<>();
        try {
            ResultSet carsResultSet = CarDAO.getInstance().pagingCars(index);
            while (carsResultSet.next()) {
                String carNumberPlate = carsResultSet.getString("car_number_plate");

                int status = carsResultSet.getInt("status");
                CarStatus carStatus = getCarStatusByKey(status);

                String categoryUID = carsResultSet.getString("category_uid");
                Category category = categoryInstance.getCategoryByCategoryUID(categoryUID);

                Car car = new Car(carNumberPlate, carStatus, category);
                cars.add(car);

            }
        } catch (SQLException e) {
            throw e;
        }
        return cars;
    }

    public void setUnavailableCar(String carNumberPlate) throws Exception {
        try {
            CarDAO.getInstance().setUnavailableCar(carNumberPlate);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void setRunningCar(String carNumberPlate) throws Exception {
        try {
            CarDAO.getInstance().setRunningCar(carNumberPlate);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void setAvailableCar(String carNumberPlate) throws Exception {
        try {
            CarDAO.getInstance().setAvailableCar(carNumberPlate);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void setBookedCar(String carNumberPlate) throws Exception {
        try {
            CarDAO.getInstance().setBookedCar(carNumberPlate);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void insertCarByOrderUID(String orderUID, String carNumberPlate) throws Exception {
        try {
            CarDAO.getInstance().insertCarByOrderUID(orderUID, carNumberPlate);
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Car> getCarsByOrderUID(String orderUID) throws Exception {
        Category categoryInstance = new Category();
        ArrayList<Car> cars = new ArrayList<>();
        try {
            ResultSet carsResultSet = CarDAO.getInstance().getCarsByOrderUID(orderUID);
            while (carsResultSet.next()) {
                String carNumberPlate = carsResultSet.getString("car_number_plate");

                int status = carsResultSet.getInt("status");
                CarStatus carStatus = getCarStatusByKey(status);

                String categoryUID = carsResultSet.getString("category_uid");
                Category category = categoryInstance.getCategoryByCategoryUID(categoryUID);

                Car car = new Car(carNumberPlate, carStatus, category);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw e;
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

    public ArrayList<Car> getAvailableCarsForOrder(String categoryUID, int carCount) throws Exception {
        Category categoryInstance = new Category();
        ArrayList<Car> availableCars = new ArrayList<>();
        
        try {
            Category category = categoryInstance.getCategoryByCategoryUID(categoryUID);
            ResultSet availableCarsEachCategory = CarDAO.getInstance().getAvailableCarsEachCategory(categoryUID, carCount);
            while (availableCarsEachCategory.next()) {
                String carNumberPlate = availableCarsEachCategory.getString("car_number_plate");
                CarStatus carStatus = CarStatus.AVAILABLE;
                Car car = new Car(carNumberPlate, carStatus, category);
                availableCars.add(car);
            }
        } catch (Exception e) {
            throw e;
        }
        return availableCars;
    }

    public int getAvailableCarCountEachCategory(String categoryUID) throws Exception {
        int availableCarCount = 0;
        try {
            ResultSet availableCarCountEachCategory = CarDAO.getInstance().getAvailableCarCountEachCategory(categoryUID);
            while (availableCarCountEachCategory.next()) {
                availableCarCount = availableCarCountEachCategory.getInt("car_count");
            }
        } catch (Exception e) {
            throw e;
        }
        return availableCarCount;
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

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Car() {
    }

    public Car(String carNumberPlate, CarStatus carStatus, Category category) {
        this.carNumberPlate = carNumberPlate;
        this.carStatus = carStatus;
        this.category = category;
    }
}
