package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 学生一覧取得（フィルタなし）
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.findAll(null, null, null);
        request.setAttribute("studentList", studentList);

        // 科目一覧取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.findAll();
        request.setAttribute("subjectList", subjectList);

        return "/WEB-INF/jsp/test_list.jsp";
    }
}
