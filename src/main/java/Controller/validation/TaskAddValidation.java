package Controller.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import model.entity.TaskCategoryUserStatusBean;

public class TaskAddValidation {
	public boolean taskAddValidation(
			String taskName,
			String category,
			String strLimitDate,
			String user,
			String status,
			String memo,
			List<TaskCategoryUserStatusBean> userList) {

		//妥当性チェックの結果を格納するboolean型の変数flagを宣言
		boolean flag = true;

		//文字数チェックが必要な項目の文字数をカウントするint型の変数countを宣言
		int count = 0;

		//taskName妥当性チェック
		if (taskName == null || taskName.isEmpty()) {
			//未入力チェック
			flag = false;
		}
		try {
			//taskNameの文字数をcountに代入
			count = taskName.length();
			if (count < 0 || count > 50) {
				//文字数チェック
				flag = false;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		//範囲チェック
		if (tag(taskName)) {
			flag = false;
		}

		//categoryId妥当性チェック
		//categoryIdとcategoryNameを格納する変数を宣言
		String categoryName = null;
		int categoryId = 0;
		//未入力チェック
		try {
			//categoryにカンマが含まれてるか確認する
			if (!(category.contains(","))) {
				flag = false;
			}
			//categoryをカンマで区切る
			String[] categoryArray = category.split(",");
			categoryName = categoryArray[0];
			categoryId = Integer.parseInt(categoryArray[1]);
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			flag = false;
		}
		//範囲チェック
		if (!(categoryId == 1 || categoryId == 2)) {//修正
			flag = false;
		}

		//strLimitDate妥当性チェック
		//登録する期限を格納するLocalDate型の変数limitDateを宣言
		LocalDate limitDate = null;
		//sql.date型の期限を格納する変数Dateを宣言
		java.sql.Date sqlDate = null;
		//空文字チェック
		if (strLimitDate == null || strLimitDate.isEmpty()) {
			strLimitDate = null;
		} else {
			try {
				//strLimitDateをLocalDate型にキャスト
				limitDate = LocalDate.parse(strLimitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")); //Date型ではない？ localdate型を使う？ 補足資料読む
				//範囲チェック
				//今日の日付を取得してLocalDate型の変数todayに代入
				LocalDate today = LocalDate.now();
				//今日の日付と入力された日付を比較する
				if (today.isAfter(limitDate)) {
					flag = false;
				}

				//LocalDate型のlimitDateをsql.date型に変換する
				sqlDate = java.sql.Date.valueOf(limitDate);
			} catch (DateTimeParseException | NullPointerException e) {
				e.printStackTrace();
				limitDate = null;
			}
		}

		//userId妥当性チェック
		//userNameとuserIdを格納する変数を宣言
		String userName = null;
		String userId = null;
		//未入力チェック
		try {
			//userIdにカンマが含まれてるか確認する
			if (!(user.contains(","))) {
				flag = false;
			}
			//userをカンマで区切る
			String[] userArray = user.split(",");
			userName = userArray[0];
			userId = userArray[1];

			//範囲チェック(ユーザマスタの情報とuserIdが一致しているか調べる)
			for (TaskCategoryUserStatusBean tcusbean : userList) {
				if (userName.equals(tcusbean.getUserId()) || userId.equals(tcusbean.getUserName())) {
					break;
				} else {
					flag = false;
				}
			}
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			flag = false;
		}

		//statusCode妥当性チェック
		//statusNameとstatusCodeを格納する変数を宣言
		String statusName = null;
		String statusCode = null;
		//未入力チェック
		try {
			//statusにカンマが含まれてるか確認する
			if (!(status.contains(","))) {
				flag = false;
			}
			//statusをカンマで区切る
			String[] statusArray = status.split(",");
			statusName = statusArray[0];
			statusCode = statusArray[1];

			//statusCodeの文字数をcountに代入
			count = statusCode.length();
			//文字数チェック
			if (count < 0 || count > 2) {
				flag = false;
			}
			//範囲チェック 修正
			if (!(statusCode.equals("00") || statusCode.equals("50") || statusCode.equals("99"))) {
				flag = false;
			}
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			flag = false;
		}

		//memo妥当性チェック
		//空文字チェック
		if (memo == null || memo.isEmpty()) {
			memo = null;
		} else {
			try {
				//memoの文字数をcountに代入
				count = memo.length();
				//文字数チェック
				if (count > 100) {
					flag = false;
				}
				//範囲チェック
				if (tag(memo)) {
					flag = false;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}

	public static boolean tag(String input) {
		String pattern = ".*<[^>]+>.*";
		return input.matches(pattern);
	}

	//パラメータの値をBeanに詰めるメソッド
	public static TaskCategoryUserStatusBean bean(
			String taskName,
			String category,
			String strLimitDate,
			String user,
			String status,
			String memo) {

		//categoryをカンマで区切る
		String[] categoryArray = category.split(",");
		String categoryName = categoryArray[0];
		int categoryId = Integer.parseInt(categoryArray[1]);

		//sql.date型の期限を格納する変数Dateを宣言
		java.sql.Date sqlDate = null;
		try {
			//strLimitDateをLocalDate型にキャスト
			LocalDate limitDate = LocalDate.parse(strLimitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			sqlDate = java.sql.Date.valueOf(limitDate);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}

		//userをカンマで区切る
		String[] userArray = user.split(",");
		String userName = userArray[0];
		String userId = userArray[1];

		//statusをカンマで区切る
		String[] statusArray = status.split(",");
		String statusName = statusArray[0];
		String statusCode = statusArray[1];

		//TaskCategoryUserStatusBeanのインスタンスを作成
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();

		//リクエストパラメータの値をBeanに詰める
		tcusbean.setTaskName(taskName);
		tcusbean.setCategoryId(categoryId);
		tcusbean.setLimitDate(sqlDate);
		tcusbean.setUserId(userId);
		tcusbean.setStatusCode(statusCode);
		tcusbean.setMemo(memo);

		return tcusbean;

	}

}
