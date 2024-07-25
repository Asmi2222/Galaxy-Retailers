package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.dbController;
import model.User;

public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from request
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        // Assume that you have a way to get the logged-in user's ID
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loggedInUser");
        int userId = user.getUserId();

        dbController db = new dbController();

        try (Connection conn = db.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cart (UserNo, ProductNo, Quantity) VALUES (?, ?, ?)")) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            System.out.println(userId);
			

            // Execute the query
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                response.sendRedirect(request.getContextPath() + "/CartServlet"); // Redirect to cart page
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp"); // Redirect to error page
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}