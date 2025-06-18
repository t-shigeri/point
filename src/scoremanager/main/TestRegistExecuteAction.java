package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.TestDao;

@WebServlet("/test/registExecute")
public class TestRegistExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TestDao testDao = new TestDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            String classNum = request.getParameter("f2");
            String subjectCd = request.getParameter("f3");
            String noStr = request.getParameter("f4");
            int no = Integer.parseInt(noStr);

            int index = 0;
            while (true) {
                String studentNo = request.getParameter("studentList[" + index + "].studentNo");
                String pointStr = request.getParameter("studentList[" + index + "].point");
                if (studentNo == null || pointStr == null) break;

                int point = Integer.parseInt(pointStr);

                Test test = new Test();

                Student student = new Student();
                student.setStudentId(studentNo);
                test.setStudent(student);

                test.setClassNum(classNum);

                Subject subject = new Subject();
                subject.setCd(subjectCd);
                test.setSubject(subject);

                test.setNo(no);
                test.setPoint(point);

                // 必要に応じてschoolCdをセット
                String schoolCd = testDao.getSchoolCdByStudentId(studentNo);
                School school = new School();
                school.setCd(schoolCd);
                test.setSchool(school);

                testDao.saveOrUpdate(test);
                index++;
            }

            request.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

