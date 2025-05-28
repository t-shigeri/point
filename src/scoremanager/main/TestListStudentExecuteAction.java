package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListStudent;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentNo = request.getParameter("studentNo");
        List<TestListStudent> testList =
            new TestListStudentDao().findByStudentNo(studentNo);
        request.setAttribute("testList", testList);
        return "/scoremanager/main/test_list_student.jsp";
    }
}
