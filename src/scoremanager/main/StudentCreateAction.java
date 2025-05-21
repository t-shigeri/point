package scoremanager.main;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet("/student_create")
public class StudentCreateAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * GETリクエスト処理 - 新規登録画面を表示する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 学生新規登録画面（student_create.jsp）にフォワード
        request.getRequestDispatcher("/scoremanager/main/student_create.jsp").forward(request, response); // 修正した部分
    }

    /**
     * POSTリクエスト処理 - フォームから送信された学生情報をDBに登録する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームから送信された各パラメータを取得
        String enrollmentYear = request.getParameter("enrollmentYear"); // 入学年度（例: 2023）
        String studentId = request.getParameter("studentId");           // 学生番号（例: A001）
        String name = request.getParameter("name");                     // 氏名（例: 山田太郎）
        String className = request.getParameter("className");           // クラス名（例: 1年A組）
        boolean enrolled = request.getParameter("enrolled") != null;   // チェックボックスがチェックされていれば在学中
        String schoolCd = request.getParameter("schoolCd");             // 学校コード（例: S001）

        // Studentオブジェクトを生成（データベース登録用）
        Student student = new Student(
            studentId,
            name,
            Integer.parseInt(enrollmentYear),
            className,
            enrolled,
            schoolCd
        );

        // DAOを使ってDBに登録処理を実行
        StudentDao studentDao = new StudentDao();
        studentDao.updateStudent(student);

        // 登録完了画面へリダイレクト（リロードで再登録されるのを防ぐ）
        response.sendRedirect(request.getContextPath() + "/student_create_done");
    }
}
