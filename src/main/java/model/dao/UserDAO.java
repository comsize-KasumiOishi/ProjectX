package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

/**
 * ログイン認証のためのm_userテーブルのDAO
 * @author 大石圭純
 */
public class UserDAO {
	/**
	 * 指定されたユーザIDとパスワードから、該当するユーザー名を検索して返す
	 * @param userId, password 従業員のユーザーIDとパスワード
	 * @return ユーザIDとパスワードとユーザー名を格納したUserBean型のuserbean
	 * @throws SQLException
	 */
	public UserBean logincheck(String userId, String password) throws SQLException, ClassNotFoundException {

		String sql = "SELECT user_name FROM m_user WHERE user_id = ? AND password = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				UserBean userbean = new UserBean();
				userbean.setUserId(userId);
				userbean.setPassword(password);
				userbean.setUserName(res.getString("user_name"));

				return userbean;

			} else {
				return null;
			}
		}
	}
}
