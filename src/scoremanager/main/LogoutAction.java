package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutAction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションを取得
        HttpSession session = request.getSession(false); // false: セッションがない場合に新規作成しない

        if (session != null) {
            // セッションからユーザーデータを削除
            session.removeAttribute("user");  // "user" はセッションに保存されているユーザーデータの属性名
            session.removeAttribute("id");    // 他にも削除したい属性があれば追加

            // セッションを無効にする（セッション全体を終了させる場合）
            session.invalidate();
        }

        // ログアウト後、ログインページへリダイレクト
        response.sendRedirect("logout.jsp");
    }
}
