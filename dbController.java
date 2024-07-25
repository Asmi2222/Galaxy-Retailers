package controller.database;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.Statement;

import model.Cart;
import model.GalaxyRetailersModel;
import model.OrderDetails;
import model.Orders;
import model.Product;
import model.ProductModel;
import model.User;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import model.MessageModel;

//new code added


public class dbController {

    public Connection getDbConnection() throws SQLException{
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/galaxy_retailers";
            String userName = "root";
            String password = "";

            conn = DriverManager.getConnection(url, userName, password);
            }catch(Exception e) {
                e.printStackTrace();
            }
        return conn;
    } 
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection conn = this.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("ProductNo");
                String productName = rs.getString("ProductName");
                String productDescription = rs.getString("ProductDescription");
                double unitPrice = rs.getDouble("UnitPrice");
                int stockLevel = rs.getInt("ProductStockLevel");

                Product product = new Product(productId, productName, productDescription, unitPrice, stockLevel);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;  // This will be an empty list if there are no products
    }
    public Product getProduct(int productId) {
        try (Connection con = getDbConnection()){
            PreparedStatement st = con.prepareStatement(utils.StringUtils.GET_PRODUCTS);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            System.out.println(productId);
            if(rs.next()) {
                Product product1 = new Product();
                System.out.println("product1"+product1);
                product1.setProductId(rs.getInt("ProductNo"));
                product1.setProductName(rs.getString("ProductName"));
                product1.setProductDescription(rs.getString("ProductDescription"));
                product1.setUnitPrice(rs.getInt("UnitPrice"));
                product1.setStockLevel(rs.getInt("ProductStockLevel"));

                return product1;

            }else {
                return null;
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
   
    public int addUser(GalaxyRetailersModel user) {
        try {
                // Check if the username already exists in the database
                if (isUsernameExists(user.getUserName())) {
                    return -2; // Username already exists
                }
                String encryptedPassword = PasswordEncryptionWithAes.encrypt(user.getUserName(), user.getPassword());
                if (encryptedPassword == null) {
                    return -1; // Encryption failed
                }
                user.setPassword(encryptedPassword);
            PreparedStatement st = getDbConnection().prepareStatement("INSERT INTO user (UserName,UserFirstName,UserLastName,UserAddress,UserEmail,UserPhoneNumber,UserPassword,UserRole)"
        			+ "VALUES (?,?,?,?,?,?,?,?)");

            st.setString(1, user.getUserName()); // Corrected method calls
            st.setString(2, user.getFirstName());
            st.setString(3, user.getLastName());
            st.setString(4, user.getAddress());
            st.setString(5, user.getEmail());
            st.setString(6, user.getPhoneNumber());
            st.setString(7, user.getPassword());
            st.setString(8, user.getRole());

            int result = st.executeUpdate();
            if (result > 0) {
				return 1;
			} else {
				return 0;
			}

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
 // Method to check if username already exists
    private boolean isUsernameExists(String username) {
        try {
            Connection con = getDbConnection();
            PreparedStatement st = con.prepareStatement("SELECT UserName FROM user WHERE UserName = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return rs.next(); // Returns true if username exists
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Return false in case of exception
        }
    }
     public int getLoginInfo(String username, String password) {
		try (Connection con = getDbConnection()) {
			PreparedStatement st = con.prepareStatement("SELECT UserName, UserPassword from user WHERE UserName = ?");
			st.setString(1, username);
			
			
			
			ResultSet result = st.executeQuery();
			
			if (result.next()) {
				// User name and password match in the database
				//newcode
                String encryptedPassword = result.getString("UserPassword");
                String decryptedPassword = PasswordEncryptionWithAes.decrypt(encryptedPassword, username);
                if (decryptedPassword == null) {
                    return -2; // Decryption failed
                }
                if (decryptedPassword.equals(password)) {
					return 1;
				} else {
					return 0;
				}
			
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -2;
		}
	}
     public User getUserRole(String username) {
    	    try (Connection con = getDbConnection()) {
    	        PreparedStatement st = con.prepareStatement("SELECT UserRole, UserNo FROM user WHERE UserName = ?");
    	        st.setString(1, username);
    	        ResultSet rs = st.executeQuery();
    	        if (rs.next()) {
    	        	
    	        	User obj = new User(username, rs.getString("UserRole"));
    	        	obj.setUserId(rs.getInt("UserNo"));
    	        	System.out.println(obj.getRole());
    	        	return obj;
    	        }
    	    } catch (SQLException ex) {
    	        ex.printStackTrace(); // Log the exception for debugging
    	    }
    	    return null; // Return null if user role not found
    	}

     public List<Product> searchProducts(String query) {
    	    List<Product> products = new ArrayList<>();
    	    String sql = "SELECT * FROM product WHERE INSTR(ProductName, ?) > 0;";

    	    try (Connection conn = getDbConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
    	        pstmt.setString(1, query); // Don't concatenate '%' here
    	        ResultSet rs = pstmt.executeQuery();

    	        while (rs.next()) {
    	            int productId = rs.getInt("ProductNo");
    	            String productName = rs.getString("ProductName");
    	            double unitPrice = rs.getDouble("UnitPrice");

    	            // Create a Product object and add it to the list
    	            Product product = new Product(productId, productName, unitPrice);
    	            products.add(product);
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return products;
    	}
  
  
     

     // Method to clear the user's cart
     public void clearCart(int userId) {
         String query = "DELETE FROM Cart WHERE UserNo = ?";
         try (Connection conn = getDbConnection();
              PreparedStatement stmt = conn.prepareStatement(query)) {
             stmt.setInt(1, userId);
             stmt.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

     // Method to retrieve cart items for a user
     public List<Cart> getCartItems(int userId) {
         List<Cart> cartItems = new ArrayList<>();
         String query = "SELECT * FROM Cart WHERE UserNo = ?";
         try (Connection conn = getDbConnection();
              PreparedStatement stmt = conn.prepareStatement(query)) {
             stmt.setInt(1, userId);
             try (ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                     int cartNo = rs.getInt("CartNo");
                     int productNo = rs.getInt("ProductNo");
                     int quantity = rs.getInt("Quantity");
                     double unitPrice = rs.getDouble("UnitPrice");
                     int productStockLevel = rs.getInt("ProductStockLevel");
                     String productName = rs.getString("ProductName");
                     Cart cartItem = new Cart(cartNo, userId, productNo, quantity, productStockLevel);
                     cartItem.setProductName(productName);
                     cartItem.setUnitPrice(unitPrice);
                     cartItems.add(cartItem);
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return cartItems;
     }

 
//////
    public static final String Update_Product = "UPDATE product SET productName = ?, "
    		+ "UnitPrice = ?, ProductStockLevel= ?, ProductDescription = ? WHERE ProductNo = ?";
 	
 	public int update(ProductModel product) {
 		try(Connection con = getDbConnection()) {
 			PreparedStatement st = con.prepareStatement(Update_Product);
 			
 			// Set Parameters
 			st.setString(5, product.getProductID());
 			st.setString(1, product.getProductName());
 			st.setInt(2, product.getPrice());
 			st.setInt(3, product.getStock());
 			st.setString(4, product.getProductDescription());
 			
 			int result = st.executeUpdate();
 			return result;
 		}catch(SQLException ex) {
 			ex.printStackTrace();
 			return -1;
 		}
 	}
 	
 	// retrieveing the totalproducts
 	public int getTotalProducts() {
 	    try (Connection conn = getDbConnection()) {
 	        String query = "SELECT COUNT(*) AS total_products FROM product";
 	        PreparedStatement stmt = conn.prepareStatement(query);
 	        ResultSet rs = stmt.executeQuery();

 	        if (rs.next()) {
 	            return rs.getInt("total_products");
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    }
 	    return -1; // Return -1 if an error occurs
 	}

 		    public void saveMessage(MessageModel messageModel) {
 		        String query = "INSERT INTO reviews (message) VALUES (?)";

 		        try (Connection con = getDbConnection()) {
 		             PreparedStatement st = con.prepareStatement(query);

 		            st.setString(1, messageModel.getMessage());
 		            
 		            int result = st.executeUpdate();
 		            return;
 		        } catch (SQLException ex) {
 		            ex.printStackTrace();
 		        }
 		    }
 		    
 		    public  void updateUserProfile(UserModel userModel) throws ClassNotFoundException {
 		        String query = "UPDATE user SET UserName = ?, UserFirstName = ?, UserLastName = ?, UserAddress = ?, UserPhoneNumber = ?, UserEmail = ? WHERE UserNo = ?";

 		        try (Connection connection = getDbConnection()){
 		             PreparedStatement statement = connection.prepareStatement(query);

 		            statement.setString(1, userModel.getUserName());
 		            statement.setString(2, userModel.getFirstName());
 		            statement.setString(3, userModel.getLastName());
 		            statement.setString(4, userModel.getAddress());
 		            statement.setString(5, userModel.getPhoneNumber());
 		            statement.setString(6, userModel.getEmail());
 		            statement.setInt(7, userModel.getUserId());

 		            statement.executeUpdate();

 		        } catch (SQLException e) {
 		            e.printStackTrace();
 		        }
 		    }
 		    
 		   public int authenticateUser(int userId, String password) throws ClassNotFoundException {
 			    String query = "SELECT UserPassword FROM user WHERE UserNo = ?";

 			    try (Connection connection = getDbConnection()) {
 			        PreparedStatement statement = connection.prepareStatement(query);

 			        statement.setInt(1, userId);
 			        ResultSet resultSet = statement.executeQuery();

 			        if (resultSet.next()) {
 			            // User name and password match in the database
 			            String encryptedPassword = resultSet.getString("UserPassword");
 			            String decryptedPassword = PasswordEncryptionWithAes.decrypt1(encryptedPassword, userId);
 			            if (decryptedPassword == null) {
 			                return -2; // Decryption failed
 			            }
 			            if (decryptedPassword.equals(password)) {
 			                // Username and password match
 			                return 1;
 			            } else {
 			                // Username exists but password doesn't match
 			                return 0;
 			            }
 			        } else {
 			            // Username doesn't exist
 			            return -1;
 			        }
 			    } catch (SQLException ex) {
 			        ex.printStackTrace(); // Log the exception for debugging
 			        return -2;
 			    }
 			}

 		  public void updateUserPassword(int userId, String newPassword) throws ClassNotFoundException {
 			    String query = "UPDATE user SET UserPassword = ? WHERE UserNo = ?";

 			    try (Connection connection = getDbConnection()) {
 			        PreparedStatement statement = connection.prepareStatement(query);

 			        // Add debug statement to print the query and parameter values
 			        System.out.println("Update Query: " + query);
 			        System.out.println("UserNo: " + userId);
 			        System.out.println("New Password: " + newPassword);

 			        statement.setString(1, newPassword);
 			        statement.setInt(2, userId);
 			        statement.executeUpdate();

 			    } catch (SQLException e) {
 			        e.printStackTrace();
 			    }
 			}
 		 public int addOrder(Orders order) {
 		    int orderId = 0;
 		    String query = "INSERT INTO orders (userNo, orderDate, orderStatus, totalPayableAmount) VALUES (?, ?, ?, ?)";
 		    try (Connection conn = getDbConnection();
 		         PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
 		        stmt.setInt(1, order.getUserId());
 		        stmt.setTimestamp(2, new java.sql.Timestamp(order.getOrderDate().getTime()));
 		        stmt.setString(3, order.getOrderStatus());
 		        stmt.setDouble(4, order.getTotalPayableAmount());
 		        stmt.executeUpdate();
 		        // Retrieve the generated order ID
 		        ResultSet rs = stmt.getGeneratedKeys();
 		        if (rs.next()) {
 		            orderId = rs.getInt(1);
 		        }
 		    } catch (SQLException e) {
 		        e.printStackTrace();
 		    }
 		    return orderId;
 		}
 		public void addOrderDetail(OrderDetails orderDetail) {
 		    String query = "INSERT INTO order_details (orderNo, productNo, quantity, unit_price, total_price) VALUES (?, ?, ?, ?, ?)";
 		    try (Connection conn = getDbConnection();
 		         PreparedStatement stmt = conn.prepareStatement(query)) {
 		        stmt.setInt(1, orderDetail.getOrderNo());
 		        stmt.setInt(2, orderDetail.getProductNo());
 		        stmt.setInt(3, orderDetail.getQuantity());
 		        stmt.setInt(4, (int) orderDetail.getUnitPrice());
 		        stmt.setInt(5, (int) orderDetail.getTotalPrice());
 		        stmt.executeUpdate();
 		    } catch (SQLException e) {
 		        e.printStackTrace();
 		    }
 		}
 		public UserModel getUserByUsername(String userName) {
 	        UserModel user = null;
 	        try (Connection connection = getDbConnection()) {
 	            String query = "SELECT UserNo, UserName, UserFirstName, UserLastName, UserAddress, UserPhoneNumber, UserEmail FROM user WHERE UserName = ?";
 	            PreparedStatement statement = connection.prepareStatement(query);
 	            statement.setString(1, userName);
 	            ResultSet resultSet = statement.executeQuery();

 	            if (resultSet.next()) {
 	                int userId = resultSet.getInt("UserNo");
 	                String firstName = resultSet.getString("UserFirstName");
 	                String lastName = resultSet.getString("UserLastName");
 	                String address = resultSet.getString("UserAddress");
 	                String phoneNumber = resultSet.getString("UserPhoneNumber");
 	                String email = resultSet.getString("UserEmail");

 	                user = new UserModel(userId, userName, firstName, lastName, address, phoneNumber, email);
 	            }
 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }
 	        return user;
 	    } 



 }

    

     

