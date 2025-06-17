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
    private static final long serialVersionUID = 1L;

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

            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(school);

            SubjectDao dao = new SubjectDao();
            boolean success = dao.save(subject);

            req.setAttribute("success", success);
            req.getRequestDispatcher("/scoremanager/main/subject_create_done.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
