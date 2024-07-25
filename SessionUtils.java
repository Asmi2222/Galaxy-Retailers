package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

public class SessionUtils {
    public static boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("loggedInUser");
            return user != null;
        }
        return false;
    }
}