package controller.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import model.ProductModel;
import utils.ProductDataSource;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
maxFileSize = 1024 * 1024 * 10,
maxRequestSize = 1024 * 1024 * 50)
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDataSource productDataSource = new ProductDataSource();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// create object and call
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
		String productID = request.getParameter("productID");
		String productName = request.getParameter("productname");
		String productDescription = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		
		Part imagePart = request.getPart("image");

		ProductModel product = new ProductModel(productID, productName, price, stock, productDescription, imagePart);

		try {
			int result = productDataSource.addProduct(product);

			if (result == 1) {
				System.out.println("Added successfully");
				//image upload code
//				C:\Users\Dell\eclipse-workspace\GalaxyRetailers\src\main\webapp\images
				
				// Get the image file name from the student object (assuming it was extracted earlier)
				String fileName = product.getImagePart();

				// Check if a filename exists (not empty or null)
				if (!fileName.isEmpty() && fileName != null) {
				  // Construct the full image save path by combining the directory path and filename
				  String savePath = "C:\\Users\\praje\\eclipse-workspace\\GalaxyRetailers\\src\\main\\webapp\\images\\home images";
				  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
				}

				
				doGet(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
