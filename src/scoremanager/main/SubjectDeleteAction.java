package scoremanager.main;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;

public class SubjectDeleteAction {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId = request.getParameter("studentId");
        new Subject().delete(studentId);
        return "student/deleteStudentResult.jsp";
    }
}
