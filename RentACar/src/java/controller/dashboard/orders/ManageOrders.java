/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.dashboard.orders;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Order;

/**
 *
 * @author admin
 */
public class ManageOrders extends HttpServlet {
   
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
            ArrayList<Order> pagingOrders = null;
            int allOrdersCount = 0;
            Order orderInstance = new Order();

            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            int index = Integer.parseInt(page);

            String search = request.getParameter("search");
            if (search == null) {
                pagingOrders = orderInstance.pagingOrders(index);
                allOrdersCount = orderInstance.getOrdersCount();
            } else {
                pagingOrders = orderInstance.pagingOrdersWithName(index, search);
                allOrdersCount = orderInstance.getOrdersCountWithName(search);
            }

            int endPage = 0;
            if (allOrdersCount % 7 == 0) {
                endPage = allOrdersCount / 7;
            } else {
                endPage = (allOrdersCount / 7) + 1;
            }

            request.setAttribute("pagingOrders", pagingOrders);
            request.setAttribute("pagingOrdersCount", pagingOrders.size());
            request.setAttribute("allOrdersCount", allOrdersCount);
            request.setAttribute("search", search);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("manage-orders.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("");
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
