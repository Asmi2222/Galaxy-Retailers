package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.dbController;
import model.Cart;
import model.OrderDetails;
import model.Orders;
import model.User;
import utils.SessionUtils;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in
        if (!SessionUtils.isUserLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        // Check if the user has the required role (e.g., "Customer")
        if (user == null || !user.getRole().equals("Customer")) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        int userId = user.getUserId();

        // Retrieve cart items for the user
        dbController dbController = new dbController();
        List<Cart> cartItems = dbController.getCartItems(userId);

        if (cartItems.isEmpty()) {
            // Cart is empty, redirect to cart page or display an error message
            response.sendRedirect(request.getContextPath() + "/CartServlet");
            return;
        }

        // Calculate total payable amount
        double totalPayableAmount = 0;
        for (Cart cartItem : cartItems) {
            totalPayableAmount += cartItem.getQuantity() * cartItem.getUnitPrice();
        }

        // Create Orders object
        Orders order = new Orders(0, userId, new Date(), "pending", totalPayableAmount);

        // Insert order into the database
        int orderId = dbController.addOrder(order);

        // Insert order details into the database
        for (Cart cartItem : cartItems) {
            OrderDetails orderDetail = new OrderDetails(orderId, cartItem.getProductNo(), cartItem.getQuantity(),
                    cartItem.getUnitPrice(), cartItem.getQuantity() * cartItem.getUnitPrice());
            dbController.addOrderDetail(orderDetail);
        }

        // Clear the user's cart
        dbController.clearCart(userId);

        // Set order placed attribute
        request.setAttribute("orderPlaced", true);

        // Forward to order-placed.jsp
        request.getRequestDispatcher("order-placed.jsp").forward(request, response);
    }
}