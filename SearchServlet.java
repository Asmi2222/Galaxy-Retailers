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


public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the search query parameter from the request
        String query = request.getParameter("query");
        
        // Perform the search using SQL LIKE operator
        dbController dbController = new dbController();
        List<Product> searchResults = dbController.searchProducts(query);
        
        // Set the search results as a request attribute
        request.setAttribute("searchResults", searchResults);
        
        // Forward the request to the search results JSP page
        request.getRequestDispatcher("/includes/searchResults.jsp").forward(request, response);
    }
}
