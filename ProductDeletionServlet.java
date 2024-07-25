package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.ProductModel;
import utils.ProductDataSource;

/**
 * Servlet implementation class ProductDeletionServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductDeletionServlet" })
public class ProductDeletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	dbController dbObj = new dbController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDeletionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDataSource productDS = new ProductDataSource();
		List<ProductModel> listOfProduct = productDS.getAllProductsInfo();
		request.setAttribute("listOfProduct", listOfProduct);

		// TODO Auto-generated method stub
		request.getRequestDispatcher("/Pages/Product.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDelete(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");

		ProductDataSource productDelete = new ProductDataSource();
		try {
			int result = productDelete.deleteProduct(id);

			if (result == 1) {
				System.out.println("DELETED successfully");
				doGet(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
