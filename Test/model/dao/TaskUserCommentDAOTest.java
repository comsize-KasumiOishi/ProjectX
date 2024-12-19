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

}
