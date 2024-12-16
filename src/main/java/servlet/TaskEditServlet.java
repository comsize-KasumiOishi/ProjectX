package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
		//変更用に入力されたタスク名
		try {
			String updateTaskName = request.getParameter("updatetaskname");
			//updateTaskName妥当性チェック
			//taskNameの文字数をcountに代入
			length = updateTaskName.length();
			if (updateTaskName.isEmpty()) {
				//未入力チェック
				url = "task-edit-failure.jsp";
			} else if (count < 0 || count > 50) {
				//文字数チェック
				url = "task-edit-failure.jsp";
			}

			//セレクトボックスでカテゴリーIDを取得
			String updatestrCategoryId = request.getParameter("updatecategoryid");
			//カテゴリーIDをint型に型変換
			int updateCategoryId = Integer.parseInt(updatestrCategoryId);
			//カテゴリーIDの妥当性チェック
			if(!(updateCategoryId == 1 || updateCategoryId == 2)) {
				url = "task-edit-failure.jsp";
			}
			
			
			//期限を取得
			String updatestrLimitDate = request.getParameter("updatelimitdate");
			//strLimitDate妥当性チェック
			//変更する期限を格納するLocalDate型の変数limitDateを宣言
			LocalDate limitDate = null;
			//sql.date型の期限を格納する変数Dateを宣言
			java.sql.Date sqlDate = null;
			try {
				//strLimitDateをLocalDate型にキャスト
				limitDate = LocalDate.parse(updatestrLimitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				//今日の日付を取得してLocalDate型の変数todayに代入
				LocalDate today = LocalDate.now();
				//今日の日付と入力された日付を比較する
				if(today.isAfter(limitDate)) {
					RequestDispatcher rd = request.getRequestDispatcher("task-edit-failure.jsp");
					rd.forward(request, response);
				}
				//todayをString型にキャスト
				String strToday = today.toString();
				
				//セッションスコープに今日の日付を設定する
				session.setAttribute("strToday", strToday);
				
				//LocalDate型のlimitDateをsql.date型に変換する
				sqlDate = java.sql.Date.valueOf(limitDate);
			} catch (DateTimeParseException | NullPointerException e) {
				url = "task-edit-failure.jsp";
			}
			
			
			
			//セレクトボックスでユーザーIDを取得
			String updateUserId = request.getParameter("updateuserid");
			//updateUserIdの妥当性チェック
			//updateUserIdの文字数をcountに代入
			count = updateUserId.length();
			//文字数チェック
			if (count < 0 || count > 24) {
				url = "task-edit-failure.jsp";
			}
			
			
			//セレクトボックスでステータスコードを取得
			String updateStatusCode = request.getParameter("updatestatuscode");
			//statusCodeの文字数をcountに代入
			count = updateStatusCode.length();
			//文字数チェック
			if (count < 0 || count > 2) {
				url = "task-edit-failure.jsp";
			}
			//範囲チェック
			if(!(updateStatusCode.equals("00") || updateStatusCode.equals("50") || updateStatusCode.equals("99"))) {
				url = "task-edit-failure.jsp";
			}
			
			
			//メモを取得
			String updateMemo = request.getParameter("updatememo");
			//memo妥当性チェック
			//memoの文字数をcountに代入
			count = updateMemo.length();
			//文字数チェック
			if (count > 100) {
				url = "task-edit-failure.jsp";
			}
			//空文字チェック
			if(updateMemo.isEmpty()) {
				updateMemo = null;
			}
			
			
			//urlに登録失敗画面のurlマッピングが代入されていたら画面遷移する
			if(url == "task-edit-failure.jsp"){
				//転送先のパスを指定して転送処理用オブジェクトを取得
				RequestDispatcher rd = request.getRequestDispatcher(url);

				//リクエストの転送
				rd.forward(request, response);
				return;
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
				if(count == 0) {
					url = "task-edit-failure.jsp"; 
				}else {
					url = "task-edit-result.jsp";
				}
			}
		} catch (ClassNotFoundException e) {
			url = "task-edit-failure.jsp";
		} catch (SQLException e) {
			url = "task-edit-failure.jsp";
		} catch (NullPointerException e) {
			url = "task-edit-failure.jsp";
		}catch(NumberFormatException e) {
			url = "task-edit-failure.jsp";
		}
			
		RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		

	}

}
