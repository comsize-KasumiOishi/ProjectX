package servlet;

import java.io.IOException;
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
 * Servlet implementation class TaskDeleteServlet
 * 
 * タスクの削除機能に関するServlet
 * @author 坂上
 */
@WebServlet("/TaskDeleteServlet")




public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskDeleteServlet() {
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
	 * 指定されたタスクIDを元にデータベースへ接続を行い、削除を行う
	 * 削除を行う前にログイン者とタスク担当者が同じかのチェックを行う
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		//ログインユーザーの取得
		String userName = (String) session.getAttribute("userName");

		//タスク担当者の取得
		TaskCategoryUserStatusBean tcusbean = (TaskCategoryUserStatusBean) session.getAttribute("detail");
		String repName = tcusbean.getUserName();

		//ログイン者とタスク担当者が一致しているか調べる
		//一致していない場合はタスクリストに戻す
		if (!(repName.equals(userName))) {
			RequestDispatcher rd = request.getRequestDispatcher("TaskListServlet");
			rd.forward(request, response);
		}

		//セッションからタスクIDを取得する
		int taskId = (int) session.getAttribute("taskId");

		//TaskCategoryUserStatusDAOのインスタンスを生成する
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();

		int count = 0;

		try {
			//削除に成功した件数を変数countに格納する
			count = dao.delete(taskId);

			if (count != 0) {

				//変数countが0でない場合requestにcountを格納する
				request.setAttribute("count", count);

				//タスク削除成功画面に遷移する
				RequestDispatcher rd = request.getRequestDispatcher("task-delete-success.jsp");
				rd.forward(request, response);

			} else {
				//上記以外の場合タスク削除失敗画面に遷移する
				RequestDispatcher rd = request.getRequestDispatcher("task-delete-failure.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException | ClassNotFoundException e) {
			RequestDispatcher rd = request.getRequestDispatcher("task-delete-failure.jsp");
			rd.forward(request, response);
		}

	}

}
