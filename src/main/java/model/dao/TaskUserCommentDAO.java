package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskUserCommentBean;

/**
 * タスクの一覧画面表示、登録、編集、削除のためのCommentテーブルのDAOです。
 * @author 坂上
 * @author 篠
 * @author 柳沢
 */

public class TaskUserCommentDAO {

	/**
	 * コメントテーブルにコメントを登録するメソッド
	 * @author 篠
	 * @param TaskUserCommentBean型の変数 tucbean
	 * @return int型の変数 res
	 * @throws SQLException , ClassNotFoundException
	 */

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

	/**
	 * コメントテーブルにあるコメントを削除するメソッド
	 * @author 篠
	 * @param int型の変数 commentId
	 * @return int型の変数 count
	 * @throws SQLException , ClassNotFoundException
	 */

	public int delete(String[] strCommentId) throws ClassNotFoundException, SQLException {
		//検索をかけるsql文を宣言
		String sql = null;

		//削除件数を格納するint型の変数countを宣言
		int count = 0;

		//StringBuilderにsql文を追加する
		StringBuilder stringbuilder = new StringBuilder("DELETE FROM t_comment WHERE");

		for (int i = 0; i < strCommentId.length; i++) {
			if (i == 0) {
				stringbuilder.append(" comment_id = ?");
			} else if (i >= 1) {
				stringbuilder.append(" OR comment_id = ?");
			}
		}

		//Where句に続く文言stringbuilderをsql文に追加する
		sql = stringbuilder.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダの数を格納するint型の変数placeを宣言
			int place = 1;
			for (int i = 0; i < strCommentId.length; i++) {
				int commentId = Integer.parseInt(strCommentId[i]);
				pstmt.setInt(place, commentId);
				place++;
			}

			count = pstmt.executeUpdate();

		}
		return count;

	}

	/**
	 * コメントテーブルにあるタスク一件に紐づいたコメントのリストを表示するメソッド
	 * @author 坂上
	 * @param int型の変数 taskId
	 * @return TaskUserCommentBean型のリスト commentList
	 * @throws SQLException , ClassNotFoundException
	 */

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

	/*コメントテーブルにあるコメントを削除するのに必要なリストの表示するメソッド*

	@author 篠
	 * @param int型の変数 commentId*@return
	TaskUserCommentBean型のリスト list*@throws SQLException,ClassNotFoundException*/

	public List<TaskUserCommentBean> deleteCommentList(String[] strCommentId)
			throws SQLException, ClassNotFoundException {
		//検索をかけるsql文を宣言
		String sql = null;

		//コメントリストを収めるリストの宣言
		List<TaskUserCommentBean> list = new ArrayList<TaskUserCommentBean>();

		//StringBuilderにsql文を追加する
		StringBuilder stringbuilder = new StringBuilder("SELECT t1.comment_id , "
				+ "t1.task_id , t2.user_id, t2.user_name , t1.comment , "
				+ "t1.update_datetime FROM t_comment t1 LEFT OUTER JOIN m_user "
				+ "t2 on t1.user_id = t2.user_id WHERE");

		for (int i = 0; i < strCommentId.length; i++) {
			if (i == 0) {
				stringbuilder.append(" comment_id = ?");
			} else if (i >= 1) {
				stringbuilder.append(" OR comment_id = ?");
			}
		}

		//Where句に続く文言stringbuilderをsql文に追加する
		sql = stringbuilder.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダの数を格納するint型の変数placeを宣言
			int place = 1;
			for (int i = 0; i < strCommentId.length; i++) {
				int commentId = Integer.parseInt(strCommentId[i]);
				pstmt.setInt(place, commentId);
				place++;
			}

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

	/**
	 * コメントテーブルにあるタスクのリストとそれに紐づいたコメントの数を表示するメソッド
	 * @author 柳沢
	 * @param 無し
	 * @return TaskUserCommentBean型のリスト commentCounts
	 * @throws SQLException , ClassNotFoundException
	 */
	
	
	public List<TaskUserCommentBean> commentCount() throws SQLException, ClassNotFoundException {
		
		List<TaskUserCommentBean> commentCounts = new ArrayList<TaskUserCommentBean>();	
		
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
			try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT task_id, COUNT(*) AS comment_count FROM t_comment GROUP BY task_id;");
				ResultSet res = pstmt.executeQuery()){
				
				
				while (res.next()) {
	                int commentCount = res.getInt("comment_count");
	                int taskId = res.getInt("task_id");

	                
	                TaskUserCommentBean taskUserCommentBean = new TaskUserCommentBean();
	              
					taskUserCommentBean.setcommentCount(commentCount);
					taskUserCommentBean.setTaskId(taskId);

					
	                commentCounts.add(taskUserCommentBean);
	            }
			}
			return commentCounts;
			
		}

	/**
	 * コメントテーブルにあるコメントを削除するメソッド
	 * @author 坂上
	 * @param int型の変数 taskId
	 * @return int型の変数 count
	 * @throws SQLException , ClassNotFoundException
	 */

	public int taskCommentDelete(int taskId) throws ClassNotFoundException, SQLException {
		//データベースからコメント情報を削除した件数を返すメソッド

		//削除件数を格納するint型の変数countを宣言
		int count = 0;

		//データベースに接続してタスクテーブルの情報を削除する
		String sql = "DELETE FROM t_comment WHERE task_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, taskId);

			count = pstmt.executeUpdate();

		}
		return count;

	}
}
