--eXgf[^ê


--[U}X^ m_user(10lª)
--@admin
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('admin','pass','admin', '2024-12-05 00:00:00');
	
--A[àVCÆ
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_tatsuya', 'tatsuya', '[àVCÆ', '2024-12-05 00:00:00');
	
--B²vÔåî
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_daisuke', 'daisuke', '²vÔåî', '2024-12-05 00:00:00');
	
--CnÓãÄ¾
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_shota', 'shota', 'nÓãÄ¾', '2024-12-05 00:00:00');
	
--D{ÙÁ¾
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_ryota', 'ryota', '{ÙÁ¾', '2024-12-05 00:00:00');
	
--Eâ{Æ
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_hikaru', 'hikaru', 'â{Æ', '2024-12-05 00:00:00');
	
--F¢º½
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_ryohei', 'ryohei', '¢º½', '2024-12-05 00:00:00');
	
--GüäNñ
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_koji', 'koji', 'üäNñ', '2024-12-05 00:00:00');
	
--HÚ@
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_ren', 'ren', 'Ú@', '2024-12-05 00:00:00');
	
--IE[
INSERT INTO 
	m_user (user_id, password, user_name, update_datetime) 
VALUES 
	('p_raul', 'raul', 'E[', '2024-12-05 00:00:00');


--JeS}X^ m_category
--A
INSERT INTO 
	m_category (category_id, category_name, update_datetime) 
VALUES
	('V¤iAFJ­vWFNg','2024-12-05 00:00:00');
	
--B
INSERT INTO
	m_category (category_id, category_name, update_datetime) 
VALUES
	('ù¶¤iBFüÇvWFNg','2024-12-05 00:00:00');


--Xe[^X}X^ m_status
--¢è
INSERT INTO
	m_status (status_code, status_name, update_datetime) 
VALUES
	('00','¢è','2024-12-05 00:00:00');

--è
INSERT INTO
	m_status (status_code, status_name, update_datetime) 
VALUES
	('50','è','2024-12-05 00:00:00');

--®¹
INSERT INTO
	m_status (status_code, status_name, update_datetime) 
VALUES
	('99','®¹','2024-12-05 00:00:00');


--^XNe[u t_task
--@
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(1, 'task1', 1, '2024-12-05', 'admin', '00', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--A
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(2, 'task2', 1, '2024-12-05', 'p_tatsuya', '50', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--B
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(3, 'task3', 1, '2024-12-05', 'p_daisuke', '99', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--C
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(4, 'task4', 2, '2024-12-05', 'admin', '00', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');
	
--D
INSERT INTO
	t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo, create_datetime, update_datetime) 
VALUES
	(5, 'task5', 2, '2024-12-05', 'p_shota', '50', ' ', '2024-12-05 00:00:00', '2024-12-05 00:00:00');