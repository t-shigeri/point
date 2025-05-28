package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    /** 学生番号で成績を取得 （TEST テーブル） */
    public List<TestListStudent> findByStudentNo(String studentNo) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection con = getConnection();
        String sql =
            "SELECT STUDENT_NO, SUBJECT_CD, NO, POINT, CLASS_NUM " +
            "FROM TEST WHERE STUDENT_NO = ? ORDER BY NO";  // テスト回順
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, studentNo);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            TestListStudent t = new TestListStudent();
            t.setStudentNo   (rs.getString("STUDENT_NO"));
            t.setSubjectCd   (rs.getString("SUBJECT_CD"));
            t.setNo          (rs.getInt   ("NO"));
            t.setPoint       (rs.getInt   ("POINT"));
            t.setClassNum    (rs.getString("CLASS_NUM"));
            list.add(t);
        }
        rs.close();
        st.close();
        con.close();
        return list;
    }
}
