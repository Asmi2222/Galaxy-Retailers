package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.dbController;
import model.UserModel;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");

        if (userName != null) {
            dbController dbController = new dbController();
            UserModel user = dbController.getUserByUsername(userName);

            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/Pages/Profile.jsp").forward(request, response);
            } else {
                // Handle case when user is not found
            }
        } else {
            // Handle case when username is not found in session
        }
    }
}