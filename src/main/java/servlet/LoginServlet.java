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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
				//アラートは現在日時から1週間以内に設定
				//TaskCategoryUserStatusDAOクラスのselectLimitDateメソッドを呼び出す
				TaskCategoryUserStatusDAO tcusdao = new TaskCategoryUserStatusDAO();

				//Date型のLimitDateを取り出して変数dateに詰める
				Date date = tcusdao.selectLimitDate(userId);

				//LimitDateをDate型からString型にキャストする
				String strDate = date.toString();
				
				//String型のstrDateをLocalDate型にキャストする
				LocalDate limitDate = LocalDate.parse(strDate);
				
				//タスクの期限から1週間引く
				LocalDate alertDate = limitDate.minusDays(7);
				System.out.println("タスクの期限から1週間引く:" + alertDate);

				//タスクの期限が現在日時から1週間以内だったら警告メッセージを出す
				if (alertDate.isBefore(limitDate)) {
					JFrame frame = new JFrame();
					
					JOptionPane.showMessageDialog(null, "メッセージだよ");

					JOptionPane.showMessageDialog(frame, "タスクの期限が迫っています", "警告",
							JOptionPane.ERROR_MESSAGE);
					
//坂上さんへ
//タスク期限と、タスク期限の1週間前の日付を比較するだけだとアラートになってない気がします。
//A5のタスク期限が1個だけ迫ってる、だとアラートすり抜けます。
//タスク期限がNullだった場合については未着手です。
					
				}
					//メニュー画面に遷移する
					RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
					rd.forward(request, response);
				
			} else {
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
