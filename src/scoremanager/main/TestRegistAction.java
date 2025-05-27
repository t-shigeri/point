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
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;

@WebServlet("")
public class TestRegistAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETリクエストで呼ばれた場合の処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッションを取得（無ければnull）
        HttpSession session = request.getSession(false);

        // セッションからユーザ(Teacher)情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        try {
            // TeacherにセットされているSchool情報を取得
            School school = teacher.getSchool();

            // School情報が不完全ならDAOを使ってDBから再取得
            if (school == null || school.getName() == null) {
                SchoolDao schoolDao = new SchoolDao();
                school = schoolDao.get(teacher.getSchool().getCd());
            }

            // SubjectDaoを使い、所属学校の科目一覧を取得
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.filter(school);

            // 取得したデータをリクエスト属性にセット（JSPに渡すため）
            request.setAttribute("teacherName", teacher.getName());
            request.setAttribute("school", school);
            request.setAttribute("subjectList", subjectList);

            // teacherMypage.jspにフォワードして表示
            request.getRequestDispatcher("/test_regist.jsp").forward(request, response);

        } catch (Exception e) {
            // 例外発生時はServletExceptionとしてスローし、エラーページへ
            throw new ServletException(e);
        }
    }
}
