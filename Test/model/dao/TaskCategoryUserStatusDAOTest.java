package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskCategoryUserStatusBean;

class TaskCategoryUserStatusDAOTest {

	//Oishi
	
	@Test
	void test() {
		fail("まだ実装されていません");
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
	void 変更画面() {
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
	void 削除画面() {
		//Arrange
		int count = 0;
		int taskId = 1;
		//Act
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			count = dao.delete(taskId);
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

}
