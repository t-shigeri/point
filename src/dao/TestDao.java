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

    // filterで引数に該当する条件を設定し成績一覧を取得
    public List<Test> filter(String enrollmentYear, String classNum, String subjectCd, String noStr) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql = "SELECT T.STUDENT_NO, T.SUBJECT_CD, T.SCHOOL_CD, T.NO, T.POINT, T.CLASS_NUM, " +
                     "S.NAME AS STUDENT_NAME, S.ENT_YEAR, " +
                     "SB.NAME AS SUBJECT_NAME, " +
                     "SC.NAME AS SCHOOL_NAME " +
                     "FROM TEST T " +
                     "JOIN STUDENT S ON T.STUDENT_NO = S.NO " +
                     "JOIN SUBJECT SB ON T.SUBJECT_CD = SB.CD AND T.SCHOOL_CD = SB.SCHOOL_CD " +
                     "JOIN SCHOOL SC ON T.SCHOOL_CD = SC.CD " +
                     "WHERE 1=1 ";

        if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
            sql += " AND S.ENT_YEAR = ? ";
        }
        if (classNum != null && !classNum.isEmpty()) {
            sql += " AND T.CLASS_NUM = ? ";
        }
        if (subjectCd != null && !subjectCd.isEmpty()) {
            sql += " AND T.SUBJECT_CD = ? ";
        }
        if (noStr != null && !noStr.isEmpty()) {
            sql += " AND T.NO = ? ";
        }

        sql += " ORDER BY S.ENT_YEAR DESC, T.CLASS_NUM, T.NO ";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int idx = 1;
            if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
                ps.setInt(idx++, Integer.parseInt(enrollmentYear));
            }
            if (classNum != null && !classNum.isEmpty()) {
                ps.setString(idx++, classNum);
            }
            if (subjectCd != null && !subjectCd.isEmpty()) {
                ps.setString(idx++, subjectCd);
            }
            if (noStr != null && !noStr.isEmpty()) {
                ps.setInt(idx++, Integer.parseInt(noStr));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();

                    // Student情報セット
                    Student student = new Student();
                    student.setStudentId(rs.getString("STUDENT_NO"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setEnrollmentYear(rs.getInt("ENT_YEAR"));
                    student.setClassId(rs.getString("CLASS_NUM"));  // クラス番号はTESTテーブルのCLASS_NUM
                    test.setStudent(student);

                    // Subject情報セット
                    Subject subject = new Subject();
                    subject.setCd(rs.getString("SUBJECT_CD"));
                    subject.setName(rs.getString("SUBJECT_NAME"));
                    test.setSubject(subject);

                    // School情報セット
                    School school = new School();
                    school.setCd(rs.getString("SCHOOL_CD"));
                    school.setName(rs.getString("SCHOOL_NAME"));
                    test.setSchool(school);

                    test.setNo(rs.getInt("NO"));
                    test.setPoint(rs.getInt("POINT"));
                    test.setClassNum(rs.getString("CLASS_NUM"));

                    list.add(test);
                }
            }
        }
        return list;
    }

    // TESTテーブルの回数（NO）一覧を取得
    public List<Integer> getTestCounts() throws Exception {
        List<Integer> countList = new ArrayList<>();
        String sql = "SELECT DISTINCT NO FROM TEST ORDER BY NO";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                countList.add(rs.getInt("NO"));
            }
        }
        return countList;
    }
}
