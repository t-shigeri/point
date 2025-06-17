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

@WebServlet("/scoremanager/main/subject_update.action")
public class SubjectUpdateAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String cd = req.getParameter("no");

            HttpSession session = req.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher == null) {
                resp.sendRedirect(req.getContextPath() + "/scoremanager/login.jsp");
                return;
            }
            School school = teacher.getSchool();


            SubjectDao dao = new SubjectDao();

            Subject subject = dao.get(cd, school);
            req.setAttribute("subject", subject);
            req.getRequestDispatcher("/point/scoremanager/main/SubjectUpdateExecute.action").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}

