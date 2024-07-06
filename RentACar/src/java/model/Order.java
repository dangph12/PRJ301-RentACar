/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.OrderDAO;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Order {
    
    public int getOrdersCountWithName(String name) {
        
        int count = 0;
        try {
            ResultSet rs = OrderDAO.getInstance().getOrdersCountWithName(name);
            while (rs.next()) {                
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    
    public int getOrdersCount() {
        
        int count = 0;
        try {
            ResultSet rs = OrderDAO.getInstance().getOrdersCount();
            while (rs.next()) {                
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    
    public ArrayList<Order> pagingOrdersWithName(int index, String name) {
        Car carInstance = new Car();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet rs = OrderDAO.getInstance().pagingOrdersWithName(index, name);
            while (rs.next()) {                
                String orderUID = rs.getString("order_uid");
                String fullName = rs.getString("full_name");
                Date receivedDate = rs.getDate("received_at");
                Date returnedDate = rs.getDate("returned_at");

                int status = rs.getInt("status");
                OrderStatus orderStatus = getOrderStatusByKey(status);
                
                ArrayList<Car> cars = carInstance.getCarsForDashBoardByOrderUID(orderUID);
                boolean paid = rs.getBoolean("is_paid");
                
                Order order = new Order(orderUID, receivedDate, returnedDate, orderStatus, cars, fullName, paid);
                
            }
        } catch (SQLException e) {
            System.out.println("");
        }
        return orders;
    }
    
    public ArrayList<Order> pagingOrders(int index) {
        Car carInstance = new Car();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet rs = OrderDAO.getInstance().pagingOrders(index);
            while (rs.next()) {                
                String orderUID = rs.getString("order_uid");
                String fullName = rs.getString("full_name");
                Date receivedDate = rs.getDate("received_at");
                Date returnedDate = rs.getDate("returned_at");

                int status = rs.getInt("status");
                OrderStatus orderStatus = getOrderStatusByKey(status);
                
                ArrayList<Car> cars = carInstance.getCarsForDashBoardByOrderUID(orderUID);
                boolean paid = rs.getBoolean("is_paid");
                
                Order order = new Order(orderUID, receivedDate, returnedDate, orderStatus, cars, fullName, paid);
                
            }
        } catch (SQLException e) {
            System.out.println("");
        }
        return orders;
    }

    public void cancelOrderByOrderUID(String orderUID) {
        try {
            OrderDAO.getInstance().cancelOrderByOrderUID(orderUID);
        } catch (SQLException e) {
        }
    }

    public ArrayList<Order> getOrdersByUserUID(String userUID) {
        Car carInstance = new Car();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet ordersByUserUID = OrderDAO.getInstance().getOrdersByUserUID(userUID);
            while (ordersByUserUID.next()) {

                String orderUID = ordersByUserUID.getString("order_uid");
                Date receivedDate = ordersByUserUID.getDate("received_at");
                Date returnedDate = ordersByUserUID.getDate("returned_at");

                int status = ordersByUserUID.getInt("status");
                OrderStatus orderStatus = getOrderStatusByKey(status);

                Date createdDate = ordersByUserUID.getDate("created_at");
                ArrayList<Car> cars = carInstance.getCarsByOrderUID(orderUID); // get car by orderuid;

                Order order = new Order(orderUID, userUID, receivedDate, returnedDate, orderStatus, createdDate, cars);
                orders.add(order);

            }
        } catch (SQLException e) {
        }
        return orders;
    }

    public OrderStatus getOrderStatusByKey(int key) {
        return switch (key) {
            case 0 ->
                OrderStatus.CANCELLED;
            case 1 ->
                OrderStatus.OPENED;
            case 2 ->
                OrderStatus.CLOSED;
            default ->
                OrderStatus.INVALID;
        };
    }

    public void insertOrderToDatabases(Order order) {
        try {
            OrderDAO.getInstance().insertOrderToDatabases(order);
        } catch (SQLException e) {
            System.out.println("");
        }
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

    private Date receivedDate;

    /**
     * Get the value of receivedDate
     *
     * @return the value of receivedDate
     */
    public Date getReceivedDate() {
        return receivedDate;
    }

    /**
     * Set the value of receivedDate
     *
     * @param receivedDate new value of receivedDate
     */
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    private Date returnedDate;

    /**
     * Get the value of returnedDate
     *
     * @return the value of returnedDate
     */
    public Date getReturnedDate() {
        return returnedDate;
    }

    /**
     * Set the value of returnedDate
     *
     * @param returnedDate new value of returnedDate
     */
    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
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

    private ArrayList<Car> cars;

    /**
     * Get the value of cars
     *
     * @return the value of cars
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

    /**
     * Set the value of cars
     *
     * @param cars new value of cars
     */
    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public Order() {
    }

    public Order(String orderUID, String userUID, Date receivedDate, Date returnedDate, OrderStatus orderStatus, Date createdDate) {
        this.orderUID = orderUID;
        this.userUID = userUID;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
    }

    public Order(String orderUID, String userUID, Date receivedDate, Date returnedDate, OrderStatus orderStatus, Date createdDate, ArrayList<Car> cars) {
        this.orderUID = orderUID;
        this.userUID = userUID;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
        this.cars = cars;
    }

    private String fullName;

    /**
     * Get the value of fullName
     *
     * @return the value of fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the value of fullName
     *
     * @param fullName new value of fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Order(String orderUID, Date receivedDate, Date returnedDate, OrderStatus orderStatus, ArrayList<Car> cars, String fullName, boolean paid) {
        this.orderUID = orderUID;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.orderStatus = orderStatus;
        this.cars = cars;
        this.fullName = fullName;
        this.paid = paid;
    }

    
}
