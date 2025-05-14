package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher == null) {
            request.setAttribute("error", "IDまたはパスワードが確認できませんでした");
            request.getRequestDispatcher("/scoremanager/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("teacher", teacher);
            response.sendRedirect("menu.jsp");
        }
        return null;
    }
}
