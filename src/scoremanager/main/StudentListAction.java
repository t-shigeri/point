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

        // 学生一覧を取得
        List<Student> studentList = studentDao.findAll();

        // JSPに渡す
        request.setAttribute("studentList", studentList);

        // 一覧画面へフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/student/student_list.jsp").forward(request, response);
    }
}
