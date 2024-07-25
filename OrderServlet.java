package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.OrderModel;
import utils.OrderUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/OrderServlet" })
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Establish the database connection
            dbController dbController = new dbController();
            Connection connection = dbController.getDbConnection();

            // Retrieve all orders from the database
            List<OrderModel> orders = OrderUtils.getAllOrders(connection);

            // Set the orders as a request attribute
            request.setAttribute("orders", orders);

            // Forward the request to the JSP page
            request.getRequestDispatcher("/Pages/Order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
