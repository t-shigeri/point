package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * フロントコントローラ (Front Controller)
 * すべてのリクエストを一元管理し、適切なアクションクラスを実行する
 */
@WebServlet(urlPatterns={"*.action"}) // .actionで終わるURLをこのサーブレットで処理
public class FrontController extends HttpServlet {

    /**
     * POSTリクエストの処理
     */
    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        System.out.println("Frontcontroller!");

        // ★★★ ここでリクエストの文字エンコーディングをUTF-8に指定 ★★★
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            String path = request.getServletPath().substring(1);
            String name = path.replace(".a", "A").replace('/', '.');

            Action action = (Action)Class.forName(name)
                .getDeclaredConstructor().newInstance();

            String url = action.execute(request, response);
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    /**
     * GETリクエストの処理
     */
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        doPost(request, response);
    }
}
