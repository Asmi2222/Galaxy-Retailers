package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.UserModel;
import utils.StringUtils;

@WebServlet("/ProfileUpdateServlet")
public class ProfileUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String userId = request.getParameter("user-Id");
         String userName = request.getParameter("user-name");
         String firstName = request.getParameter("first-name");
         String lastName = request.getParameter("last-name");
         String email = request.getParameter("email");
         String phoneNumber = request.getParameter("phone");
         String address = request.getParameter("address");

         if (!StringUtils.isBlank1(userId) && !StringUtils.isBlank1(userName) && !StringUtils.isBlank1(firstName)
                 && !StringUtils.isBlank1(lastName) && !StringUtils.isBlank1(email)
                 && !StringUtils.isBlank1(phoneNumber) && !StringUtils.isBlank1(address)) {
             UserModel userModel = new UserModel();
             userModel.setUserId(Integer.parseInt(userId));
             userModel.setUserName(userName);
             userModel.setFirstName(firstName);
             userModel.setLastName(lastName);
             userModel.setEmail(email);
             userModel.setPhoneNumber(phoneNumber);
             userModel.setAddress(address);

             try {
 				dbController databaseController = new dbController();
				databaseController.updateUserProfile(userModel);
 			} catch (ClassNotFoundException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
           }
         // Redirect to the same page or any other desired page
         response.sendRedirect(request.getContextPath() + "/Pages/Profile.jsp");
         }
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("user-Id");
        String userName = request.getParameter("user-name");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String address = request.getParameter("address");

        if (!StringUtils.isBlank1(userId) && !StringUtils.isBlank1(userName) && !StringUtils.isBlank1(firstName)
                && !StringUtils.isBlank1(lastName) && !StringUtils.isBlank1(email)
                && !StringUtils.isBlank1(phoneNumber) && !StringUtils.isBlank1(address)) {
            UserModel userModel = new UserModel();
            userModel.setUserId(Integer.parseInt(userId));
            userModel.setUserName(userName);
            userModel.setFirstName(firstName);
            userModel.setLastName(lastName);
            userModel.setEmail(email);
            userModel.setPhoneNumber(phoneNumber);
            userModel.setAddress(address);

            try {
            	dbController databaseController = new dbController();
				databaseController.updateUserProfile(userModel);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        // Redirect to the same page or any other desired page
        response.sendRedirect(request.getContextPath() + "/Profile.jsp");
    }
}