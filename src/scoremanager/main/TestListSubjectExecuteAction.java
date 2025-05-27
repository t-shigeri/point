package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subjectCd = request.getParameter("subjectCd");

        TestDao dao = new TestDao();
        List<Test> testList = dao.findBySubjectCd(subjectCd);
        request.setAttribute("testList", testList);

        return "/WEB-INF/jsp/test_list_subject.jsp";
    }
}
