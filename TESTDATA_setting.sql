--テストデータ一覧


--ユーザマスタ m_user(10人分)
--①admin
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('admin','pass','admin', '2024-12-05 00:00:00');
	
--②深澤辰哉
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_tatsuya', 'tatsuya', '深澤辰哉', '2024-12-05 00:00:00');
	
--③佐久間大介
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_daisuke', 'daisuke', '佐久間大介', '2024-12-05 00:00:00');
	
--④渡辺翔太
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_shota', 'shota', '渡辺翔太', '2024-12-05 00:00:00');
	
--⑤宮館涼太
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_ryota', 'ryota', '宮館涼太', '2024-12-05 00:00:00');
	
--⑥岩本照
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_hikaru', 'hikaru', '岩本照', '2024-12-05 00:00:00');
	
--⑦阿部亮平
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_ryohei', 'ryohei', '阿部亮平', '2024-12-05 00:00:00');
	
--⑧向井康二
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_koji', 'koji', '向井康二', '2024-12-05 00:00:00');
	
--⑨目黒蓮
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_ren', 'ren', '目黒蓮', '2024-12-05 00:00:00');
	
--⑩ラウール
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_raul', 'raul', 'ラウール', '2024-12-05 00:00:00');


--カテゴリマスタ m_category
--A
INSERT INTO 
	m_category (category_id, category_name, update_datetime) 
VALUES
	('新商品A：開発プロジェクト','2024-12-05 00:00:00');
	
--B
INSERT INTO
	m_category (category_id, category_name, update_datetime) 
VALUES
	('既存商品B：改良プロジェクト','2024-12-05 00:00:00');


--ステータスマスタ m_status
--未着手
INSERT INTO
	m_status (status_code, status_name, update_datetime) 
VALUES
	('00','未着手','2024-12-05 00:00:00');

--着手
INSERT INTO
	m_status (status_code, status_name, update_datetime) 
VALUES
	('50','着手','2024-12-05 00:00:00');

--完了
INSERT INTO
	m_status (status_code, status_name, update_datetime) 
VALUES
	('99','完了','2024-12-05 00:00:00');


--タスクテーブル t_task
--①
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(1, 'task1', 1, '2024-12-05', 'admin', '00', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--②
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(2, 'task2', 1, '2024-12-05', 'p_tatsuya', '50', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--③
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(3, 'task3', 1, '2024-12-05', 'p_daisuke', '99', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--④
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(4, 'task4', 2, '2024-12-05', 'admin', '00', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--⑤
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(5, 'task5', 2, '2024-12-05', 'p_shota', '50', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');