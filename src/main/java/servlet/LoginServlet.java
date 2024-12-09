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
import model.dao.UserDAO;
import model.entity.TaskCategoryUserStatusBean;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		
		UserDAO userdao = new UserDAO();
		
		try {

			UserBean userbean =userdao.logincheck(userId, password);
			
			if (userbean != null) {
				
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				session.setAttribute("password", password);
				session.setAttribute("userName", userbean.getUserName());
				
				RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
				rd.forward(request, response);
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DAOで作成したリストをセッションに詰める
		TaskCategoryUserStatusDAO tcusdao = new TaskCategoryUserStatusDAO();
		List<TaskCategoryUserStatusBean> categoryList = null;
		List<TaskCategoryUserStatusBean> userList = null;
		List<TaskCategoryUserStatusBean> statusList = null;
		
		try {
			categoryList = tcusdao.selectCategoryId();
			userList = tcusdao.selectUserId();
			statusList = tcusdao.selectStatusCode();
			
			HttpSession session = request.getSession();
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("userList", userList);
			session.setAttribute("statusList", statusList);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
