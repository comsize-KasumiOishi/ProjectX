package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskCategoryUserStatusDAO;
import model.entity.TaskCategoryUserStatusBean;

/**
 * タスク登録を行うためのサーブレット
 * @author 篠杏樹
 */
/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/TaskAddServlet")
public class TaskAddServlet extends HttpServlet {

	/**
	 * プルダウンで表示する項目を取得するメソッド
	 * @throws ClassNotFoundException,SQLException
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TaskCategoryUserStatusDAOクラスのインスタンスを作成
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();

		//カテゴリマスタの情報を格納するリストを作成
		List<TaskCategoryUserStatusBean> categoryList = new ArrayList<>();

		//ユーザマスタの情報を格納するリストを作成
		List<TaskCategoryUserStatusBean> userList = new ArrayList<>();

		//ステータスマスタの情報を格納するリストを作成
		List<TaskCategoryUserStatusBean> statusList = new ArrayList<>();

		try {
			//TaskCategoryUserStatusDAOクラスのselectCategoryIdメソッドを呼び出す
			categoryList = dao.selectCategoryId();

			//TaskCategoryUserStatusDAOクラスのselectUserIdメソッドを呼び出す
			userList = dao.selectUserId();

			//TaskCategoryUserStatusDAOクラスのselectStatusCodeメソッドを呼び出す
			statusList = dao.selectStatusCode();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//今日の日付を取得してLocalDate型の変数todayに代入
		LocalDate today = LocalDate.now();

		//todayをString型にキャスト
		String strToday = today.toString();

		//セッションスコープにリストと今日の日付を設定する
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("userList", userList);
		session.setAttribute("statusList", statusList);
		session.setAttribute("strToday", strToday);

		//転送先のパスを指定して転送処理用オブジェクトを取得
		RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");

		//リクエストの転送
		rd.forward(request, response);
	}

	/**
	 * タスク情報を登録するメソッド
	 * @throws NullPointerException,DateTimeParseException,ClassNotFoundException,
	 * SQLException
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得
		String taskName = request.getParameter("taskName");
		String category = request.getParameter("category");
		String strLimitDate = request.getParameter("limitDate");
		String user = request.getParameter("user");
		String status = request.getParameter("status");
		String memo = request.getParameter("memo");

		//遷移先のURLマッピングを格納するString型の変数urlを宣言
		String url = null;

		//文字数チェックが必要な項目の文字数をカウントするint型の変数countを宣言
		int count = 0;

		//taskName妥当性チェック
		if (taskName == null || taskName.isEmpty()) {
			//未入力チェック
			url = "task-register-failure.jsp";
		}
		try {
			//taskNameの文字数をcountに代入
			count = taskName.length();
			if (count < 0 || count > 50) {
				//文字数チェック
				url = "task-register-failure.jsp";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		//categoryId妥当性チェック
		//categoryIdとcategoryNameを格納する変数を宣言
		String categoryName = null;
		int categoryId = 0;
			//未入力チェック
			try {
				//categoryにカンマが含まれてるか確認する
				if (!(category.contains(","))) {
					url = "task-register-failure.jsp";
				}
				//categoryをカンマで区切る
				String[] categoryArray = category.split(",");
				categoryName = categoryArray[0];
				categoryId = Integer.parseInt(categoryArray[1]);
			} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				url = "task-register-failure.jsp";
			}
			//範囲チェック
			if (!(categoryId == 1 || categoryId == 2)) {
				url = "task-register-failure.jsp";
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
					url = "task-register-failure.jsp";
				}

				//LocalDate型のlimitDateをsql.date型に変換する
				sqlDate = java.sql.Date.valueOf(limitDate);
			} catch (DateTimeParseException | NullPointerException e) {
				e.printStackTrace();
				limitDate = null;
			}
		}

		//userId妥当性チェック
		//セッションスコープに設定したuserListを取得
		HttpSession session = request.getSession();
		List<TaskCategoryUserStatusBean> userList = (List<TaskCategoryUserStatusBean>) session.getAttribute("userList");
		//userNameとuserIdを格納する変数を宣言
		String userName = null;
		String userId = null;
		//未入力チェック
		try {
			//userIdにカンマが含まれてるか確認する
			if (!(user.contains(","))) {
				url = "task-register-failure.jsp";
			}
			//userをカンマで区切る
			String[] userArray = user.split(",");
			userName = userArray[0];
			userId = userArray[1];

			//範囲チェック(ユーザマスタの情報とuserIdが一致しているか調べる)
			for (TaskCategoryUserStatusBean tcusbean : userList) {
				if(userName.equals(tcusbean.getUserId()) || userId.equals(tcusbean.getUserName())) {
					break;
				}else{
					url = "task-register-failure.jsp";
				}
			}
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			url = "task-register-failure.jsp";
		}

		//statusCode妥当性チェック
		//statusNameとstatusCodeを格納する変数を宣言
		String statusName = null;
		String statusCode = null;
		//未入力チェック
		try {
			//statusにカンマが含まれてるか確認する
			if (!(status.contains(","))) {
				url = "task-register-failure.jsp";
			}
			//statusをカンマで区切る
			String[] statusArray = status.split(",");
			statusName = statusArray[0];
			statusCode = statusArray[1];
			
			//statusCodeの文字数をcountに代入
			count = statusCode.length();
			//文字数チェック
			if (count < 0 || count > 2) {
				url = "task-register-failure.jsp";
			}
			//範囲チェック
			if (!(statusCode.equals("00") || statusCode.equals("50") || statusCode.equals("99"))) {
				url = "task-register-failure.jsp";
			}
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			url = "task-register-failure.jsp";
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
					url = "task-register-failure.jsp";
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		//urlに登録失敗画面のurlマッピングが代入されていたら画面遷移する
		if (url == "task-register-failure.jsp") {
			//転送先のパスを指定して転送処理用オブジェクトを取得
			RequestDispatcher rd = request.getRequestDispatcher(url);

			//リクエストの転送
			rd.forward(request, response);
			return;
		}

		//TaskCategoryUserStatusBeanのインスタンスを作成
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();

		//リクエストパラメータの値をBeanに詰める
		tcusbean.setTaskName(taskName);
		tcusbean.setCategoryId(categoryId);
		tcusbean.setLimitDate(sqlDate);
		tcusbean.setUserId(userId);
		tcusbean.setStatusCode(statusCode);
		tcusbean.setMemo(memo);

		//TaskCategoryUserStatusDAOクラスのインスタンスを作成
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();

		//登録件数を格納するint型の変数resを宣言
		int res = 0;

		//TaskCategoryUserStatusDAOクラスのinsertメソッドを呼び出す
		try {
			res = dao.insert(tcusbean);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			url = "task-register-failure.jsp";
		}

		//resの値に応じて遷移する画面を変更する
		if (res == 0) {
			url = "task-register-failure.jsp";
		} else {
			url = "task-register-success.jsp";
		}

		//転送先のパスを指定して転送処理用オブジェクトを取得
		RequestDispatcher rd = request.getRequestDispatcher(url);

		//リクエストの転送
		rd.forward(request, response);
	}

}
