package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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

		//ログイン画面で入力されたユーザIDとパスワードを取得する
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");

		//ユーザIDまたはパスワードが未入力だった場合ログイン失敗画面に遷移する
		if (userId.isEmpty() || password.isEmpty()) {
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

				//タスクの期限が迫っているユーザに対して、ログイン時にアラートを出す
				//アラートはタスクの期限から1週間以内に設定
				//アラートを発したタスクの数をカウント
				int alertCount = 0;
				
				//TaskCategoryUserStatusDAOインスタンスを生成する
				TaskCategoryUserStatusDAO tcusdao = new TaskCategoryUserStatusDAO();

				//Date型のリストに、limitDateListメソッドで取得したリストを格納する
				//一定の期限のLimitDateがあればbooleanを返す
				List<Date> limitDateList = tcusdao.limitDateList(userId);

				for (Date date : limitDateList) {
					//LimitDateをDate型からString型にキャストする
					String strDate = date.toString();

					//String型のstrDateをLocalDate型にキャストする
					LocalDate limitDate = LocalDate.parse(strDate);

					//比較する現在日時を呼ぶ
					LocalDate currentDate = LocalDate.now();

					//もし期限の七日前が現在日時より前だったらtrue
					if (currentDate.isBefore(limitDate.minusDays(7))) {
						//注意喚起無し
					} else {
						//注意喚起あり
						alertCount++;
					}
				}
				
				session.setAttribute("alert", alertCount);
			
			} else {
				//ログイン認証に失敗した場合はログイン失敗画面に遷移する
				RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//TaskCategoryUserStatusDAOで作成したリストをセッションに詰める
		//これは登録機能などでカテゴリーのプルダウンを表示させる際に使用する
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
		//メニュー画面に遷移する
		RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
		rd.forward(request, response);
	}

}
