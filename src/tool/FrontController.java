package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scoremanager.LoginAction;
import scoremanager.LoginExecuteAction;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("action");

        Action action = null;
        switch (actionName) {
            case "login":
                action = new LoginAction();
                break;
            case "login_execute":
                action = new LoginExecuteAction();
                break;
            default:
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                return;
        }

        try {
            action.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
