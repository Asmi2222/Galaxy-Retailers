package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.GalaxyRetailersModel;
import utils.StringUtils;



public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final dbController dbController;

    public RegisterServlet() {
        this.dbController = new dbController();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	
		
		String userName = request.getParameter("userName");
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String address = request.getParameter("address");
	    String email = request.getParameter("email");
	    String phoneNumber = request.getParameter("phoneNumber");
	    String password = request.getParameter("password");
	    String confirmPassword = request.getParameter("confirmPassword"); // New parameter for confirm password

        if (!password.equals(confirmPassword)) {
            // Password and confirm password don't match, set error message and forward back to register page
            request.setAttribute("errorMessage", "Password and confirm password do not match. GO back to register again");
            request.getRequestDispatcher("/Pages/register.jsp").forward(request, response);
            return;
        }
	    String userRole = email.endsWith("@galaxyretailers.com") ? "Admin" : "Customer";
	    
		GalaxyRetailersModel user = new GalaxyRetailersModel(
				userName,
				firstName,
				lastName,
				address,
				email,
				phoneNumber,
				password,
				userRole);
				
		// Call DBController to register the student
		int result = dbController.addUser(user);

		if (result == 1) {
	        // User added successfully, redirect to login page
	        response.sendRedirect(request.getContextPath() + "/Pages/login.jsp");
	    } else if (result == -2) {
	        // Username already exists, display error message
	        request.setAttribute("errorMessage", "Username already exists. Please choose a different username.Go back to register again.");
	        request.getRequestDispatcher("/Pages/register.jsp").forward(request, response);
	    } else {
	        // Other errors occurred, handle them accordingly
	        System.out.println("Failed to add user.");
	    }
	}
}