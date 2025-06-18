// LogoutAction.java
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 文字化け防止のためにリクエストの文字エンコーディングを設定
        request.setCharacterEncoding("UTF-8");

        // セッション破棄
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // login.jsp にフォワード（必要ならメッセージ渡すことも可能）
        return "/scoremanager/main/logout.jsp";
    }
}
