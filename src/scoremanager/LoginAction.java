package scoremanager;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;
@WebServlet("/scoremanager/main/login.action")
public class LoginAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // login.jspを表示するために、リクエストディスパッチャを設定
        RequestDispatcher rd = request.getRequestDispatcher("../login.jsp");
        rd.forward(request, response);  // login.jspにリクエストを転送
    }
}
