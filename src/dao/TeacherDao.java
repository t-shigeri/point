package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

	public Teacher login(String id, String password) {
	    Teacher teacher = null;

	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(
	             "SELECT t.id, t.password, t.name, t.school_cd, s.name AS school_name " +
	             "FROM TEACHER t LEFT JOIN SCHOOL s ON t.school_cd = s.cd " +
	             "WHERE t.id = ? AND t.password = ?")) {

	        stmt.setString(1, id);
	        stmt.setString(2, password);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            // Teacher オブジェクトの生成とセット
	            teacher = new Teacher();
	            teacher.setId(rs.getString("id"));
	            teacher.setPassword(rs.getString("password"));
	            teacher.setName(rs.getString("name"));

	            // School オブジェクトの生成とセット（← ここに入れる！）
	            School school = new School();
	            school.setCd(rs.getString("school_cd"));
	            school.setName(rs.getString("school_name")); // ★これが名前

	            // Teacher に School をセット
	            teacher.setSchool(school);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return teacher;
	}
}
