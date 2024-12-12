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
			
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		//取得した詳細画面用データをセッションにセット
		session.setAttribute("detail", tcusbean);
		//取得したコメントデータをセッションにセット
		session.setAttribute("commentList", commentList);	
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
		TaskCategoryUserStatusBean tcus = (TaskCategoryUserStatusBean) session.getAttribute("detail");
		String repName = tcus.getUserName();
		
		//ログイン者とタスク担当者が一致しているか調べる
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
