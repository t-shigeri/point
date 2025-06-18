package scoremanager.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String cd = request.getParameter("cd");
            HttpSession session = request.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            School school = teacher.getSchool();

            SubjectDao dao = new SubjectDao();
            Subject subject = dao.get(cd, school);



            request.setAttribute("subject", subject);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "科目情報の取得に失敗しました。");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/scoremanager/main/subject_update.jsp");
        rd.forward(request, response);
    }
}
