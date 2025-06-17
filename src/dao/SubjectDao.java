package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
public class SubjectDao extends Dao{








public Subject get(String cd, School school) throws Exception {

Subject subject = null;
	try {
Connection con = getConnection();
String sql = "SELECT * FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?";
PreparedStatement st = con.prepareStatement(sql);
st.setString(1, cd);
st.setString(2, school.getCd());

ResultSet rs = st.executeQuery();
if (rs.next()) {
subject = new Subject();
subject.setCd(rs.getString("CD"));
subject.setName(rs.getString("NAME"));
subject.setSchool(school);
}
st.close();
con.close();
} catch (SQLException e) {
    e.printStackTrace();
}
return subject;
}





public boolean save(Subject subject) throws Exception {
int f = 0;
try{
Connection con = getConnection();

String sql = "MERGE INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)";
PreparedStatement st = con.prepareStatement(sql);
st.setString(1, subject.getSchool().getCd());
st.setString(2, subject.getCd());
st.setString(3, subject.getName());

f = st.executeUpdate();
st.close();
con.close();
}catch(SQLException e) {
e.printStackTrace();
return false;
}
return f > 0;
}



public List<Subject> findAll() throws Exception {
    List<Subject> list = new ArrayList<>();

    Connection con = getConnection();

    String sql = "SELECT s.CD, s.NAME, s.SCHOOL_CD, sc.NAME as SCHOOL_NAME " +
            "FROM SUBJECT s " +
            "LEFT JOIN SCHOOL sc ON s.SCHOOL_CD = sc.CD";

    PreparedStatement st = con.prepareStatement(sql);
    ResultSet rs = st.executeQuery();

    while (rs.next()) {
        Subject subject = new Subject();
        subject.setCd(rs.getString("CD"));
        subject.setName(rs.getString("NAME"));

        School school = new School();
        school.setCd(rs.getString("SCHOOL_CD"));
        school.setName(rs.getString("SCHOOL_NAME"));

        subject.setSchool(school);

        list.add(subject);
    }

    rs.close();
    st.close();
    con.close();

    return list;
}

public List<Subject> filter(School school) throws Exception {
    List<Subject> list = new ArrayList<>();
try{
    Connection con = getConnection();
    String sql = "SELECT * FROM SUBJECT WHERE SCHOOL_CD = ?";
    PreparedStatement st = con.prepareStatement(sql);
    st.setString(1, school.getCd());

    ResultSet rs = st.executeQuery();
    while (rs.next()) {
        Subject subject = new Subject();
        subject.setCd(rs.getString("CD"));
        subject.setName(rs.getString("NAME"));
        subject.setSchool(school);
        list.add(subject);
    }

    st.close();
    con.close();
}catch(SQLException e) {
e.printStackTrace();
}
    return list;
}






public boolean delete(Subject subject) throws Exception {
int f=0;
try{
Connection con = getConnection();

String sql = "DELETE FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?";
PreparedStatement st = con.prepareStatement(sql);
st.setString(1, subject.getCd());
st.setString(2, subject.getSchool().getCd());

f = st.executeUpdate();
st.close();
con.close();
}catch(SQLException e) {
e.printStackTrace();
return false;
}
return f > 0;
}





}




