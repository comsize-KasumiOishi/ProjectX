package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

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
		String updateTaskName = request.getParameter("updatetaskname");
		try {
			//空文字をnullに変換
			if (updateTaskName.equals("")) {
				updateTaskName = null;
			}
			//セレクトボックスでカテゴリーIDを取得
			String updatestrCategoryId = request.getParameter("updatecategoryid");
			//カテゴリーIDをint型に型変換
			int updateCategoryId = Integer.parseInt(updatestrCategoryId);
			//期限を取得
			long updateLimitDate = request.getDateHeader("updatelimitdate");
			//セレクトボックスでユーザーIDを取得
			String updateUserId = request.getParameter("updateuserid");
			//セレクトボックスでステータスコードを取得
			String updateStatusCode = request.getParameter("updatestatuscode");
			//確認用
//			System.out.println("サーブレット内: " + updateStatusCode);
//			メモを取得
			String updateMemo = request.getParameter("updatememo");
//			long型で取得した期限をDate型に変換
			Date date = new Date(updateLimitDate);

//			入力された値が元の値と全て同じの場合、編集失敗画面に遷移する
			if (updateTaskName.equals(tcusbean.getTaskName()) &&
					updateCategoryId == tcusbean.getCategoryId() &&
					date.compareTo(tcusbean.getLimitDate()) == 0 &&
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
				updatetcus.setLimitDate(date);
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
