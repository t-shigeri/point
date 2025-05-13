package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // SubjectDaoのインスタンス化
        SubjectDao subjectDao = new SubjectDao();

        // 全科目の取得
        List<Subject> subjectList = subjectDao.findAll();

        // リクエストスコープにsubjectListを保存
        request.setAttribute("subjectList", subjectList);

        // JSPページにフォワード
        request.getRequestDispatcher("../view/subject_list.jsp").forward(request, response);
    }
}


//package scoremanager.main;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import bean.Subject;
//import dao.SubjectDao;
//
//@WebServlet("/scoremanager/main/subject_list.action")
//public class SubjectListAction extends HttpServlet {
//
//  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//          throws ServletException, IOException {
//      try {
//          // 科目DAOを使って全科目取得
//          SubjectDao subjectDao = new SubjectDao();
//          List<Subject> subjectList = subjectDao.findAll();
//
//          // リクエスト属性に設定してJSPへ転送
//          request.setAttribute("subjectList", subjectList);
//          request.getRequestDispatcher("/WEB-INF/view/subject/list.jsp")
//                 .forward(request, response);
//
//      } catch (Exception e) {
//          e.printStackTrace();
//          throw new ServletException("科目一覧の取得に失敗しました。", e);
//      }
//  }
//}