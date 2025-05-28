package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;

@WebServlet("/test/regist")
public class TestRegistAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TestDao testDao = new TestDao();
    private StudentDao studentDao = new StudentDao();
    private SubjectDao subjectDao = new SubjectDao();

    // GETリクエスト
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            execute(request, response);
            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // POSTリクエスト（必要なら）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            execute(request, response);
            request.getRequestDispatcher("/scremanager/main/test_regist.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }





//    // 実際の処理はここにまとめる
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 検索フォームのパラメータ（セレクトボックスのname属性に合わせてf1～f4）
//        String enrollmentYear = request.getParameter("f1");
//        String classNum = request.getParameter("f2");
//        String subjectCd = request.getParameter("f3");
//        String no = request.getParameter("f4");
//
//        // 検索条件に該当する成績一覧取得
//        List<Test> testList = testDao.filter(enrollmentYear, classNum, subjectCd, no);
//        request.setAttribute("testList", testList);
//
//        // セレクトボックス用データを取得しリクエスト属性にセット
//        List<Integer> enrollmentYears = studentDao.getEnrollmentYears();
//        List<String> classList = studentDao.getClassList();
//        List<Subject> subjectList = subjectDao.findAll();
//        List<Integer> countList = testDao.getTestCounts();
//
//        request.setAttribute("enrollmentYears", enrollmentYears);
//        request.setAttribute("classList", classList);
//        request.setAttribute("subjectList", subjectList);
//        request.setAttribute("countList", countList);
//    }
}
