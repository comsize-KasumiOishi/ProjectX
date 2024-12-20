package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskUserCommentBean;

class TaskUserCommentDAOTest {

	@Test
	void コメント件数取得() {

		//準備のためのインスタンス
		//Arrange 準備
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		List<TaskUserCommentBean> list = new ArrayList<TaskUserCommentBean>();
		//Act 	実行
		try {
			list = dao.commentCount();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert 検証
		assertNotNull(list);
	}

	@Test
	void コメントリストを取得成功() {

		int taskId = 1;

		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		List<TaskUserCommentBean> list = new ArrayList<TaskUserCommentBean>();

		//Act 	実行
		try {
			list = dao.commentList(taskId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert 検証
		assertNotNull(list);
	}

	@Test
	void コメントリストを取得失敗() {

		int taskId = 4;

		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		List<TaskUserCommentBean> list = new ArrayList<TaskUserCommentBean>();

		//Act 	実行
		try {
			list = dao.commentList(taskId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert 検証
		assertTrue(list.isEmpty());
	}

	/*コメント情報を登録するメソッド
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void コメント情報登録成功() {
		//Arrange
		TaskUserCommentBean tucbean = new TaskUserCommentBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		tucbean.setTaskId(1);
		tucbean.setUserId("p_daisuke");
		tucbean.setComment("かきくけこ");

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.insert(tucbean);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//Assert
		assertEquals(count, 1);
	}

	/*taskid未入力チェック
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void コメント情報登録失敗タスクIDなし() {
		//Arrange
		TaskUserCommentBean tucbean = new TaskUserCommentBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		//		tucbean.setTaskId(1);
		tucbean.setUserId("p_daisuke");
		tucbean.setComment("かきくけこ");

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.insert(tucbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//Assert
		assertEquals(count, 0);
	}

	/*userid未入力チェック
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void コメント情報登録失敗ユーザーIDなし() {
		//Arrange
		TaskUserCommentBean tucbean = new TaskUserCommentBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		tucbean.setTaskId(1);
		//		tucbean.setUserId("p_daisuke");
		tucbean.setComment("かきくけこ");

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.insert(tucbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}

	/*userid文字数チェック
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void コメント情報登録失敗ユーザーID文字数超過() {
		//Arrange
		TaskUserCommentBean tucbean = new TaskUserCommentBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		tucbean.setTaskId(1);
		tucbean.setUserId("abcdefghijklmnopqrstuvwxyz");
		tucbean.setComment("かきくけこ");

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.insert(tucbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}

	/*comment未入力チェック
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void コメント情報登録失敗コメントなし() {
		//Arrange
		TaskUserCommentBean tucbean = new TaskUserCommentBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		tucbean.setTaskId(1);
		tucbean.setUserId("p_daisuke");
		//		tucbean.setComment("かきくけこ");

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.insert(tucbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}

	/*comment文字数チェック
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void コメント情報登録失敗コメント文字数超過() {
		//Arrange
		TaskUserCommentBean tucbean = new TaskUserCommentBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		tucbean.setTaskId(1);
		tucbean.setUserId("p_daisuke");
		tucbean.setComment("ああああああああああああああああああ"
				+ "あああああああああああああああああああああああ"
				+ "あああああああああああああああああああああああ"
				+ "あああああああああああああああああああああああ"
				+ "ああああああああああああああ");

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.insert(tucbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}

	/*コメント情報を削除するメソッド
	 * @auther 篠
	 * @return int型の変数count
	 * @throws ClassNotFoundException,SQLException
	 */
	@Test
	void コメント情報削除成功() {
		//Arrange
		int commentId = 2;
		//登録件数を格納するint型の変数countを宣言
		int count = 0;

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.delete(commentId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//Assert
		assertEquals(count, 1);
	}

	/*コメントを削除するのに必要なリストを取得するメソッド
	 * @auther 篠
	 * @return TaskUserCommentBean型の変数list
	 * @throws ClassNotFoundException,SQLException
	 */
	@Test
	void コメント削除情報取得() {
		//Arrange
		int commentId = 2;
		List<TaskUserCommentBean> list = new ArrayList<TaskUserCommentBean>();

		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			list = dao.deleteCommentList(commentId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//Assert
		assertNotNull(list);
	}
	
	@Test
	void タスク削除時コメント削除() {
		//Arrange
		int taskId = 5;
		int count = 0;
		//Act
		TaskUserCommentDAO dao = new TaskUserCommentDAO();
		try {
			count = dao.taskCommentDelete(taskId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert
		assertEquals(count, 1);
	}

}
