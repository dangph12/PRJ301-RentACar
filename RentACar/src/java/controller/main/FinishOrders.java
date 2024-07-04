/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.main;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Bill;
import model.Car;
import model.Order;
import model.OrderStatus;
import model.User;
import util.Util;

/**
 *
 * @author admin
 */
public class FinishOrders extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            User userInstance = new User();
            String userUID = Util.getInstance().generateUUID();
            User user = createNewUser(request, userUID);
            userInstance.insertUserToDatabases(user);

            Order orderInstance = new Order();
            String orderUID = Util.getInstance().generateUUID();
            Order order = createNewOrder(request, orderUID, userUID);
            orderInstance.insertOrderToDatabases(order);
            
            Car carInstance = new Car();
            ArrayList<Car> cars = carInstance.getAvailableCarsForOrder(order.getCategoryUID(), order.getCarCount());
            carInstance.setBookedCars(cars); //
            carInstance.insertCarsByOrderUID(order.getOrderUID(), cars); // need to change name
            

            Bill billInstance = new Bill();
            Bill bill = createNewBill(request, orderUID);
            billInstance.insertBillToDatabases(bill);

            request.setAttribute("userUID", userUID);
            request.getRequestDispatcher("view-orders").forward(request, response);
        }
    }
    
    public Bill createNewBill(HttpServletRequest request, String orderUID) {
       String amount = request.getParameter("total-amount");
       int totalAmount = Integer.parseInt(amount);
       String method = request.getParameter("payment-method");
       int paymentMethod = Integer.parseInt(method);
       boolean paid = false;
       String date = request.getParameter("received-at");
       Date receivedDate = Date.valueOf(date);
       
       Bill bill = new Bill(orderUID, totalAmount, paymentMethod, paid, receivedDate);
       return bill;
    }
    
    public Order createNewOrder(HttpServletRequest request, String orderUID, String userUID) {
        String categoryUID = request.getParameter("selected-category-uid");
        String count = request.getParameter("car-count");
        int carCount = Integer.parseInt(count);
        String date = request.getParameter("received-at");
        Date receivedDate = Date.valueOf(date);
        String days = request.getParameter("rentaldays");
        int rentalDays = Integer.parseInt(days);
        Date returnedDate = Date.valueOf(receivedDate.toLocalDate().plusDays(rentalDays));
        Date createdDate = Date.valueOf(LocalDate.now());

        Order order = new Order(orderUID, userUID, categoryUID, carCount, receivedDate, returnedDate, OrderStatus.OPENED, createdDate);

        return order;
    }

    public User createNewUser(HttpServletRequest request, String userUID) {
        String fullName = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        User user = new User(userUID, fullName, phone, email, address);

        return user;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
