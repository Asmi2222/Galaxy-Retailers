package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;

import com.mysql.cj.xdevapi.Statement;

import controller.database.dbController;
import model.ProductModel;

public class ProductDataSource {
	dbController dbObj = new dbController();

	public int addProduct(ProductModel product) throws SQLException {
		Connection conn = dbObj.getDbConnection();

		PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM product WHERE productNo = ?");
		statement.setString(1, product.getProductID());

		ResultSet rs = statement.executeQuery();
		rs.next();
		if (rs.getInt(1) > 0) {
			return -1;
		}
		statement = conn.prepareStatement(
				"INSERT INTO product (ProductNo, productname, UnitPrice, ProductStockLevel, ProductDescription, imagePart) " + "VALUES (?,?,?,?,?,?)");

		statement.setString(1, product.getProductID());
		statement.setString(2, product.getProductName());
		statement.setInt(3, product.getPrice());
		statement.setInt(4, product.getStock());
		statement.setString(5, product.getProductDescription());
		statement.setString(6, product.getImagePart());

		statement.executeUpdate();
		

		return 1;
	}

	// arraylist
	public List<ProductModel> getAllProductsInfo() {
		dbController dbConnection = new dbController();
		try {

			PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT * FROM product");
			ResultSet result = stmt.executeQuery();

			List<ProductModel> products = new ArrayList<ProductModel>();

			while (result.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(result.getString("ProductNo"));
				product.setProductName(result.getString("ProductName"));
				product.setPrice(result.getInt("UnitPrice"));
				product.setStock(result.getInt("ProductStockLevel"));
				product.setImagePart(result.getString("imagePart"));
				
				products.add(product);
			}
			return products;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int deleteProduct(String product_id) throws SQLException {
		Connection conn = dbObj.getDbConnection();

		PreparedStatement statement = conn.prepareStatement("DELETE FROM product WHERE ProductNo = ?");
		statement.setString(1, product_id);

		int result = statement.executeUpdate();
		if (result > 0) {
			return 1;
		}
		return -1;
	}
	
	public ProductModel updateProduct(String product_id) throws SQLException {
		Connection conn = dbObj.getDbConnection();
		
//		PreparedStatement statement = conn.prepareStatement("Update FROM product WHERE ProductNo = ?");
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM product WHERE ProductNo=?");
		statement.setString(1, product_id);
		ResultSet rs = statement.executeQuery();
		ProductModel product =new ProductModel();
		if(rs.next())
		{
			product.setProductID(rs.getString("ProductNo"));
			product.setProductName(rs.getString("ProductName"));
			product.setPrice(rs.getInt("UnitPrice"));
			
			product.setStock(rs.getInt("ProductStockLevel"));
			product.setProductDescription(rs.getString("ProductDescription"));
		}
		return product;
	}

	public static int getTotalProducts() {
		return 0;
		// TODO Auto-generated method stub
		
	}
}
