package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskUserCommentBean;

public class TaskUserCommentDAO {

	public int insert(TaskUserCommentBean tucbean) throws ClassNotFoundException, SQLException {
		//データベースにコメント情報を登録するメソッド

		//データベースに接続してタスク情報を登録する
		String sql = "INSERT INTO t_comment (task_id, user_id, comment) VALUES (?, ?, ?)";

		//登録件数を格納するint型の変数resを宣言
		int res = 0;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダへの値の設定
			pstmt.setInt(1, tucbean.getTaskId());
			pstmt.setString(2, tucbean.getUserId());
			pstmt.setString(3, tucbean.getComment());

			//SQLステートメントの実行(更新系)
			res = pstmt.executeUpdate();

		}
		return res;

	}

	public int delete(int commentId) throws ClassNotFoundException, SQLException {
		//データベースからコメント情報を削除した件数を返すメソッド

		//削除件数を格納するint型の変数countを宣言
		int count = 0;

		//データベースに接続してタスクテーブルの情報を削除する
		String sql = "DELETE FROM t_comment WHERE comment_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, commentId);

			count = pstmt.executeUpdate();

		}
		return count;

	}

	public List<TaskUserCommentBean> commentList(int taskId) throws SQLException, ClassNotFoundException {
		//コメントリストを収めるリストの宣言
		List<TaskUserCommentBean> list = new ArrayList<TaskUserCommentBean>();

		//データベースに接続してコメントテーブルの情報を取得する
		String sql = "SELECT t1.comment_id , t1.task_id , t2.user_id, t2.user_name , t1.comment , t1.update_datetime FROM t_comment t1 LEFT OUTER JOIN m_user t2 on t1.user_id = t2.user_id WHERE task_id = ? ORDER BY comment_id ASC";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, taskId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				//リストに詰めるためのBeanの宣言
				TaskUserCommentBean tucbean = new TaskUserCommentBean();
				tucbean.setCommentId(res.getInt("t1.comment_id"));
				tucbean.setTaskId(res.getInt("t1.task_id"));
				tucbean.setUserId(res.getString("t2.user_id"));
				tucbean.setUserName(res.getString("t2.user_name"));
				tucbean.setComment(res.getString("t1.comment"));
				tucbean.setUpdateDateTime(res.getTimestamp("t1.update_datetime"));

				list.add(tucbean);
			}
		}
		return list;

	}
}
