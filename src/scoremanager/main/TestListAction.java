package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.StudentDao;
import dao.SubjectDao;

@WebServlet("/test/list")
public class TestListAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = execute(request, response);
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 入学年度・クラス番号リスト取得
        StudentDao studentDao = new StudentDao();
        request.setAttribute("enrollmentYears", studentDao.getEnrollmentYears());
        request.setAttribute("classList",       studentDao.getClassList());

        // 科目リスト取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.findAll();
        request.setAttribute("subjectList", subjectList);

        // 検索画面 JSP にフォワード
        return "/scoremanager/main/test_list.jsp";
    }
}
