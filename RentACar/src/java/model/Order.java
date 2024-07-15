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

    public int getOrdersCountWithName(String name) throws Exception {

        int count = 0;
        try {
            ResultSet rs = OrderDAO.getInstance().getOrdersCountWithName(name);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return count;
    }

    public int getOrdersCount() throws Exception {

        int count = 0;
        try {
            ResultSet rs = OrderDAO.getInstance().getOrdersCount();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return count;
    }

    public ArrayList<Order> pagingOrdersWithName(int index, String name) throws Exception {
        User userInstance = new User();
        Bill billInstance = new Bill();
        Car carInstance = new Car();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet rs = OrderDAO.getInstance().pagingOrdersWithName(index, name);
            while (rs.next()) {
                String orderUID = rs.getString("order_uid");
                String userUID = rs.getString("user_uid");
                User user = userInstance.getUserByUserUID(userUID);
                
                Date receivedDate = rs.getDate("received_at");
                Date returnedDate = rs.getDate("returned_at");

                int status = rs.getInt("status");
                OrderStatus orderStatus = getOrderStatusByKey(status);

                Date createdDate = rs.getDate("created_at");
                ArrayList<Car> cars = carInstance.getCarsByOrderUID(orderUID);
                Bill bill = billInstance.getBillByOrderUID(orderUID);

                Order order = new Order(orderUID, receivedDate, returnedDate, orderStatus, createdDate, cars, user, bill);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw e;
        }
        return orders;
    }

    public ArrayList<Order> pagingOrders(int index) throws Exception {
        User userInstance = new User();
        Bill billInstance = new Bill();
        Car carInstance = new Car();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet rs = OrderDAO.getInstance().pagingOrders(index);
            while (rs.next()) {
                String orderUID = rs.getString("order_uid");
                String userUID = rs.getString("user_uid");
                User user = userInstance.getUserByUserUID(userUID);
                
                Date receivedDate = rs.getDate("received_at");
                Date returnedDate = rs.getDate("returned_at");

                int status = rs.getInt("status");
                OrderStatus orderStatus = getOrderStatusByKey(status);

                Date createdDate = rs.getDate("created_at");
                ArrayList<Car> cars = carInstance.getCarsByOrderUID(orderUID);
                Bill bill = billInstance.getBillByOrderUID(orderUID);

                Order order = new Order(orderUID, receivedDate, returnedDate, orderStatus, createdDate, cars, user, bill);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw e;
        }
        return orders;
    }

    public void cancelOrderByOrderUID(String orderUID) throws Exception {
        try {
            OrderDAO.getInstance().cancelOrderByOrderUID(orderUID);
        } catch (SQLException e) {
            throw e;
        }
    }

    public Order getOrderByOrderUID(String orderUID) throws Exception {
        User userInstance = new User();
        Bill billInstance = new Bill();
        Car carInstance = new Car();
        Order order = null;
        try {
            ResultSet ordersByUserUID = OrderDAO.getInstance().getOrderByOrderUID(orderUID);
            while (ordersByUserUID.next()) {
                String userUID = ordersByUserUID.getString("user_uid");
                User user = userInstance.getUserByUserUID(userUID);
                
                Date receivedDate = ordersByUserUID.getDate("received_at");
                Date returnedDate = ordersByUserUID.getDate("returned_at");

                int status = ordersByUserUID.getInt("status");
                OrderStatus orderStatus = getOrderStatusByKey(status);

                Date createdDate = ordersByUserUID.getDate("created_at");
                ArrayList<Car> cars = carInstance.getCarsByOrderUID(orderUID);
                Bill bill = billInstance.getBillByOrderUID(orderUID);

                order = new Order(orderUID, receivedDate, returnedDate, orderStatus, createdDate, cars, user, bill);
            }
        } catch (SQLException e) {
            throw e;
        }
        return order;
    }

    public ArrayList<Order> getOrdersByUserUID(String userUID) throws Exception{
        User userInstance = new User();
        User user = userInstance.getUserByUserUID(userUID);
        
        Car carInstance = new Car();
        Bill billInstance = new Bill();
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
                ArrayList<Car> cars = carInstance.getCarsByOrderUID(orderUID);
                Bill bill = billInstance.getBillByOrderUID(orderUID);
                
                Order order = new Order(orderUID, receivedDate, returnedDate, orderStatus, createdDate, cars, user, bill);
                
                orders.add(order);

            }
        } catch (SQLException e) {
            throw e;
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

    public void insertOrderToDatabases(Order order) throws Exception {
        try {
            OrderDAO.getInstance().insertOrderToDatabases(order);
        } catch (SQLException e) {
            throw e;
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
    
    private User user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

        private Bill bill;

    /**
     * Get the value of bill
     *
     * @return the value of bill
     */
    public Bill getBill() {
        return bill;
    }

    /**
     * Set the value of bill
     *
     * @param bill new value of bill
     */
    public void setBill(Bill bill) {
        this.bill = bill;
    }


    public Order() {
    }

    public Order(String orderUID, Date receivedDate, Date returnedDate, OrderStatus orderStatus, Date createdDate, ArrayList<Car> cars, User user, Bill bill) {
        this.orderUID = orderUID;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
        this.cars = cars;
        this.user = user;
        this.bill = bill;
    }

    public Order(String orderUID, Date receivedDate, Date returnedDate, OrderStatus orderStatus, Date createdDate) {
        this.orderUID = orderUID;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
    }

    public Order(String orderUID, Date receivedDate, Date returnedDate, OrderStatus orderStatus, Date createdDate, ArrayList<Car> cars) {
        this.orderUID = orderUID;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
        this.cars = cars;
    }

    public Order(String orderUID, Date receivedDate, Date returnedDate, OrderStatus orderStatus, Date createdDate, User user) {
        this.orderUID = orderUID;
        this.receivedDate = receivedDate;
        this.returnedDate = returnedDate;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
        this.user = user;
    }
    
}
