package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.Product;


public class ProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve the list of products from the database
        dbController dbController = new dbController();
        List<Product> products = dbController.getProducts();
        
        // Set the list of products as a request attribute
        request.setAttribute("products", products);
        
        // Forward the request to the home page (index.jsp)
        request.getRequestDispatcher("/Pages/index.jsp").forward(request, response);
    }
}