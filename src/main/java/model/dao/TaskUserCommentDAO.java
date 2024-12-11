package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
