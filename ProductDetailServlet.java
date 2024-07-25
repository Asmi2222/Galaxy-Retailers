package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.Product;
import utils.StringUtils;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/ProductDetailServlet" })
public class ProductDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    dbController database = new dbController();
    
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productNo = request.getParameter("productId");
        int productId= Integer.parseInt(productNo);
        System.out.println("ProductID"+productNo);
        if(productNo != null) {
            Product product = database.getProduct(productId);
            if(product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher(StringUtils.PRODUCT_DETAIL_PAGE).forward(request, response);
                return;
            }
        }
        
        response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
    }
    

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}