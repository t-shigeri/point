package tool;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// フロントコントローラパターンを実装したサーブレット
public class FrontController extends HttpServlet {

    // GETリクエストを処理
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res); // 共通処理に委譲
    }

    // POSTリクエストを処理
    @Override
   public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res); // 共通処理に委譲
    }

    // GET/POST 共通のリクエスト処理
    public void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // リクエストパラメータ "action" を取得（例: ?action=login）
        String action = req.getParameter("action");
        String view = null;

        try {
            // action によって表示するビューを分岐
            switch (action) {
                case "login":
                    view = "/login.jsp";     // ログイン画面へ
                    break;
                case "register":
                    view = "/register.jsp";  // 登録画面へ
                    break;
                default:
                    view = "/error.jsp";     // 不明なアクションの場合はエラー画面
            }

            // JSP にフォワードして画面を表示
            RequestDispatcher dispatcher = req.getRequestDispatcher(view);
            dispatcher.forward(req, res);
        } catch (Exception e) {
            // 例外発生時は ServletException にラップしてスロー
            throw new ServletException(e);
        }
    }
}
