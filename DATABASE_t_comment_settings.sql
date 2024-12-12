CREATE TABLE task_db.t_comment(
	comment_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	task_id INT NOT NULL,
	user_id VARCHAR(24) NOT NULL,
	comment VARCHAR(100) NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

--外部キーの付与
ALTER TABLE task_db.t_comment ADD FOREIGN KEY(task_id)
REFERENCES task_db.t_task(task_id);

ALTER TABLE task_db.t_comment ADD FOREIGN KEY(user_id)
REFERENCES task_db.m_user(user_id);
