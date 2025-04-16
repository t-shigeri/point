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

    // H2用のDB接続設定
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("H2ドライバのロードに失敗しました", e);
        }
        return DriverManager.getConnection("jdbc:h2:~/tanaka", "sa", ""); // ← ここは環境に応じて変えてね
    }

    // 学生の一覧取得
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT s.STUDENT_ID, s.NAME, s.ENROLLMENT_YEAR, s.CLASS_ID, s.IS_ENROLLED, c.CLASS_NAME " +
                     "FROM STUDENT s " +
                     "JOIN CLASS c ON s.CLASS_ID = c.CLASS_ID " +
                     "ORDER BY s.ENROLLMENT_YEAR DESC, s.STUDENT_ID";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                    rs.getString("STUDENT_ID"),
                    rs.getString("NAME"),
                    rs.getInt("ENROLLMENT_YEAR"),
                    rs.getString("CLASS_ID"),
                    rs.getBoolean("IS_ENROLLED"),
                    rs.getString("CLASS_NAME")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}
