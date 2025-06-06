package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

/**
 * 学生情報の新規登録を実行するサーブレット
 */
@WebServlet("/student/createExecute")
public class StudentCreateExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final StudentDao studentDao = new StudentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 文字コード設定
        request.setCharacterEncoding("UTF-8");

        // フォームパラメータ取得
        String enrollmentYear = request.getParameter("enrollmentYear");
        String studentId      = request.getParameter("studentId");
        String name           = request.getParameter("name");
        String classId        = request.getParameter("classId");
        boolean enrolled      = "t".equals(request.getParameter("enrolled"));

        // Studentオブジェクトにセット
        Student student = new Student();
        student.setStudentId(studentId);
        student.setName(name);
        if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
            student.setEnrollmentYear(Integer.parseInt(enrollmentYear));
        }
        student.setClassId(classId);
        student.setEnrolled(enrolled);

        // DBに登録
        studentDao.createStudent(student);

        // 登録完了画面へフォワード
        request.setAttribute("student", student);
        request.getRequestDispatcher("/scoremanager/main/student_create_done.jsp")
               .forward(request, response);
    }
}
