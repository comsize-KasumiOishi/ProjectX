package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.validation.TaskAddValidation;
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

		//セッションスコープに設定したuserListを取得
		HttpSession session = request.getSession();
		List<TaskCategoryUserStatusBean> userList = (List<TaskCategoryUserStatusBean>) session.getAttribute("userList");

		//TaskAddValidationクラスのインスタンスを作成
		TaskAddValidation taskaddvalidation = new TaskAddValidation();

		//妥当性チェックの結果を格納するboolean型の変数flagを宣言
		boolean flag = true;

		//TaskAddValidationクラスのtaskAddValidationメソッドを呼び出す
		flag = taskaddvalidation.taskAddValidation(taskName, category, strLimitDate, user, status, memo, userList);

		//妥当性チェックの結果を格納する変数flagの値がfalseだったら
		//登録失敗画面に遷移する
		if (flag == false) {
			//登録失敗画面のurlマッピングを変数urlに格納する
			url = "task-register-failure.jsp";
			
			//転送先のパスを指定して転送処理用オブジェクトを取得
			RequestDispatcher rd = request.getRequestDispatcher(url);

			//リクエストの転送
			rd.forward(request, response);
			return;
		}
		
		//TaskCategoryUserStatusBean型の変数tcusbeanを宣言
		TaskCategoryUserStatusBean tcusbean = null;
		
		//TaskAddValidationクラスのbeanメソッドを呼び出す
		tcusbean = TaskAddValidation.bean(taskName, category, strLimitDate, user, status, memo);

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
