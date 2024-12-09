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
		
		//ログイン画面で入力されたユーザIDとパスワードを取得する
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		
		//ユーザIDまたはパスワードが未入力だった場合ログイン失敗画面に遷移する
		if(userId.isEmpty() || password.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
			rd.forward(request, response);
		}
		
		//UserDAOのインスタンスを生成する
		UserDAO userdao = new UserDAO();
		
		try {
			//UserBean型の変数にUserDAOのログイン認証メソッドを格納する
			UserBean userbean = userdao.logincheck(userId, password);
			
			//UserBean型の変数がnullでない場合はログイン認証に成功する
			if (userbean != null) {
				
				//ユーザIDとパスワードとユーザー名をセッションに格納する
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				session.setAttribute("password", password);
				session.setAttribute("userName", userbean.getUserName());
				
				//メニュー画面に遷移する
				RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
				rd.forward(request, response);
			}
			else {
				//ログイン認証に失敗した場合はログイン失敗画面に遷移する
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
