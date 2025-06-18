package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;

@WebServlet("/scoremanager/main/subject_create_done.action")
public class SubjectCreateExecuteAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            String cd = req.getParameter("cd");
            String name = req.getParameter("name");

            HttpSession session = req.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            School school = teacher.getSchool();

            SubjectDao dao = new SubjectDao();

            boolean hasError = false;

            // エラーメッセージチェック
            if (cd == null || cd.trim().isEmpty()) {
                req.setAttribute("cdError", "科目コードを入力してください。");
                hasError = true;
            } else if (cd.length() != 3) {
                req.setAttribute("cdError", "科目コードは3文字で入力してください。");
                hasError = true;
            } else {
                Subject existing = dao.get(cd, school);
                if (existing != null) {
                    req.setAttribute("cdError", "科目コードが重複しています");
                    hasError = true;
                }
            }

            if (name == null || name.trim().isEmpty()) {
                req.setAttribute("nameError", "このフィールドを入力してください。");
                hasError = true;
            }

            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(school);
            req.setAttribute("subject", subject);

            if (hasError) {
                req.getRequestDispatcher("/scoremanager/main/subject_create.jsp").forward(req, resp);
                return;
            }

            boolean success = dao.save(subject);
            req.setAttribute("success", success);
            req.getRequestDispatcher("/scoremanager/main/subject_create_done.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
