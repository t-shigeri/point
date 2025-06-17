package scoremanager.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;

@WebServlet("/test/regist")
public class TestRegistAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TestDao testDao = new TestDao();
    private StudentDao studentDao = new StudentDao();
    private SubjectDao subjectDao = new SubjectDao();


    // GETは検索処理（一覧取得）
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            execute(request, response);
            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // POSTは登録処理
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8"); // 文字化け防止



            int index = 0;
            while (true) {
                String studentNo = request.getParameter("studentList[" + index + "].studentNo");
                String pointStr = request.getParameter("studentList[" + index + "].point");
                if (studentNo == null || pointStr == null) {
                    break; // データがなくなったら終了
                }
                int point = Integer.parseInt(pointStr);

                String enrollmentYear = request.getParameter("f1");
                String classNum = request.getParameter("f2");
                String subjectCd = request.getParameter("f3");
                String noStr = request.getParameter("f4");
                int no = Integer.parseInt(noStr);

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

                String schoolCd = testDao.getSchoolCdByStudentId(studentNo);
                School school = new School();
                school.setCd(schoolCd);
                test.setSchool(school);

                // 登録（insert or update）処理
                testDao.saveOrUpdate(test);

                index++;
            }

            // 登録後は検索結果を再表示（POST-Redirect-GET）
            String redirectUrl = request.getContextPath() + "/test/regist"
                    + "?f1=" + request.getParameter("f1")
                    + "&f2=" + request.getParameter("f2")
                    + "&f3=" + request.getParameter("f3")
                    + "&f4=" + request.getParameter("f4");
            response.sendRedirect(redirectUrl);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // 検索処理の共通部分
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String enrollmentYear = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");
        String no = request.getParameter("f4");

        List<Student> studentList = null;
        if (enrollmentYear != null && !enrollmentYear.isEmpty()
                && classNum != null && !classNum.isEmpty()) {
            // 在籍中の学生だけ取得する場合は"t"を渡す
            studentList = studentDao.findAll(enrollmentYear, classNum, "t");
        }
        request.setAttribute("studentList", studentList);

        if (studentList != null && subjectCd != null && !subjectCd.isEmpty() && no != null && !no.isEmpty()) {
            List<Test> testList = testDao.filter(enrollmentYear, classNum, subjectCd, no);
            Map<String, Integer> scoreMap = new HashMap<>();
            for (Test test : testList) {
                if (test.getStudent() != null) {
                    scoreMap.put(test.getStudent().getStudentId(), test.getPoint());
                }
            }
            // Student に point をセットする (Student クラスに setPoint メソッドを用意してください)
            for (Student s : studentList) {
                Integer point = scoreMap.get(s.getStudentId());
                s.setPoint(point);
            }
        }



        request.setAttribute("enrollmentYears", studentDao.getEnrollmentYears());
        request.setAttribute("classList", studentDao.getClassList());
        request.setAttribute("subjectList", subjectDao.findAll());

        request.setAttribute("countList", java.util.Arrays.asList(1, 2, 3, 4, 5));
    }
}
