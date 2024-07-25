package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import utils.StringUtils;
import model.MessageModel;

@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	    	
        String message = request.getParameter("message");

        StringUtils stringUtils = new StringUtils(); // Create an instance of StringUtils
        if (!stringUtils.isBlank(message)) {
            MessageModel messageModel = new MessageModel();
            messageModel.setMessage(message);
            dbController databaseController = new dbController();
			databaseController.saveMessage(messageModel);
        }

        // Redirect to the same page or any other desired page
        response.sendRedirect(request.getContextPath() + "/Pages/About.jsp");
    }
}