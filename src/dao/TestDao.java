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

	/**
	 * 成績一覧を条件に応じて検索するメソッド
	 */
	public List<Test> filter(String enrollmentYear, String classNum, String subjectCd, String noStr) throws Exception {
		List<Test> list = new ArrayList<>();

		String sql = "SELECT T.STUDENT_NO, T.SUBJECT_CD, T.SCHOOL_CD, T.NO, T.POINT, T.CLASS_NUM, "
				+ "S.NAME AS STUDENT_NAME, S.ENT_YEAR, " + "SB.NAME AS SUBJECT_NAME, " + "SC.NAME AS SCHOOL_NAME "
				+ "FROM TEST T " + "JOIN STUDENT S ON T.STUDENT_NO = S.NO "
				+ "JOIN SUBJECT SB ON T.SUBJECT_CD = SB.CD AND T.SCHOOL_CD = SB.SCHOOL_CD "
				+ "JOIN SCHOOL SC ON T.SCHOOL_CD = SC.CD " + "WHERE 1=1 ";

		if (enrollmentYear != null && !enrollmentYear.isEmpty())
			sql += " AND S.ENT_YEAR = ? ";
		if (classNum != null && !classNum.isEmpty())
			sql += " AND T.CLASS_NUM = ? ";
		if (subjectCd != null && !subjectCd.isEmpty())
			sql += " AND T.SUBJECT_CD = ? ";
		if (noStr != null && !noStr.isEmpty())
			sql += " AND T.NO = ? ";

		sql += "ORDER BY S.ENT_YEAR DESC, T.CLASS_NUM, T.NO";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			int idx = 1;
			if (enrollmentYear != null && !enrollmentYear.isEmpty())
				ps.setInt(idx++, Integer.parseInt(enrollmentYear));
			if (classNum != null && !classNum.isEmpty())
				ps.setString(idx++, classNum);
			if (subjectCd != null && !subjectCd.isEmpty())
				ps.setString(idx++, subjectCd);
			if (noStr != null && !noStr.isEmpty())
				ps.setInt(idx++, Integer.parseInt(noStr));

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Test test = new Test();

					Student student = new Student();
					student.setStudentId(rs.getString("STUDENT_NO"));
					student.setName(rs.getString("STUDENT_NAME"));
					student.setEnrollmentYear(rs.getInt("ENT_YEAR"));
					student.setClassId(rs.getString("CLASS_NUM"));
					test.setStudent(student);

					Subject subject = new Subject();
					subject.setCd(rs.getString("SUBJECT_CD"));
					subject.setName(rs.getString("SUBJECT_NAME"));
					test.setSubject(subject);

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

	/**
	 * 成績の保存（既存なら更新、なければ挿入）
	 */
	public void saveOrUpdate(Test test) throws Exception {
		String checkSql = "SELECT COUNT(*) FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ? AND CLASS_NUM = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(checkSql)) {
			ps.setString(1, test.getStudent().getStudentId());
			ps.setString(2, test.getSubject().getCd());
			ps.setInt(3, test.getNo());
			ps.setString(4, test.getClassNum());

			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				int count = rs.getInt(1);

				if (count > 0) {
					// UPDATE
					String updateSql = "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ? AND CLASS_NUM = ?";
					try (PreparedStatement ups = con.prepareStatement(updateSql)) {
						ups.setInt(1, test.getPoint());
						ups.setString(2, test.getStudent().getStudentId());
						ups.setString(3, test.getSubject().getCd());
						ups.setInt(4, test.getNo());
						ups.setString(5, test.getClassNum());
						ups.executeUpdate();
					}
				} else {
					// INSERT
					String schoolCd = test.getSchool() != null ? test.getSchool().getCd()
							: getSchoolCdByStudentId(test.getStudent().getStudentId(), con);
					String insertSql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
					try (PreparedStatement ins = con.prepareStatement(insertSql)) {
						ins.setString(1, test.getStudent().getStudentId());
						ins.setString(2, test.getSubject().getCd());
						ins.setString(3, schoolCd);
						ins.setInt(4, test.getNo());
						ins.setInt(5, test.getPoint());
						ins.setString(6, test.getClassNum());
						ins.executeUpdate();
					}
				}
			}
		}
	}

	/**
	 * 学生IDから学校コードを取得する（内部用）
	 */
	private String getSchoolCdByStudentId(String studentId, Connection con) throws Exception {
		String sql = "SELECT SCHOOL_CD FROM STUDENT WHERE NO = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, studentId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getString("SCHOOL_CD");
				} else {
					throw new Exception("学生IDに対応する学校CDが見つかりません: " + studentId);
				}
			}
		}
	}

	public String getSchoolCdByStudentId(String studentId) throws Exception {
		String sql = "SELECT SCHOOL_CD FROM STUDENT WHERE NO = ?";
		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, studentId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("SCHOOL_CD");
				}
			}
		}
		return null;
	}
}