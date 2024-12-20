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
 * タスク一覧機能に関するServlet
 * @author 柳沢
 */

@WebServlet("/TaskListServlet")
public class TaskListServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			session.setAttribute("taskList", taskList);
			session.setAttribute("commentCounts", commentCounts);
			RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
			rd.forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}