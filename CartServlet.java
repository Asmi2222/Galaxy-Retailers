package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.dbController;
import model.Cart;
import model.Product;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final dbController dbController;

    public CartServlet() {
        this.dbController = new dbController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action != null && action.equals("delete")) {
            int cartNo = Integer.parseInt(request.getParameter("cartNo"));
            deleteCartItem(cartNo);
        }
        
        // Get cart items and forward to cart.jsp
        List<Cart> cartItems = getCartItems();
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/Pages/cart.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    private void deleteCartItem(int cartNo) {
        try (Connection conn = dbController.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM cart WHERE CartNo = ?")) {
            pstmt.setInt(1, cartNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private List<Cart> getCartItems() {
        List<Cart> cartItems = new ArrayList<>();
        try (Connection conn = dbController.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cart");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int cartNo = rs.getInt("CartNo");
                int productNo = rs.getInt("ProductNo");
                int quantity = rs.getInt("Quantity");
                // Get product details by productNo
                Product product = getProduct(productNo);
                if (product != null) {
                    cartItems.add(new Cart(cartNo, productNo, product.getProductName(), quantity, product.getUnitPrice()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
    
    private Product getProduct(int productNo) {
        try (Connection conn = dbController.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product WHERE ProductNo = ?")) {
            pstmt.setInt(1, productNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int productId = rs.getInt("ProductNo");
                    String productName = rs.getString("ProductName");
                    String productDescription = rs.getString("ProductDescription");
                    double unitPrice = rs.getDouble("UnitPrice");
                    int stockLevel = rs.getInt("ProductStockLevel");
                    return new Product(productId, productName, productDescription, unitPrice, stockLevel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
