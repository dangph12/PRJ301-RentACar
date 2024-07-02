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
import model.Order;
import model.User;
import util.Util;

/**
 *
 * @author admin
 */
public class FinishOrders extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            
            String userUID = Util.getInstance().generateUserUID();
            String fullName = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            
            User user = new User(userUID, fullName, phone, email, address);
            userInstance.insertUserToDatabases(user);
            
            Order orderInstance = new Order();
            
            String categoryUID = request.getParameter("selected-category-uid");
            String orderUID = Util.getInstance().generateUserUID();
            
            String count = request.getParameter("car-count");
            int carCount = Integer.parseInt(count);
            
            String date = request.getParameter("received-at");
            Date receivedDate = Date.valueOf(date);
            
            String days = request.getParameter("rentaldays");
            int rentalDays = Integer.parseInt(days);
            Date returnedDate = Date.valueOf(receivedDate.toLocalDate().plusDays(rentalDays));
            
            Date createdDate = Date.valueOf(LocalDate.now());
            
            Order order = new Order(orderUID, userUID, receivedDate, returnedDate, createdDate);
            ArrayList<Order> orders = orderInstance.createOrders(order, categoryUID, carCount);
            
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("view-orders").forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
