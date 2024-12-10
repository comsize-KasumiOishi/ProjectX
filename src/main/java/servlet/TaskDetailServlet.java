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
 * Servlet implementation class TaskDetailServlet
 */
@WebServlet("/TaskDetailServlet")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
       
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//一覧リストから送られてきた該当データのタスクIDを取得
		String strTaskId = request.getParameter("taskid");
		//int型に型変換
		int taskId = Integer.parseInt(strTaskId);
		//各DAOとBeanを呼び出し
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		TaskCategoryUserStatusDAO dao = new TaskCategoryUserStatusDAO();
		try {
			//取得したタスクIDを用いて詳細画面用のBean型を取得
			tcusbean = dao.detail(taskId);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//取得した詳細画面用データをセッションにセット
		HttpSession session = request.getSession();
		session.setAttribute("detail", tcusbean);
		//タスク詳細画面に遷移する
		RequestDispatcher rd = request.getRequestDispatcher("task-detail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		TaskCategoryUserStatusBean tcusbean = (TaskCategoryUserStatusBean) session.getAttribute("detail");
		String repName = tcusbean.getUserName();
		
		//ログイン者とタスク担当者が一致しているか調べる
		//一致していない場合はタスクリストに戻す
		if(repName.equals(userName)) {
			RequestDispatcher rd = request.getRequestDispatcher("task-edit.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("TaskListServlet");
			rd.forward(request, response);	
		}
	}

}
