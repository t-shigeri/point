package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    /** 科目コード＋年度・クラス絞り込みで成績を取得 */
    public List<TestListSubject> findBySubject(
            String enrollmentYear,
            String classNum,
            String subjectCd) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection con = getConnection();

        // ベースの SQL
        StringBuilder sb = new StringBuilder(
            "SELECT STUDENT_NO, SUBJECT_CD, NO, POINT, CLASS_NUM " +
            "FROM TEST WHERE SUBJECT_CD = ?");
        // 学生テーブル絞り込み
        if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
            sb.append(" AND STUDENT_NO IN (SELECT NO FROM STUDENT WHERE ENT_YEAR = ?)");
        }
        if (classNum != null && !classNum.isEmpty()) {
            sb.append(" AND CLASS_NUM = ?");
        }
        sb.append(" ORDER BY STUDENT_NO, NO");

        PreparedStatement st = con.prepareStatement(sb.toString());
        int idx = 1;
        st.setString(idx++, subjectCd);
        if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
            st.setInt(idx++, Integer.parseInt(enrollmentYear));
        }
        if (classNum != null && !classNum.isEmpty()) {
            st.setString(idx++, classNum);
        }

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            TestListSubject t = new TestListSubject();
            t.setStudentNo(rs.getString("STUDENT_NO"));
            t.setSubjectCd(rs.getString("SUBJECT_CD"));
            t.setNo(rs.getInt("NO"));
            t.setPoint(rs.getInt("POINT"));
            t.setClassNum(rs.getString("CLASS_NUM"));
            list.add(t);
        }
        rs.close();
        st.close();
        con.close();
        return list;
    }
}
