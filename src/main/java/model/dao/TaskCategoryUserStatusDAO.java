package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	public int insert(TaskCategoryUserStatusBean tcusbean) throws ClassNotFoundException, SQLException {
		//データベースにタスク情報を登録するメソッド
		
		//データベースに接続してタスク情報を登録する
		String sql = "INSERT INTO t_task (task_name, category_id, limit_date, user_id, status_code, memo) VALUES (?, ?, ?, ?, ?, ?)";

		//登録件数を格納するint型の変数resを宣言
		int res = 0;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダへの値の設定
			pstmt.setString(1, tcusbean.getTaskName());
			pstmt.setInt(2, tcusbean.getCategoryId());
			pstmt.setDate(3, tcusbean.getLimitDate());
			pstmt.setString(4, tcusbean.getUserId());
			pstmt.setString(5, tcusbean.getStatusCode());
			pstmt.setString(6, tcusbean.getMemo());

			//SQLステートメントの実行(更新系)
			res = pstmt.executeUpdate();

		}

		return res;

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
	//タスク編集を行うメソッド
		public int update(TaskCategoryUserStatusBean tcus) throws SQLException, ClassNotFoundException {
//		変更したデータの数を送る変数
			int count;
			
			try (Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con
							.prepareStatement(
				"update t_task set task_name = ?, category_id = ?, limit_date = ? , user_id = ? , status_code = ? , memo = ?  where task_id = ?")) {
//				タスク名の変更
				pstmt.setString(1, tcus.getTaskName());
//				カテゴリーの変更
				pstmt.setInt(2, tcus.getCategoryId());
//				期限の変更
				pstmt.setDate(3, tcus.getLimitDate());
//				担当者の変更
				pstmt.setString(4, tcus.getUserId());
//				ステータスの変更
				pstmt.setString(5, tcus.getStatusCode());
//				メモの変更
				pstmt.setString(6, tcus.getMemo());
//				変更するタスクID
				pstmt.setInt(7, tcus.getTaskId());
				
				count = pstmt.executeUpdate();
			return count;
			
		}
		}
		
		public TaskCategoryUserStatusBean detail (int taskid) throws SQLException, ClassNotFoundException{
//			選択されたタスクIDのもつ情報を詰めるためのBeanの宣言
			TaskCategoryUserStatusBean detail = new TaskCategoryUserStatusBean();
			
			try (Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con
							.prepareStatement(
						"select t1.task_id, t1.task_name, t2.category_id, t2.category_name, t1.limit_date, t3.user_id, t3.user_name, t4.status_code, t4.status_name, t1.memo, t1.create_datetime, t1.update_datetime "
						+ " from t_task t1 "
						+ "left outer join m_category t2 on t1.category_id = t2.category_id "
						+ "left outer join m_user t3 on t1.user_id = t3.user_id "
						+"left outer join m_status t4 on t1.status_code = t4.status_code  where t1.task_id = ?;"
									)){
//				詳細画面に送るタスクを検索するために参照するタスクID
				pstmt.setInt(1, taskid);
				
//				タスク検索を行う
				ResultSet res = pstmt.executeQuery();
				
//				タスク詳細画面に必要な情報をBeanに詰める
				while(res.next()) {
//					タスクID
					int detailTaskId = res.getInt("t1.task_id");
					
//					タスク名
					String detailTaskName = res.getString("t1.task_name");
					
//					カテゴリーID
					int detailCategoryId = res.getInt("t2.category_id");
					
//					カテゴリー名
					String detailCategoryName = res.getString("t2.category_name");
					
//					期限
					Date detailLimitDate = res.getDate("t1.limit_date");
					
//					ユーザーID
					String detailUserId = res.getString("t3.user_id");
					
//					ユーザー名
					String detailUserName = res.getString("t3.user_name");
					
//					ステータスコード
					String detailStatusCode = res.getString("t4.status_code");
					
//					ステータス名
					String detailStatusName = res.getString("t4.status_name");
					
//					メモ
					String detailMemo = res.getString("t1.memo");
					
//					タスク作成時刻
					Timestamp detailCreateDateTime = res.getTimestamp("t1.create_datetime");

//					タスク更新時刻
					Timestamp detailUpdateDateTime = res.getTimestamp("t1.update_datetime");
					
//					各項目をBeanにセットする
					detail.setTaskId(detailTaskId);
					detail.setTaskName(detailTaskName);
					detail.setCategoryId(detailCategoryId);
					detail.setCategoryName(detailCategoryName); 
					detail.setLimitDate(detailLimitDate);
					detail.setUserId(detailUserId);
					detail.setUserName(detailUserName);
					detail.setStatusCode(detailStatusCode);
					detail.setStatusName(detailStatusName);
					detail.setMemo(detailMemo);
					detail.setCreateDateTime(detailCreateDateTime);
					detail.setUpdateDateTime(detailUpdateDateTime);
				}
				
			}
//			Beanに詰めた情報を戻り値として戻す
			return detail;
			
		}
		
		public List<TaskCategoryUserStatusBean> selectAll() throws SQLException, ClassNotFoundException {

			List<TaskCategoryUserStatusBean> taskList = new ArrayList<TaskCategoryUserStatusBean>();

			// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
			try (Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(
							"SELECT t1.task_id, t1.task_name, t2.category_name, t1.limit_date, t3.user_name, t4.status_name, t1.memo FROM t_task t1 JOIN m_category t2 ON t1.category_id = t2.category_id JOIN m_user t3 ON t1.user_id = t3.user_id JOIN m_status t4 ON t1.status_code = t4.status_code");
					ResultSet res = pstmt.executeQuery()) {

				// 結果の操作
				while (res.next()) {
					int taskId = res.getInt("t1.task_id");
					String taskName = res.getString("t1.task_name");
					String categoryName = res.getString("t2.category_name");
					Date limitDate = res.getDate("t1.limit_date");
					String userName = res.getString("t3.user_name");
					String statusName = res.getString("t4.status_name");
					String memo = res.getString("t1.memo");
					System.out.println("タスクID 一覧サーブレット:"+taskId);
					

					TaskCategoryUserStatusBean taskCategoryUserStatus = new TaskCategoryUserStatusBean();
					taskCategoryUserStatus.setTaskId(taskId);
					taskCategoryUserStatus.setTaskName(taskName);
					taskCategoryUserStatus.setCategoryName(categoryName);
					taskCategoryUserStatus.setLimitDate(limitDate);
					taskCategoryUserStatus.setUserName(userName);
					taskCategoryUserStatus.setStatusName(statusName);
					taskCategoryUserStatus.setMemo(memo);

					taskList.add(taskCategoryUserStatus);
				}
			}
			return taskList;

		}
}
