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

public Subject get(String cd,Shcool school){
//	データベースと接続
    Connection con = getConnection();

	return null;
}
public List<Product> search(String keyword) throws Exception {
    // ① 検索結果を格納するリストを作成
    List<Product> list = new ArrayList<>();

    // ② データベース接続を取得（DAOクラスの getConnection() を利用）
    Connection con = getConnection();

    // ③ SQL文の準備（商品名にキーワードが含まれるものを検索）
    PreparedStatement st = con.prepareStatement(
        "SELECT * FROM product WHERE name LIKE ?");
    // ④ プレースホルダ `?` にキーワードをセット（% を付けて部分一致検索にする）
    st.setString(1, "%" + keyword + "%");

    // ⑤ SQL文を実行し、結果を取得
    ResultSet rs = st.executeQuery();

    // ⑥ 検索結果のデータを1件ずつ Product オブジェクトに変換してリストに追加
    while (rs.next()) {
        Product p = new Product();
        p.setId(rs.getInt("id"));       // ID を設定
        p.setName(rs.getString("name")); // 名前を設定
        p.setPrice(rs.getInt("price"));  // 価格を設定
        list.add(p);  // 作成したオブジェクトをリストに追加
    }
    // ⑦ 使用したリソースを解放（PreparedStatement と Connection を閉じる）
    st.close();
    con.close();

    // ⑧ 検索結果のリストを呼び出し元に返す
    return list;
}
public boolean save(Subject subject){
	try {
    Connection con = getConnection();
    PreparedStatement st = con.prepareStatement(
            "INSERT INTO STUDENT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)");
        st.setSchool(1, subject.getSchool());
        st.setString(2, subject.getCd());
        st.setString(2, subject.getName());
        st.executeUpdate();

        st.close();
        con.close();
	} catch (SQLException e) {
		return false;
	}
	return true;
}
public List<Subject> filter(School school){
	return null;
}
}
