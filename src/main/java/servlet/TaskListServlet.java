package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class ItemListServlet
 */
@WebServlet("/TaskListServlet")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");


		HttpSession session = request.getSession();
		
		List<TaskCategoryUserStatusBean> taskList = null;
		List<TaskUserCommentBean> commentCounts = null;

		// DAOの生成
		TaskCategoryUserStatusDAO listDao = new TaskCategoryUserStatusDAO();
		TaskUserCommentDAO commentDao = new TaskUserCommentDAO();

		try {
			// DAOの利用
			taskList = listDao.selectAll();
			commentCounts = commentDao.commentCount();
			session.setAttribute("commentCounts", commentCounts);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (taskList != null) {
		// リクエストスコープへの属性の設定
		session.setAttribute("taskList", taskList);
		RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
		rd.forward(request, response);
		}
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}