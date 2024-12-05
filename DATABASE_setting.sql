--同名のデータベースがない場合、task_dbを作成
CREATE DATABASE IF NOT EXISTS task_db;
--ユーザーテーブルを作成
CREATE TABLE task_db.m_user(
	user_id VARCHAR(24) PRIMARY KEY NOT NULL,
	 password VARCHAR(32) NOT NULL,
	 user_name VARCHAR(20) NOT NULL,
	 update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	 UNIQUE KEY (user_name)
	);
--カテゴリーデーブルを作成
CREATE TABLE task_db.m_category(
	category_id INT PRIMARY KEY NOT NULL,
	category_name VARCHAR(20) NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	UNIQUE KEY (category_name)
	);
--ステータステーブルの作成
CREATE TABLE task_db.m_status(
	status_code CHAR(2) PRIMARY KEY NOT NULL,
	status_name VARCHAR(20) NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	UNIQUE KEY (status_name)
	);
	
--タスクテーブルの作成
CREATE TABLE task_db.t_task(
	task_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	task_name VARCHAR(50) NOT NULL,
	category_id INT NOT NULL,
	limit_date DATE,
	user_id VARCHAR(24) NOT NULL,
	status_code CHAR(2) NOT NULL,
	memo VARCHAR(100) NOT NULL,
	create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

--外部キーの付与
ALTER TABLE task_db.t_task ADD FOREIGN KEY(user_id)
REFERENCES task_db.m_user(user_id);

ALTER TABLE task_db.t_task ADD FOREIGN KEY(category_id)
REFERENCES task_db.m_category(category_id);

ALTER TABLE task_db.t_task ADD FOREIGN KEY(status_code)
REFERENCES task_db.m_status(status_code);

--カラム名の確認を行う
show columns from task_db.m_user;

--外部キー制約の確認
SHOW CREATE TABLE task_db.t_task;
