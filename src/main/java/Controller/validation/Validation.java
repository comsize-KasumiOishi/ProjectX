package Controller.validation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import model.dao.TaskCategoryUserStatusDAO;
import model.entity.TaskCategoryUserStatusBean;

public class Validation {

	public boolean checkValidation(
			String checkTaskName, 
			String checkCategoryId,
			String checkLimitDate,
			String checkUserId,
			String checkStatusCode,
			String checkMemo		) throws ClassNotFoundException, SQLException {
		
		boolean check = true;	//妥当性チェック用boolean変数
		int length = 0;		//文字数カウント用int
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO(); //妥当性チェック用dao
		TaskCategoryUserStatusBean bean = new TaskCategoryUserStatusBean(); 
		
		//タスク名妥当性チェック(文字数、未入力)
		length = checkTaskName.length();
		if (checkTaskName.isEmpty()) {
			//未入力チェック
			check = false;
		} else if (length < 0 || length > 50) {
			//文字数チェック
			check = false;
		}
		
		//カテゴリーIDの妥当性チェック
		//int型に変換
		int categoryId = Integer.parseInt(checkCategoryId);
		List<TaskCategoryUserStatusBean> categoryList;
		
			int checkCount = 0; //妥当性チェック用int変数
			categoryList = dao.selectCategoryId();
			for(TaskCategoryUserStatusBean categorybean : categoryList) {
				if(categoryId == categorybean.getCategoryId()) {
					checkCount++;
				}
			}
			if(checkCount == 0) {
				check = false;
			}
		
		
		//期限の妥当性チェック
		//変更する期限を格納するLocalDate型の変数limitDateを宣言
		LocalDate limitDate = null;
		//sql.date型の期限を格納する変数Dateを宣言
		java.sql.Date sqlDate = null;
		if (checkLimitDate == null || checkLimitDate.isEmpty()) {
			check = false;
		} else {
			try {
				//strLimitDateをLocalDate型にキャスト
				limitDate = LocalDate.parse(checkLimitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				//今日の日付を取得してLocalDate型の変数todayに代入
				LocalDate today = LocalDate.now();
				//今日の日付と入力された日付を比較する
				if (today.isAfter(limitDate)) {
					check = false;
				}
				//LocalDate型のlimitDateをsql.date型に変換する
				sqlDate = java.sql.Date.valueOf(limitDate);
			} catch (DateTimeParseException | NullPointerException e) {
				check = false;
			}
		}
		
		//updateUserIdの文字数をcountに代入
		length  = checkUserId.length();
		//文字数チェック
		if (length < 0 || length > 24) {
			check = false;
		}
		
		//statusCodeの文字数をcountに代入
	length = checkStatusCode.length();
		//文字数チェック
		if (length < 0 || length > 2) {
			check = false;
		}
		//範囲チェック
		
		List<TaskCategoryUserStatusBean> statusList;
		
			statusList = dao.selectStatusCode();
			 checkCount = 0; //妥当性チェック用int変数
			for(TaskCategoryUserStatusBean statusbean : statusList) {
				if (checkStatusCode.equals(statusbean.getStatusCode())) {
					checkCount++;
				}
			}
			
			if(checkCount == 0) {
				check = false;
			}
		
		
		if (checkMemo == null || checkMemo.isEmpty()) {
			checkMemo = null;
		} else {
			//memoの文字数をcountに代入
			length = checkMemo.length();
			//文字数チェック
			if (length > 100) {
				check = false;
			}
		}
		
		
		
		
		return check;
	}

}
