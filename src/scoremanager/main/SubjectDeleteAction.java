package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;

@WebServlet("/scoremanager/main/subject_delete.action")
public class SubjectDeleteAction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cd = request.getParameter("cd");
        String schoolCd = request.getParameter("schoolCd");
        if(cd == null || cd.isEmpty() || schoolCd == null || schoolCd.isEmpty()){
            request.setAttribute("errorMessage", "パラメータが不足しています。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            School school = new School();
            school.setCd(schoolCd);

            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setSchool(school);

            request.setAttribute("subject", subject);
            request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
