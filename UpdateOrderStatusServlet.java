package controller.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import utils.OrderUtils;

@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String newStatus = request.getParameter("orderStatus");

            dbController dbController = new dbController();
            Connection connection = dbController.getDbConnection();

            boolean success = OrderUtils.updateOrderStatus(connection, orderId, newStatus);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/OrderServlet");
            } else {
                // Handle error
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}