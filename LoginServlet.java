package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.dbController;
import model.LoginModel;
import model.User;
import utils.StringUtils;


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final dbController dbController;

    public LoginServlet() {
        this.dbController = new dbController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username"); 
        String password = request.getParameter("password");
        
     // Create a LoginModel object to hold user credentials
        LoginModel loginModel = new LoginModel(userName, password);
        
        int loginResult = dbController.getLoginInfo(userName,password);
        
        if (loginResult == 1) {
            // Fetch user role from database and redirect accordingly
            User user = dbController.getUserRole(userName);
            
            if (user.getRole() != null) {
            	// Create a new session or get the existing session
                HttpSession session = request.getSession(true);
                
                // Set session timeout to 30 minutes (in seconds)
                session.setMaxInactiveInterval(1800); // 30 minutes
             // Store user information in session attributes
                session.setAttribute("username", userName);
                session.setAttribute("role", user.getRole());
             // Assuming user is successfully authenticated and user information is available
                //User user = new User(userName, userRole);
                // Store user information in session attributes
                
                session.setAttribute("loggedInUser", user);
                

             // Add cookies
                Cookie usernameCookie = new Cookie("username", userName);
                Cookie roleCookie = new Cookie("userRole", user.getRole());
                // Set cookie expiry time (30 minutes)
                usernameCookie.setMaxAge(30 * 60);
                roleCookie.setMaxAge(30 * 60);
                // Add cookies to the response
                response.addCookie(usernameCookie);
                response.addCookie(roleCookie);
                
                if (user.getRole().equals("Admin")) {
                    response.sendRedirect(request.getContextPath() + "/Dashboard");
                } else {
                    response.sendRedirect(request.getContextPath() + "/products");
                    
                }
            } else {
                // Handle null user role
            }
        } else if (loginResult == 0) {
            // Invalid username or password
            request.setAttribute("errorMessage", "Username and password don't match.");
            request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
        } else if (loginResult == -1) {
            // User account doesn't exist
            request.setAttribute("errorMessage", "Username already exists. Please choose a different username.Go back to register again.");
            request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
        }
    }   

        
   
}

