package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // ✅ 修正: teacher から school を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            throw new Exception("ログインセッションがありません。");
        }
        School school = teacher.getSchool();

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "このフィールドを入力してください。");
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setSchool(school);
            subject.setName("");

            request.setAttribute("subject", subject);
            return "/scoremanager/main/subject_update.jsp";
        }

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);  // null ではなく確実にセット！

        SubjectDao dao = new SubjectDao();
        dao.save(subject);

        request.setAttribute("subject", subject);
        return "/scoremanager/main/subject_update_done.jsp";
    }
}
