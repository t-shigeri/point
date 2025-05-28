package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListSubject;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // フォームの name 属性に合わせてパラメータ取得
        String enrollmentYear = request.getParameter("enrollmentYear");
        String classNum       = request.getParameter("classNum");
        String subjectCd      = request.getParameter("subjectCd");

        List<TestListSubject> testList =
            new TestListSubjectDao().findBySubject(enrollmentYear, classNum, subjectCd);
        request.setAttribute("testList", testList);
        return "/scoremanager/main/test_list_subject.jsp";
    }
}
