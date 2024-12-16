package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import model.dao.TaskUserCommentDAO;
import model.entity.TaskCategoryUserStatusBean;
import model.entity.TaskUserCommentBean;

/**
 * 
 * タスクの詳細画面を表示するメソッド
 * Servlet implementation class TaskDetailServlet
 * @author 坂上
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
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response
	 * 一覧リストから送られてきた該当データのタスクIDを元に
	 * 該当データのコメントを含めた詳細表示のためのデータをセッションに入れる
	 * タスク詳細画面へと遷移する
	 * 
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//一覧リストから送られてきた該当データのタスクIDを取得
		String strTaskId = request.getParameter("taskId");
		//int型に型変換
		int taskId = Integer.parseInt(strTaskId);
		//各DAOとBeanを呼び出し
		//タスク詳細画面用
		TaskCategoryUserStatusBean tcusbean = new TaskCategoryUserStatusBean();
		TaskCategoryUserStatusDAO tcusdao = new TaskCategoryUserStatusDAO();
		//タスクコメント用
		List<TaskUserCommentBean> commentList = new ArrayList<TaskUserCommentBean>();
		TaskUserCommentDAO tucdao = new TaskUserCommentDAO();
		try {
			//取得したタスクIDを用いて詳細画面用のBean型を取得
			tcusbean = tcusdao.detail(taskId);
			//取得したタスクIDを用いて詳細画面用のコメント表示を取得
			commentList = tucdao.commentList(taskId);
			
			//例外が起きた場合はタスク一覧画面へと遷移する
		} catch (ClassNotFoundException e) {
			RequestDispatcher rd = request.getRequestDispatcher("TaskListServlet");
			rd.forward(request, response);	
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("TaskListServlet");
			rd.forward(request, response);	
		}
		
		HttpSession session = request.getSession();
		//取得した詳細画面用データをセッションにセット
		session.setAttribute("detail", tcusbean);
		//取得したコメントデータをセッションにセット
		session.setAttribute("commentList", commentList);
		//取得したタスクIDをセッションにセット
		session.setAttribute("taskId", taskId);
		//タスク詳細画面に遷移する
		RequestDispatcher rd = request.getRequestDispatcher("task-detail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * タスク詳細画面で表示されたデータのタスク担当者とログインユーザーが同じであれば
	 * タスク編集画面へと遷移する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//現在のログインユーザーの取得
		String userName = (String)session.getAttribute("userName");
		
		//タスク担当者の取得
		TaskCategoryUserStatusBean tcus = (TaskCategoryUserStatusBean) session.getAttribute("detail");
		String repName = tcus.getUserName();
		
		//ログインユーザーとタスク担当者が一致しているか調べる
		//一致していない場合はタスクリストに戻す
		if(repName.equals(userName)) {
			session.setAttribute("detailtable", tcus);
			RequestDispatcher rd = request.getRequestDispatcher("task-edit.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("TaskListServlet");
			rd.forward(request, response);	
		}
	}

}
