package scoremanager.main;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDao;

@WebServlet("/subjectlist.action")
public class SubjectListAction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("school");

        if (school == null) {
            response.sendRedirect("/book/chapter24/login-in.jsp");
            return;
        }

        try {
            SubjectDao dao = new SubjectDao();
            List<Subject> subjectList = dao.filter(school);

            request.setAttribute("subjectList", subjectList);
            request.getRequestDispatcher("/WEB-INF/view/subject/list.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("科目一覧の取得に失敗しました。");
        }
    }
}
