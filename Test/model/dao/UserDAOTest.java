package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class UserDAOTest {

	@Test
	void logincheck成功確認() {
		//準備
		UserDAO dao = new UserDAO();
		String userId = "admin";
		String password = "pass";
		UserBean userbean = null;

		try {
			userbean = dao.logincheck(userId, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals("admin", userbean.getUserId());
		assertEquals("pass", userbean.getPassword());
	}

	@Test
	void loginAuthentication失敗確認() {
		//準備
		UserDAO dao = new UserDAO();
		String userId = "";
		String password = "";
		UserBean userbean = null;

		try {
			userbean = dao.logincheck(userId, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertNull(userbean);
	}

}
