package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontController invoked");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            // 例: /student/logout.action → logout → LogoutAction
            String path = request.getServletPath(); // e.g. /student/logout.action
            String[] parts = path.split("/");
            String lastPart = parts[parts.length - 1]; // logout.action
            String base = lastPart.replace(".action", ""); // logout
            String className = base.substring(0, 1).toUpperCase() + base.substring(1) + "Action"; // LogoutAction

            // パッケージ名を固定 (LogoutActionは scoremanager.main パッケージにあるため)
            String fullClassName = "scoremanager.main." + className;

            Action action = (Action) Class.forName(fullClassName)
                .getDeclaredConstructor().newInstance();

            String url = action.execute(request, response);
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
