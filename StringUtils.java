package utils;

public class StringUtils {

    // Database Connection Constants
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/galaxy_retailers";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "";

    // Queries
    public static final String QUERY_ADD_TO_CART = "INSERT INTO cart (UserNo, ProductNo, Quantity) VALUES (?, ?, ?)";
    public static final String QUERY_INSERT_USER = "INSERT INTO user (UserFirstName, UserLastName, UserName, UserAddress, UserEmail, UserPhoneNumber, UserPassword) VALUES (?, ?, ?, ?, ?, ?, ?)";
   
    public static final String QUERY_GET_ALL_USERS = "SELECT * FROM user";

    // Parameter Names
    public static final String PARAM_PRODUCT_NAME = "productName";
    public static final String PARAM_PRICE = "price";
    public static final String PARAM_PRODUCT_NO = "productNo";
    public static final String PARAM_QUANTITY = "quantity";
    public static final String PARAM_USER_NAME = "userName";
    public static final String PARAM_USER_PASSWORD = "userPassword";
    
   

	// End: DB Connection
	public static final String INSERT_USER = "INSERT INTO user (UserName,UserFirstName,UserLastName,UserAddress,UserEmail,UserPhoneNumber,UserPassword,UserRole)"
			+ "VALUES (?,?,?,?,?,?,?,?)";
	public static final String GET_LOGIN_USER = "SELECT UserName, UserPassword from user WHERE UserName = ? AND UserPassword = ?";
	public static final String GET_All_USER = "SELECT * from user ";
	public static final String SERVLET_URL_LOGIN = "/LoginServlet";
	public static final String PRODUCT_DETAIL_PAGE = "/Pages/ProductDetail.jsp";
	public static final String GET_PRODUCTS = "Select * from product WHERE ProductNo=?";
	public static final String URL_INDEX = "/Pages/index.jsp";
    // Database Connection Constants
   
   




    // JSP Routes

  
    public static final String SERVLET_URL_LOGOUT = "/LogoutServlet";
    
    public static final String PAGE_URL_LOGIN = "/Pages/login.jsp";
    public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";

    // JSP Routes

    public static final String PAGE_HOME = "/index.jsp";
    public static final String PAGE_CART = "/cart.jsp";
    public static final String PAGE_ERROR = "/error.jsp";
    
   

    // Servlet Routes
   
    public static final String SERVLET_REGISTER = "/register";
    public static final String SERVLET_ADD_TO_CART = "/addToCart";
    public static final String SERVLET_LOGOUT = "/logout";
    
    public static boolean isBlank(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    
    public static boolean isBlank1(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    
   
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    } 

}
