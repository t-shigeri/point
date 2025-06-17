package scoremanager.main;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;

public class SubjectUpdateExecuteAction extends HttpServlet{
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        School school = teacher.getSchool();

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        SubjectDao dao = new SubjectDao();
        boolean success = dao.save(subject);

        req.setAttribute("success", success);
        return "/scoremanager/main/subject_update_done.jsp";
    }
}



