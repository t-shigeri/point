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
		return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/tanaka", "sa", "");
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

		try (Connection conn = getConnection(); PreparedStatement request = conn.prepareStatement(sql)) {

			int index = 1;
			if (enrollmentYear != null && !enrollmentYear.isEmpty()) {
				request.setInt(index++, Integer.parseInt(enrollmentYear));
			}
			if (classId != null && !classId.isEmpty()) {
				request.setString(index++, classId);
			}

			try (ResultSet response = request.executeQuery()) {
				while (response.next()) {
					Student student = new Student();
					student.setStudentId(response.getString("NO"));
					student.setName(response.getString("NAME"));
					student.setEnrollmentYear(response.getInt("ENT_YEAR"));
					student.setClassId(response.getString("CLASS_NUM"));
					student.setClassName(response.getString("CLASS_NUM"));
					student.setEnrolled(response.getBoolean("IS_ATTEND"));
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

	// 学生番号(no)に対応する Student を返す
	public Student findByNo(String no) {
		String sql = "SELECT NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD FROM STUDENT WHERE NO = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, no);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Student s = new Student();
					s.setStudentId(rs.getString("NO"));
					s.setName(rs.getString("NAME"));
					s.setEnrollmentYear(rs.getInt("ENT_YEAR"));
					s.setClassId(rs.getString("CLASS_NUM"));
					s.setEnrolled(rs.getBoolean("IS_ATTEND"));
					s.setSchoolCd(rs.getString("SCHOOL_CD"));
					return s;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 学生情報を更新する
	public void updateStudent(Student student) {
		String sql = "UPDATE STUDENT SET NAME = ?, ENT_YEAR = ?, CLASS_NUM = ?, IS_ATTEND = ? WHERE NO = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, student.getName());
			ps.setInt(2, student.getEnrollmentYear());
			ps.setString(3, student.getClassId());
			ps.setBoolean(4, student.isEnrolled());
			ps.setString(5, student.getStudentId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createStudent(Student student) {
		String sql = "INSERT INTO STUDENT(NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, student.getStudentId());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getEnrollmentYear());
			ps.setString(4, student.getClassId());
			ps.setBoolean(5, student.isEnrolled());
			// schoolCd をフォームで受け取るようにしている場合
			ps.setString(6, student.getSchoolCd());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// 必要なら例外を投げ直してサーブレット側でエラーページへ
		}
	}

}
