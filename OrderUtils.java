package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import model.OrderDetailsModel;
import model.OrderModel;

public class OrderUtils {
    public static List<OrderModel> getAllOrders(Connection connection) {
        List<OrderModel> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM orders";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderModel order = new OrderModel();
                order.setOrderNo(resultSet.getInt("OrderNo"));
                order.setUserNo(resultSet.getInt("UserNo"));
                order.setOrderDate(resultSet.getString("OrderDate"));
                order.setOrderStatus(resultSet.getString("OrderStatus"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public static boolean updateOrderStatus(Connection connection, int orderId, String newStatus) {
        try {
            String query = "UPDATE orders SET OrderStatus = ? WHERE OrderNo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newStatus);
            statement.setInt(2, orderId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
//    public static List<OrderDetailsModel> getOrderDetails(Connection connection, int orderId) {
//        List<OrderDetailsModel> orderDetails = new ArrayList<>();
//        try {
//            String query = "SELECT * FROM order_details WHERE OrderNo = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, orderId);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                OrderDetailsModel orderDetail = new OrderDetailsModel();
//                orderDetail.setOrderId(resultSet.getInt("OrderNo"));
//                orderDetail.setProductId(resultSet.getInt("product_id"));
//                orderDetail.setQuantity(resultSet.getInt("quantity"));
//                orderDetail.setPrice(resultSet.getInt("price"));
//                orderDetail.setTotalPrice(resultSet.getInt("total_price"));
//                orderDetails.add(orderDetail);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return orderDetails;
//    }
}
