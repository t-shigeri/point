package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao {

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("H2ドライバのロードに失敗しました", e);
        }
        return DriverManager.getConnection("jdbc:h2:~/tanaka", "sa", "");
    }

    // 学生の一覧取得（フィルタリング対応）
    public List<Student> findAll(String enrollmentYear, String classId, String isEnrolled) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND FROM STUDENT WHERE 1=1";

        if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
            sql += " AND ENT_YEAR = ?";
        }
        if (classId != null && !classId.isEmpty()) {
            sql += " AND CLASS_NUM = ?";
        }
        if ("t".equals(isEnrolled)) {
            sql += " AND IS_ATTEND = TRUE";
        }

        sql += " ORDER BY ENT_YEAR DESC, NO";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;
            if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(enrollmentYear));
            }
            if (classId != null && !classId.isEmpty()) {
                ps.setString(index++, classId);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString("NO"));
                    student.setName(rs.getString("NAME"));
                    student.setEnrollmentYear(rs.getInt("ENT_YEAR"));
                    student.setClassId(rs.getString("CLASS_NUM"));
                    student.setClassName(rs.getString("CLASS_NUM"));
                    student.setEnrolled(rs.getBoolean("IS_ATTEND"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 入学年度の一覧を取得
    public List<Integer> getEnrollmentYears() {
        List<Integer> years = new ArrayList<>();
        String sql = "SELECT DISTINCT ENT_YEAR FROM STUDENT ORDER BY ENT_YEAR DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                years.add(rs.getInt("ENT_YEAR"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return years;
    }

    // クラス一覧を取得
    public List<String> getClassList() {
        List<String> classes = new ArrayList<>();
        String sql = "SELECT DISTINCT CLASS_NUM FROM STUDENT ORDER BY CLASS_NUM";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                classes.add(rs.getString("CLASS_NUM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
