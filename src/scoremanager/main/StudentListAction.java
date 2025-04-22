package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet("/student/list")
public class StudentListAction extends HttpServlet {

    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フィルタリングパラメータの取得
        String enrollmentYear = request.getParameter("f1");
        String classId = request.getParameter("f2");
        String isEnrolled = request.getParameter("f3");

        // 学生一覧を取得（フィルタリングが必要な場合）
        List<Student> studentList = studentDao.findAll(enrollmentYear, classId, isEnrolled);

        // 年度やクラスの選択肢を取得する
        List<Integer> enrollmentYears = studentDao.getEnrollmentYears();
        List<String> classList = studentDao.getClassList();

        // リクエスト属性にデータをセット
        request.setAttribute("studentList", studentList);
        request.setAttribute("enrollmentYears", enrollmentYears);
        request.setAttribute("classList", classList);

        // 学生一覧画面へフォワード
        request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);
    }
}
