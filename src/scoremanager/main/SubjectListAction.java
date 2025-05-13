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
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.findAll();

            request.setAttribute("subjectList", subjectList);
            request.getRequestDispatcher("/scoremanager/main/subject_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // POSTでもGETと同様の処理をする
    }
}
