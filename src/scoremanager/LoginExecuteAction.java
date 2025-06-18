package scoremanager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;

public class LoginExecuteAction extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームからIDとパスワードを取得
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // DAOでログイン認証
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher == null) {
            // ログイン失敗時
            request.setAttribute("error", "IDまたはパスワードが確認できませんでした");
            RequestDispatcher rd = request.getRequestDispatcher("/scoremanager/login.jsp");
            rd.forward(request, response);
        } else {
            // ログイン成功時はセッションに情報をセット
            HttpSession session = request.getSession();
            session.setAttribute("teacher", teacher);                  // teacherオブジェクト丸ごと
            session.setAttribute("teacherName", teacher.getName());    // 名前だけ
            if (teacher.getSchool() != null) {
                session.setAttribute("schoolName", teacher.getSchool().getName());  // 学校名だけ
            } else {
                session.setAttribute("schoolName", "");
            }

            // メニュー画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/scoremanager/main/menu.jsp");
        }
    }
}
