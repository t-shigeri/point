package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet("/student/updateExecute")
public class StudentUpdateExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // パラメータ取得
        request.setCharacterEncoding("UTF-8");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String enrollmentYear = request.getParameter("enrollmentYear");
        String classId = request.getParameter("classId");
        String enrolledParam = request.getParameter("enrolled");
        boolean enrolled = (enrolledParam != null);

        // Student オブジェクトにセット
        Student student = new Student();
        student.setStudentId(no);
        student.setName(name);
        if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
            student.setEnrollmentYear(Integer.parseInt(enrollmentYear));
        }
        student.setClassId(classId);
        student.setEnrolled(enrolled);

        // 更新処理
        studentDao.updateStudent(student);

        // 完了画面へフォワード
        request.setAttribute("student", student);
        request.getRequestDispatcher("/scoremanager/main/student_update_done.jsp").forward(request, response);
    }
}
