package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    /** 学生番号で検索 */
    public List<Test> findByStudentNo(String studentNo) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM "
                   + "FROM TEST WHERE STUDENT_NO = ? ORDER BY NO";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, studentNo);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Test t = new Test();
            t.setStudentNo(rs.getString("STUDENT_NO"));
            t.setSubjectCd(rs.getString("SUBJECT_CD"));
            t.setSchoolCd(rs.getString("SCHOOL_CD"));
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

    /** 科目コードで検索 */
    public List<Test> findBySubjectCd(String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM "
                   + "FROM TEST WHERE SUBJECT_CD = ? ORDER BY STUDENT_NO";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, subjectCd);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Test t = new Test();
            t.setStudentNo(rs.getString("STUDENT_NO"));
            t.setSubjectCd(rs.getString("SUBJECT_CD"));
            t.setSchoolCd(rs.getString("SCHOOL_CD"));
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
