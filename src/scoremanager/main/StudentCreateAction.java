package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet("/student_create")
public class StudentCreateAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/student_create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enrollmentYear = request.getParameter("enrollmentYear");
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String className = request.getParameter("className");
        boolean enrolled = Boolean.parseBoolean(request.getParameter("enrolled"));
        String schoolCd = request.getParameter("schoolCd"); // SCHOOL_CD をフォームから取得

        Student student = new Student(studentId, name, Integer.parseInt(enrollmentYear), className, enrolled, schoolCd);
        StudentDao studentDAO = new StudentDao();
        studentDAO.createStudent(student);

        response.sendRedirect("student_create_done.jsp");
    }
}
