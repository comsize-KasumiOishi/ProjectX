package model.entity;

import java.sql.Timestamp;

public class TaskUserCommentBean {

	private int taskId;
	private String userId;
	private String comment;
	private String userName;
	private Timestamp updateDateTime;
	private int commentId;
	private int commentCount;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public int getcommentCount() {
		return commentCount;
	}

	public void setcommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
