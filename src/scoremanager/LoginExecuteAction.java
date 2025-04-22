package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.User;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        // 入力チェック
        if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("error", "すべてのフィールドを入力してください。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        // 認証処理
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher != null) {
            HttpSession session = req.getSession();
            session.setAttribute("teacher", teacher);

            User user = new User();
            user.setAuthenticated(true);
            session.setAttribute("user", user);

            res.sendRedirect("menu.jsp");
        } else {
            req.setAttribute("error", "IDまたはパスワードが間違っています。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }
}
