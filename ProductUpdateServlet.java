package controller.servlets;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.dbController;
import model.ProductModel;
import utils.ProductDataSource;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductUpdateServlet" })
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	dbController dbController = new dbController();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			String productID = request.getParameter("id");

			ProductDataSource obj = new ProductDataSource();

			ProductModel product = obj.updateProduct(productID);

			request.setAttribute("productInfo", product);

			request.getRequestDispatcher("Pages/Update.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ProductID = request.getParameter("id");
		String ProductName = request.getParameter("productName");
		String ProductDescription = request.getParameter("description");
		int Price = Integer.parseInt(request.getParameter("price"));
		int Stock = Integer.parseInt(request.getParameter("stock"));

		ProductModel product = new ProductModel();

		product.setProductID(ProductID);
		product.setProductName(ProductName);
		product.setProductDescription(ProductDescription);
		product.setPrice(Price);
		product.setStock(Stock);

		int result = dbController.update(product);
		if (result > 0) {
			System.out.println("Product Update Successfully.");
			ProductDataSource productDS = new ProductDataSource();
			List<ProductModel> listOfProduct = productDS.getAllProductsInfo();
			request.setAttribute("listOfProduct", listOfProduct);

			// TODO Auto-generated method stub
			request.getRequestDispatcher("/Pages/Product.jsp").forward(request, response);

		} else {
			System.out.println("Product Update Failed!!");
		}

	}

}
