package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskCategoryUserStatusBean;

public class TaskCategoryUserStatusDAO {

	public List<TaskCategoryUserStatusBean> selectCategoryId() throws ClassNotFoundException, SQLException {
		//カテゴリマスタの情報一覧を格納したリストを返すメソッド

		//データベースに接続してカテゴリマスタの情報を取得する
		String sql = "SELECT * FROM m_category";

		//カテゴリマスタの情報を格納するリストを作成
		List<TaskCategoryUserStatusBean> categoryList = new ArrayList<>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//SQLステートメントの実行(参照系)
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				//TaskCategoryUserStatusBeanクラスのインスタンスを作成
				TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
				tcusbean.setCategoryId(res.getInt("category_id"));
				tcusbean.setCategoryName(res.getString("category_name"));

				//カテゴリーマスタの情報をリストに格納する
				categoryList.add(tcusbean);
			}
		}
		return categoryList;

	}

	public List<TaskCategoryUserStatusBean> selectUserId() throws ClassNotFoundException, SQLException {
		//ユーザマスタのユーザIDを格納したリストを返すメソッド

		//データベースに接続してユーザマスタの情報を取得する
		String sql = "SELECT * FROM m_user";

		//ユーザマスタの情報を格納するリストを作成
		List<TaskCategoryUserStatusBean> userList = new ArrayList<>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//SQLステートメントの実行(参照系)
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				//TaskCategoryUserStatusBeanクラスのインスタンスを作成
				TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
				tcusbean.setUserId(res.getString("user_id"));
				tcusbean.setUserName(res.getString("user_name"));

				//ユーザマスタの情報をリストに格納する
				userList.add(tcusbean);
			}
		}
		return userList;

	}

	public List<TaskCategoryUserStatusBean> selectStatusCode() throws ClassNotFoundException, SQLException {
		//ステータスマスタの情報一覧を格納したリストを返すメソッド

		//データベースに接続してステータスマスタの情報を取得する
		String sql = "SELECT * FROM m_status";

		//ステータスマスタの情報を格納するリストを作成
		List<TaskCategoryUserStatusBean> statusList = new ArrayList<>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//SQLステートメントの実行(参照系)
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				//TaskCategoryUserStatusBeanクラスのインスタンスを作成
				TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
				tcusbean.setStatusCode(res.getString("status_code"));
				tcusbean.setStatusName(res.getString("status_name"));

				//ステータスマスタの情報をリストに格納する
				statusList.add(tcusbean);
			}
		}
		return statusList;
	}
	
	public int delete(int taskId) throws SQLException, ClassNotFoundException {
		//削除した件数を返すメソッド
		
		int count = 0;
		
		//データベースに接続してタスクテーブルの情報を削除する
		String sql = "DELETE FROM t_task WHERE task_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, taskId);

			count = pstmt.executeUpdate();

		}
		return count;
	}

}
