package scoremanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;

public class LoginExecuteAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher == null) {
            request.setAttribute("error", "IDまたはパスワードが確認できませんでした");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("teacher", teacher);
            response.sendRedirect("");
        }
    }
}
