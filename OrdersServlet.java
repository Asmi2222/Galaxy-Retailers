package controller.servlets;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Orders;
import controller.database.dbController;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve userId from the request
        String userIdStr = request.getParameter("userId");
        if (userIdStr == null || userIdStr.isEmpty()) {
            // Handle case where userId parameter is missing or empty
            // You can redirect the user to an error page or handle it as appropriate
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "userId parameter is missing or empty");
            return;
        }

        int userId = Integer.parseInt(userIdStr);

        // Retrieve other order details from the request
        // Example: You can retrieve product details, quantity, etc.

        // Create an Orders object with the retrieved details
        Orders order = new Orders(0, userId, new Date(), "pending", 0.0); // Adjust the parameters according to your Orders class constructor

        // Insert the order into the database using dbController
        dbController db = new dbController();
        int orderId = db.addOrder(order);

        // Handle successful order insertion
        if (orderId > 0) {
            // Redirect the user to the order history page
            response.sendRedirect(request.getContextPath() + "/orderHistory.jsp");
        } else {
            // Handle case where order insertion failed
            // You can redirect the user to an error page or handle it as appropriate
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to place order");
        }
    }
}
