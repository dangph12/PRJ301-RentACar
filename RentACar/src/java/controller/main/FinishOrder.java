/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.main;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Bill;
import model.Car;
import model.Category;
import model.Order;
import model.OrderStatus;
import model.User;
import util.Util;

/**
 *
 * @author admin
 */
public class FinishOrder extends HttpServlet {

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
        try {
            Category category = new Category();
            String categoryUID = request.getParameter("selected-category-uid");
            Category selectedCategory = category.getCategoryWithAvailableCarCountByCategoryUID(categoryUID);

            User userInstance = new User();
            String userUID = Util.getInstance().generateUUID();
            User user = createNewUser(request, userUID);
            userInstance.insertUserToDatabases(user);

            Order orderInstance = new Order();
            String orderUID = Util.getInstance().generateUUID().trim();
            Order order = createNewOrder(request, orderUID, user);
            orderInstance.insertOrderToDatabases(order);

            Car carInstance = new Car();
            // for a single category
            
            String count = request.getParameter("car-count");
            int carCount = Integer.parseInt(count);
            ArrayList<Car> cars = carInstance.getAvailableCarsForOrder(categoryUID, carCount);
            for (Car car : cars) {
                carInstance.setBookedCar(car.getCarNumberPlate());
                carInstance.insertCarByOrderUID(order.getOrderUID(), car.getCarNumberPlate());
            }

            Bill billInstance = new Bill();
            Bill bill = createNewBill(request, orderUID);
            billInstance.insertBillToDatabases(bill);
            
            request.setAttribute("orderUID", orderUID);
            Cookie ck = new Cookie("orderUID", orderUID);
            response.addCookie(ck);

            response.sendRedirect("view-order");
        } catch (Exception e) {
            String error = e.getMessage();
            request.setAttribute("error", error);
            String backPage = "show-cars";
            request.setAttribute("backPage", backPage);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    public Bill createNewBill(HttpServletRequest request, String orderUID) {
        String amount = request.getParameter("total-amount");
        int totalAmount = Integer.parseInt(amount);
        String method = request.getParameter("payment-method");
        int paymentMethod = Integer.parseInt(method);
        boolean paid = false;

        int defaultDaysToCancelBill = 7;
        Date cancelledDate = Date.valueOf(LocalDate.now().plusDays(defaultDaysToCancelBill));

        Bill bill = new Bill(orderUID, totalAmount, paymentMethod, paid, cancelledDate);
        return bill;
    }

    public Order createNewOrder(HttpServletRequest request, String orderUID, User user) {

        String date = request.getParameter("received-at");
        Date receivedDate = Date.valueOf(date);
        String days = request.getParameter("rental-days");
        int rentalDays = Integer.parseInt(days);
        Date returnedDate = Date.valueOf(receivedDate.toLocalDate().plusDays(rentalDays));
        Date createdDate = Date.valueOf(LocalDate.now());

        Order order = new Order(orderUID, receivedDate, returnedDate, OrderStatus.OPENED, createdDate, user);
        
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