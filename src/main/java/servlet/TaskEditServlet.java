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
 * Servlet implementation class TaskEditServlet
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//アップデート結果をカウントする変数
		int count = 0;
		HttpSession session = request.getSession();
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		TaskCategoryUserStatusBean tcusbean = (TaskCategoryUserStatusBean) session.getAttribute("detail");
		//変更用に入力されたタスク名
		try {
			String updateTaskName = request.getParameter("updatetaskname");

			//セレクトボックスでカテゴリーIDを取得
			String updatestrCategoryId = request.getParameter("updatecategoryid");
			//カテゴリーIDをint型に型変換
			int updateCategoryId = Integer.parseInt(updatestrCategoryId);
			
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
				e.printStackTrace();
			}
			
			
			
			//セレクトボックスでユーザーIDを取得
			String updateUserId = request.getParameter("updateuserid");
			//セレクトボックスでステータスコードを取得
			String updateStatusCode = request.getParameter("updatestatuscode");
			//確認用
			//			System.out.println("サーブレット内: " + updateStatusCode);
			//			メモを取得
			String updateMemo = request.getParameter("updatememo");
			if (updateMemo.equals("")) {
				updateMemo = null;
			}
		

			//			入力された値が元の値と全て同じの場合、編集失敗画面に遷移する
			if (updateTaskName.equals(tcusbean.getTaskName()) &&
					updateCategoryId == tcusbean.getCategoryId() &&
					sqlDate.compareTo(tcusbean.getLimitDate()) == 0 &&
					updateUserId.equals(tcusbean.getUserId()) &&
					updateStatusCode.equals(tcusbean.getStatusCode()) &&
					updateMemo.equals(tcusbean.getMemo())) {
				RequestDispatcher rd = request.getRequestDispatcher("task-edit-failure.jsp");
				rd.forward(request, response);
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
			}
		} catch (ClassNotFoundException e) {
			RequestDispatcher rd = request.getRequestDispatcher("task-edit-failure.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("task-edit-failure.jsp");
			rd.forward(request, response);
		} catch (NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("task-edit-failure.jsp");
			rd.forward(request, response);
		}
		if (count == 0) {
			//編集に失敗した場合
			RequestDispatcher rd = request.getRequestDispatcher("task-edit-failure.jsp");
			rd.forward(request, response);
		} else {
			//編集に成功した場合
			RequestDispatcher rd = request.getRequestDispatcher("task-edit-result.jsp");
			rd.forward(request, response);
		}

	}

}
