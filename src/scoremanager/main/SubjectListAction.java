package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;

@WebServlet("/scoremanager/main/subject_list.action")
public class SubjectListAction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 学校指定を外し、すべての科目を取得
            SubjectDao dao = new SubjectDao();
            List<Subject> subjectList = dao.findAll(); // findAll() を用意する or filter(null) 等で全件取得

            request.setAttribute("subjectList", subjectList);
            request.getRequestDispatcher("/WEB-INF/view/subject/list.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("科目一覧の取得に失敗しました。");
        }
    }
}
