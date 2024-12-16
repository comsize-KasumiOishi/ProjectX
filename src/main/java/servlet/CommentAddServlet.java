package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskUserCommentDAO;
import model.entity.TaskUserCommentBean;

/**
 * Servlet implementation class CommentAddServlet
 */
@WebServlet("/CommentAddServlet")
public class CommentAddServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * コメントをデータベースに登録するクラスです
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得
		String comment = request.getParameter("comment");
		String strTaskId = request.getParameter("taskid");
		String userId = request.getParameter("userid");

		//遷移先のURLマッピングを格納するString型の変数urlを宣言
		String url = null;

		//文字数チェックが必要な項目の文字数をカウントするint型の変数countを宣言
		int count = 0;

		//comment妥当性チェック
		if (comment == null || comment.isEmpty()) {
			//未入力チェック
			url = "comment-register-failure.jsp";
		}
		try {
			//commentの文字数をcountに代入
			count = comment.length();
			if (count < 0 || count > 100) {
				//文字数チェック
				url = "comment-register-failure.jsp";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		//taskid妥当性チェック
		if (strTaskId == null || strTaskId.isEmpty()) {
			//未入力チェック
			url = "comment-register-failure.jsp";
		}

		//userId妥当性チェック
		if (userId == null || userId.isEmpty()) {
			//未入力チェック
			url = "comment-register-failure.jsp";
		}
		try {
			//userIdの文字数をcountに代入
			count = userId.length();
			if (count < 0 || count > 24) {
			//文字数チェック
			url = "comment-register-failure.jsp";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		//urlに登録失敗画面のurlマッピングが代入されていたら画面遷移する
		if (url == "comment-register-failure.jsp") {
			//転送先のパスを指定して転送処理用オブジェクトを取得
			RequestDispatcher rd = request.getRequestDispatcher(url);

			//リクエストの転送
			rd.forward(request, response);
			
			//メソッドの途中で処理を終了させる
			return;
		}

		//taskidをint型にキャスト
		int taskId = Integer.parseInt(strTaskId);

		//TaskUserCommentBeanのインスタンスを作成
		TaskUserCommentBean tucbean = new TaskUserCommentBean();

		//リクエストパラメータの値をBeanに詰める
		tucbean.setTaskId(taskId);
		tucbean.setUserId(userId);
		tucbean.setComment(comment);

		//TaskUserCommentDAOクラスのインスタンスを作成
		TaskUserCommentDAO dao = new TaskUserCommentDAO();

		//登録件数を格納するint型の変数resを宣言
		int res = 0;

		//TaskCategoryUserStatusDAOクラスのinsertメソッドを呼び出す
		try {
			res = dao.insert(tucbean);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			url = "comment-register-failure.jsp";
		}

		//resの値に応じて遷移する画面を変更する
		if (res == 0) {
			url = "comment-register-failure.jsp";
		} else {
			url = "comment-register-success.jsp";
		}

		//転送先のパスを指定して転送処理用オブジェクトを取得
		RequestDispatcher rd = request.getRequestDispatcher(url);

		//リクエストの転送
		rd.forward(request, response);
	}

}
