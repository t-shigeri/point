package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    // 全件検索
    public List<Test> findAll() throws Exception {
        List<Test> list = new ArrayList<>();

        Connection con = getConnection();
        String sql = "SELECT * FROM TEST";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test test = new Test();

            Student student = new Student();
            student.setStudentId(rs.getString("STUDENT_NO"));
            test.setStudent(student);

            Subject subject = new Subject();
            subject.setCd(rs.getString("SUBJECT_CD"));
            test.setSubject(subject);

            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD"));
            test.setSchool(school);

            test.setNo(rs.getInt("NO"));
            test.setPoint(rs.getInt("POINT"));
            test.setClassNum(rs.getString("CLASS_NUM"));

            list.add(test);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 1件登録または更新
    public boolean save(Test test) throws Exception {
        Connection con = getConnection();

        String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) "
                   + "KEY (STUDENT_NO, SUBJECT_CD, NO) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setString(3, test.getSchoolCd().getCd());
        st.setInt(4, test.getNo());
        st.setInt(5, test.getPoint());
        st.setString(6, test.getClassNum());

        int result = st.executeUpdate();
        st.close();
        con.close();

        return result > 0;
    }

    // 削除
    public boolean delete(Test test) throws Exception {
        Connection con = getConnection();

        String sql = "DELETE FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setInt(3, test.getNo());

        int result = st.executeUpdate();
        st.close();
        con.close();

        return result > 0;
    }
}

//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import bean.Test;
//
//public class TestDao extends Dao {
//
//    /** 学生番号で検索 */
//    public List<Test> findByStudentNo(String studentNo) throws Exception {
//        List<Test> list = new ArrayList<>();
//        Connection con = getConnection();
//        String sql = "SELECT STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM "
//                   + "FROM TEST WHERE STUDENT_NO = ? ORDER BY NO";
//        PreparedStatement st = con.prepareStatement(sql);
//        st.setString(1, studentNo);
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            Test t = new Test();
//            t.setStudent(rs.getString("STUDENT_NO"));
//            t.setClassNum(rs.getString("CLASS_NUM"));
//            t.setSubject(rs.getString("SUBJECT_CD"));
//            t.setSchool(rs.getString("SCHOOL_CD"));
//            t.setNo(rs.getInt("NO"));
//            t.setPoint(rs.getInt("POINT"));
//
//            list.add(t);
//        }
//        rs.close();
//        st.close();
//        con.close();
//        return list;
//    }
//
//    /** 科目コードで検索 */
//    public List<Test> findBySubjectCd(String subjectCd) throws Exception {
//        List<Test> list = new ArrayList<>();
//        Connection con = getConnection();
//        String sql = "SELECT STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM "
//                   + "FROM TEST WHERE SUBJECT_CD = ? ORDER BY STUDENT_NO";
//        PreparedStatement st = con.prepareStatement(sql);
//        st.setString(1, subjectCd);
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            Test t = new Test();
//            t.setStudentNo(rs.getString("STUDENT_NO"));
//            t.setSubjectCd(rs.getString("SUBJECT_CD"));
//            t.setSchoolCd(rs.getString("SCHOOL_CD"));
//            t.setNo(rs.getInt("NO"));
//            t.setPoint(rs.getInt("POINT"));
//            t.setClassNum(rs.getString("CLASS_NUM"));
//            list.add(t);
//        }
//        rs.close();
//        st.close();
//        con.close();
//        return list;
//    }
//}
