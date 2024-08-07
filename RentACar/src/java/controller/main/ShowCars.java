/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.main;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author admin
 */
public class ShowCars extends HttpServlet {

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
            Category categoryInstance = new Category();
            
            ArrayList<Category> categories = categoryInstance.getAllCategories();
            ArrayList<Category> activeCategories = new ArrayList<>();
            ArrayList<Category> inactiveCategories = new ArrayList<>();
            for (Category category : categories) {
                if (category.getCarCount() > 0) {
                    activeCategories.add(category);
                } else {
                    inactiveCategories.add(category);
                }
            }
            
            if (activeCategories.size() == 0) {
                throw new Exception("Hết xe");
            }
            
            request.setAttribute("activeCategories", activeCategories);
            request.setAttribute("inactiveCategories", inactiveCategories);

            int itemsPerRow = 3;
            request.setAttribute("itemsPerRow", itemsPerRow);
            
            request.getRequestDispatcher("show-cars.jsp").forward(request, response);
        } catch (Exception e) {
            String error = e.getMessage();
            request.setAttribute("error", error);
            String backPage = "show-cars";
            request.setAttribute("backPage", backPage);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
