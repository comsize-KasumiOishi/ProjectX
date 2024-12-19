package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskCategoryUserStatusBean;

class TaskCategoryUserStatusDAOTest {

	@Test
	void limitDateList格納成功確認() {
		//準備
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		String userId = "admin";
		List<Date> limitDateList = null;

		try {
			limitDateList = dao.limitDateList(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(limitDateList);
	}

	@Test
	void categoryList格納成功確認() {
		//準備
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		List<TaskCategoryUserStatusBean> categoryList = null;

		try {
			categoryList = dao.selectCategoryId();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(categoryList);
	}

	@Test
	void userList格納成功確認() {
		//準備
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		List<TaskCategoryUserStatusBean> userList = null;

		try {
			userList = dao.selectUserId();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(userList);
	}

	@Test
	void statusList格納成功確認() {
		//準備
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		List<TaskCategoryUserStatusBean> statusList = null;

		try {
			statusList = dao.selectStatusCode();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNotNull(statusList);
	}

	@Test
	void 詳細画面表示test() {
		//準備のためのインスタンス
		//Arrange 準備
		int taskId = 1;
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		//Act 	実行
		try {
			bean = dao.detail(taskId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert 検証
		assertNotNull(bean);
	}

	@Test
	void 変更画面成功() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		bean.setTaskName("task1");
		bean.setCategoryId(1);
		bean.setLimitDate(date);
		bean.setUserId("admin");
		bean.setStatusCode("50");
		bean.setMemo("とくにありません");
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
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

	@Test
	void taskNameNULL変更失敗画面() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		//		bean.setTaskName("task1");
		bean.setCategoryId(1);
		bean.setLimitDate(date);
		bean.setUserId("admin");
		bean.setStatusCode("50");
		bean.setMemo("とくにありません");
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			//Assert
			assertEquals(count, 0);
		}
	}

	@Test
	void categoryIdNULL変更失敗画面() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		bean.setTaskName("task1");
		//		bean.setCategoryId(1);
		bean.setLimitDate(date);
		bean.setUserId("admin");
		bean.setStatusCode("50");
		bean.setMemo("とくにありません");
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			//Assert
			assertEquals(count, 0);
		}
	}

	@Test
	void userIdNULL変更失敗画面() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		bean.setTaskName("task1");
		bean.setCategoryId(1);
		bean.setLimitDate(date);
		//		bean.setUserId("admin");
		bean.setStatusCode("50");
		bean.setMemo("とくにありません");
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			//Assert
			assertEquals(count, 0);
		}
	}

	@Test
	void statusCodeNULL変更失敗画面() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		bean.setTaskName("task1");
		bean.setCategoryId(1);
		bean.setLimitDate(date);
		bean.setUserId("admin");
		//		bean.setStatusCode("50");
		bean.setMemo("とくにありません");
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			//Assert
			assertEquals(count, 0);
		}
	}

	@Test
	void taskName文字数チェック変更失敗画面() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		bean.setTaskName("あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもらりるれろわおんやゆよ１２３４５");
		bean.setCategoryId(1);
		bean.setLimitDate(date);
		bean.setUserId("admin");
		bean.setStatusCode("50");
		bean.setMemo("とくにありません");
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			//Assert
			assertEquals(count, 0);
		}
	}

	@Test
	void statusCode文字数チェック変更失敗画面() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		bean.setTaskName("task1");
		bean.setCategoryId(1);
		bean.setLimitDate(date);
		bean.setUserId("admin");
		bean.setStatusCode("123");
		bean.setMemo("とくにありません");
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			//Assert
			assertEquals(count, 0);
		}
	}

	@Test
	void memo文字数チェック変更失敗画面() {
		//Arrange
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean();
		int count = 0;
		Date date = new Date(2024 - 12 - 06);
		bean.setTaskName("task1");
		bean.setCategoryId(1);
		bean.setLimitDate(date);
		bean.setUserId("admin");
		bean.setStatusCode("50");
		bean.setMemo(
				"寿限無 寿限無 五劫のすり切れ 海砂利水魚の 水行末、雲来末、風来末 食う寝るところに住むところ やぶらこうじのぶらこうじ パイポパイポパイポのシューリンガン シューリンガンのグーリンダイ グーリンダイのポンポコピーのポンポコナーの長久命の長助");
		//122文字
		bean.setTaskId(1);
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.update(bean);
			fail();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			//Assert
			assertEquals(count, 0);
		}
	}

	@Test
	void 期限リストの取得() {
		//Arrange
		List<Date> list = new ArrayList<Date>();
		String userId = "admin";
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			list = dao.limitDateList(userId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert
		assertNotNull(list);
	}

	@Test
	void カテゴリーリスト表示() {
		//Arrange
		List<TaskCategoryUserStatusBean> list = new ArrayList<TaskCategoryUserStatusBean>();
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		//Act

		try {
			list = dao.selectCategoryId();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert  
		assertNotNull(list);
	}

	@Test
	void ユーザーリスト表示() {
		//Arrange
		List<TaskCategoryUserStatusBean> list = new ArrayList<TaskCategoryUserStatusBean>();
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		//Act

		try {
			list = dao.selectUserId();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert
		assertNotNull(list);
	}

	@Test
	void ステータスリスト表示() {
		//Arrange
		List<TaskCategoryUserStatusBean> list = new ArrayList<TaskCategoryUserStatusBean>();
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		//Act

		try {
			list = dao.selectStatusCode();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert
		assertNotNull(list);
	}

	@Test
	void タスク一覧画面表示() {
		//準備のためのインスタンス
		//Arrange 準備
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		List<TaskCategoryUserStatusBean> list = new ArrayList<TaskCategoryUserStatusBean>();
		//Act 	実行
		try {
			list = dao.selectAll();
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
	void タスク削除成功() {
		//準備のためのインスタンス
		//Arrange 準備
		int count = 0;
		int taskId = 5;
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		//Act 	実行
		try {
			count = dao.delete(taskId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert 検証
		assertEquals(count, 1);
	}

	@Test
	void タスク削除失敗() {
		//準備のためのインスタンス
		//Arrange 準備
		int count = 0;
		int taskId = 6;
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		//Act 	実行
		try {
			count = dao.delete(taskId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Assert 検証
		assertEquals(count, 0);
	}

	/*タスクの全項目を登録するメソッド*

	@auther 篠
	 * @return int型の変数count
	 * @throws ClassNotFoundException,SQLException
	 */
	@Test
	void 全項目タスク登録成功() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		tcusbean.setTaskName("task1");
		tcusbean.setCategoryId(1);
		tcusbean.setLimitDate(date);
		tcusbean.setUserId("admin");
		tcusbean.setStatusCode("50");
		tcusbean.setMemo("とくにありません");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(count, 1);
	}

	/*taskName未入力チェック（Nullチェック）
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void タスク登録失敗タスク名なし() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		//		tcusbean.setTaskName("task1");
		tcusbean.setCategoryId(1);
		tcusbean.setLimitDate(date);
		tcusbean.setUserId("admin");
		tcusbean.setStatusCode("50");
		tcusbean.setMemo("とくにありません");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}

	/*taskName文字数チェック（50文字以上）
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void タスク登録失敗タスク名文字数超過() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		tcusbean.setTaskName("あああああああああああああああああああああああああ"
				+ "ああああああああああああああああああああああああああ");
		tcusbean.setCategoryId(1);
		tcusbean.setLimitDate(date);
		tcusbean.setUserId("admin");
		tcusbean.setStatusCode("50");
		tcusbean.setMemo("とくにありません");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}

	/*CategoryId未入力チェック（Nullチェック）
	 * @auther 篠
	 * @return int型の変数count
	 * @throws ClassNotFoundException,SQLException
	 */
	@Test
	void カテゴリー名登録失敗カテゴリー名なし() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		tcusbean.setTaskName("task1");
		//			tcusbean.setCategoryId(1);
		tcusbean.setLimitDate(date);
		tcusbean.setUserId("admin");
		tcusbean.setStatusCode("50");
		tcusbean.setMemo("とくにありません");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}

	/*userId未入力チェック（Nullチェック）
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void タスク登録失敗ユーザー名なし() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		tcusbean.setTaskName("task1");
		tcusbean.setCategoryId(0);
		tcusbean.setLimitDate(date);
		//		tcusbean.setUserId("admin");
		tcusbean.setStatusCode("50");
		tcusbean.setMemo("とくにありません");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}
		
	}

	/*StatusCode未入力チェック（Nullチェック）
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void タスク登録失敗ステータスコードなし() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		tcusbean.setTaskName("task1");
		tcusbean.setCategoryId(0);
		tcusbean.setLimitDate(date);
		tcusbean.setUserId("admin");
		//		tcusbean.setStatusCode("50");
		tcusbean.setMemo("とくにありません");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}
		
	}

	/*StatusCode文字数チェック（2文字以上）
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void タスク登録失敗ステータスコード文字数超過() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		tcusbean.setTaskName("task1");
		tcusbean.setCategoryId(0);
		tcusbean.setLimitDate(date);
		tcusbean.setUserId("admin");
		tcusbean.setStatusCode("000");
		tcusbean.setMemo("とくにありません");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}
		
	}

	/*memo文字数チェック（100文字以上）
	* @auther 篠
	* @return int型の変数count
	* @throws ClassNotFoundException,SQLException
	*/
	@Test
	void タスク登録失敗メモ() {
		//Arrange
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		//登録件数を格納するint型の変数countを宣言
		int count = 0;
		Date date = new Date(2024 - 12 - 19);
		tcusbean.setTaskName("task1");
		tcusbean.setCategoryId(0);
		tcusbean.setLimitDate(date);
		tcusbean.setUserId("admin");
		tcusbean.setStatusCode("000");
		tcusbean.setMemo("あああああああああああああああああああああああああああ"
				+ "あああああああああああああああああああああああああああああああ"
				+ "あああああああああああああああああああああああああああああああ"
				+ "ああああああああああああ");

		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.insert(tcusbean);
			fail();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//Assert
			assertEquals(count, 0);
		}

	}
}
