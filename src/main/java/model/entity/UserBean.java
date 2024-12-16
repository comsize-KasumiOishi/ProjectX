package model.entity;

import java.io.Serializable;

/**
 * ログイン認証のためのm_userテーブルのBeanです。
 * @author 大石圭純
 */

public class UserBean implements Serializable {

	private String userId;
	private String password;
	private String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
