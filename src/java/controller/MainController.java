/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * MainController - A servlet that handles basic arithmetic calculations
 * This servlet processes form data from a calculator web page and performs
 * addition, subtraction, multiplication, and division operations.
 * 
 * @author qnhat
 */
@WebServlet(name = "MainController", urlPatterns = {"/b2controller"})
public class MainController extends HttpServlet {
    
    // Constants for error messages
    private static final String ERROR_DIVIDE_BY_ZERO = "Please enter n2 other than 0";
    private static final String ERROR_INVALID_NUMBER = "Please enter a valid number!!";

    /**
     * Process HTTP requests (not used in this calculator application)
     * This method is kept for compatibility but redirects to doGet method
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles HTTP GET requests for calculator operations
     * Processes form data, performs calculations, and forwards to JSP page
     * 
     * @param request servlet request containing form parameters
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type to HTML with UTF-8 encoding
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Get parameters from the form
        String num1_raw = request.getParameter("num1");
        String num2_raw = request.getParameter("num2");
        String phepTinh = request.getParameter("phepTinh");
        
        // Initialize variables for calculation result and error message
        double result = 0;
        String error = "";
        
        // Check if all parameters are provided
        if (num1_raw != null && num2_raw != null && phepTinh != null) {
            try {
                // Convert string parameters to double numbers
                double n1 = Double.parseDouble(num1_raw);
                double n2 = Double.parseDouble(num2_raw);
                
                // Perform calculation based on selected operation
                switch (phepTinh) {
                    case "+":
                        result = n1 + n2;  // Addition
                        break;
                    case "-":
                        result = n1 - n2;  // Subtraction
                        break;
                    case "x":
                        result = n1 * n2;  // Multiplication
                        break;
                    case ":":
                        if (n2 != 0) {
                            result = n1 / n2;  // Division
                        } else {
                            error = ERROR_DIVIDE_BY_ZERO;  // Division by zero error
                        }
                        break;
                }
            } catch (Exception e) {
                // Handle invalid number format error
                error = ERROR_INVALID_NUMBER;
            }
        }
        
        // Set attributes to pass data to JSP page
        request.setAttribute("num1", num1_raw);
        request.setAttribute("num2", num2_raw);
        request.setAttribute("phepTinh", phepTinh);
        request.setAttribute("result", result);
        request.setAttribute("error", error);
        
        // Forward request to JSP page for display
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests
     * Redirects to processRequest method for compatibility
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
     * Returns a short description of the servlet
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Calculator Servlet - Handles basic arithmetic operations";
    }

}
