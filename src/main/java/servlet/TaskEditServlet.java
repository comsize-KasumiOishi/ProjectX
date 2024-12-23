package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.validation.Validation;
import model.dao.TaskCategoryUserStatusDAO;
import model.entity.TaskCategoryUserStatusBean;

/**
 * タスク編集画面で入力された値の妥当性チェックを行った後
 * データベースへアクセスし、タスクの編集を行う
 * 編集が成功すればタスク編集成功画面へ
 * 編集が失敗すればタスク編集失敗画面へ
 * Servlet implementation class TaskEditServlet
 * @author 坂上
 */
@WebServlet("/TaskEditServlet")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * 編集画面で入力された値の各妥当性チェックを行い
	 * また、全てが同じ値であったら編集失敗画面へと遷移する
	 * 
	 * そうでない場合はデータベースへのアクセスを行い
	 * 編集を行い、編集が成功すれば成功画面へ
	 * 失敗したら失敗画面へと遷移する
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//アップデート結果をカウントする変数を宣言
		int count = 0;
		//文字数をカウントする変数を宣言
		int length = 0;
		//画面遷移先のURLをしまう変数を宣言
		String url = null;
		HttpSession session = request.getSession();
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		TaskCategoryUserStatusBean tcusbean = (TaskCategoryUserStatusBean) session.getAttribute("detail");
		Validation check = new Validation();
		try {
			//変更用に入力されたタスク名
			String updateTaskName = request.getParameter("updatetaskname");
			
			//セレクトボックスでカテゴリーIDを取得
			String updatestrCategoryId = request.getParameter("updatecategoryid");
			
			//期限を取得
			String updatestrLimitDate = request.getParameter("updatelimitdate");
			
			
			//セレクトボックスでユーザーIDを取得
			String updateUserId = request.getParameter("updateuserid");
			
			//セレクトボックスでステータスコードを取得
			String updateStatusCode = request.getParameter("updatestatuscode");
			
			//メモを取得
			String updateMemo = request.getParameter("updatememo");
			
			//妥当性チェック
			boolean checkValidation = check.checkValidation(updateTaskName, updatestrCategoryId, updatestrLimitDate, updateUserId, updateStatusCode, updateMemo);

			//urlに登録失敗画面のurlマッピングが代入されていたら画面遷移する
			if (checkValidation = false) {
				url = "task-edit-failure.jsp";
				//転送先のパスを指定して転送処理用オブジェクトを取得
				RequestDispatcher rd = request.getRequestDispatcher(url);
				//リクエストの転送
				rd.forward(request, response);
				return;
			}
			
			//必要な項目のキャスト及びセッション
			
			//int型に変換
			int updateCategoryId = Integer.parseInt(updatestrCategoryId);
			
			
			//変更する期限を格納するLocalDate型の変数limitDateを宣言
			LocalDate limitDate = null;
			//sql.date型の期限を格納する変数Dateを宣言
			java.sql.Date sqlDate = null;
			//strLimitDateをLocalDate型にキャスト
			limitDate = LocalDate.parse(updatestrLimitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			//今日の日付を取得してLocalDate型の変数todayに代入
			LocalDate today = LocalDate.now();
			//todayをString型にキャスト
			String strToday = today.toString();
			//セッションスコープに今日の日付を設定する
			session.setAttribute("strToday", strToday);
			//LocalDate型のlimitDateをsql.date型に変換する
			sqlDate = java.sql.Date.valueOf(limitDate);
			
			//memo妥当性チェック
			if (updateMemo == null || updateMemo.isEmpty()) {
				updateMemo = null;
			}
			

			//			入力された値が元の値と全て同じの場合、編集失敗画面に遷移する
			if (updateTaskName.equals(tcusbean.getTaskName()) &&
					updateCategoryId == tcusbean.getCategoryId() &&
					sqlDate.compareTo(tcusbean.getLimitDate()) == 0 &&
					updateUserId.equals(tcusbean.getUserId()) &&
					updateStatusCode.equals(tcusbean.getStatusCode()) &&
					updateMemo.equals(tcusbean.getMemo())) {
				url = "task-edit-failure.jsp";
			} else {
				//				そうでない場合はBean型に情報を詰める
				TaskCategoryUserStatusBean updatetcus = new TaskCategoryUserStatusBean();
				updatetcus.setTaskName(updateTaskName);
				updatetcus.setCategoryId(updateCategoryId);
				updatetcus.setLimitDate(sqlDate);
				updatetcus.setUserId(updateUserId);
				updatetcus.setStatusCode(updateStatusCode);
				updatetcus.setMemo(updateMemo);
				updatetcus.setTaskId(tcusbean.getTaskId());
				//				引数にBean型を指定し、updateメソッドを呼ぶ
				count = dao.update(updatetcus);
				if (count == 0) {
					url = "task-edit-failure.jsp";
				} else {
					url = "task-edit-result.jsp";
				}
			}
		} catch (ClassNotFoundException e) {
			url = "task-edit-failure.jsp";
		} catch (SQLException e) {
			url = "task-edit-failure.jsp";
		} catch (NullPointerException e) {
			System.out.println("ぬるぽ");
			url = "task-edit-failure.jsp";
		} catch (NumberFormatException e) {
			url = "task-edit-failure.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
