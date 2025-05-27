package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentNo = request.getParameter("studentNo");

        TestDao dao = new TestDao();
        List<Test> testList = dao.findByStudentNo(studentNo);
        request.setAttribute("testList", testList);

        return "/WEB-INF/jsp/test_list_student.jsp";
    }
}
