package scoremanager.main;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;

@WebServlet("/scoremanager/main/subject_delete_execute.action")
public class SubjectDeleteExecuteAction extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // パラメータから値を取得
            String cd = request.getParameter("cd");
            String schoolCd = request.getParameter("schoolCd");

            if (cd == null || cd.isEmpty() || schoolCd == null || schoolCd.isEmpty()) {
                request.setAttribute("errorMessage", "パラメータが不足しています。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            // Subjectインスタンスを作成
            School school = new School();
            school.setCd(schoolCd);

            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setSchool(school);

            // DAOで削除実行
            SubjectDao subjectDao = new SubjectDao();
            boolean result = subjectDao.delete(subject);

            if (result) {
                response.sendRedirect("/point/scoremanager/main/subject_delete_done.jsp");
            } else {
                request.setAttribute("errorMessage", "削除に失敗しました。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
