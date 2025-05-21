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

@WebServlet("/student/update")
public class StudentUpdateAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストパラメータから学生番号を取得
        String no = request.getParameter("no");
        if (no == null || no.isEmpty()) {
            // 学生番号が指定されていない場合は一覧へリダイレクト
            response.sendRedirect(request.getContextPath() + "/student/list");
            return;
        }

        // 学生情報を取得
        Student student = studentDao.findByNo(no);

        // フォームの選択肢データを取得
        List<Integer> enrollmentYears = studentDao.getEnrollmentYears();
        List<String> classList = studentDao.getClassList();

        // リクエスト属性にセット
        request.setAttribute("student", student);
        request.setAttribute("enrollmentYears", enrollmentYears);
        request.setAttribute("classList", classList);

        // 更新画面へフォワード
        request.getRequestDispatcher("/scoremanager/main/student_update.jsp").forward(request, response);
    }
}
