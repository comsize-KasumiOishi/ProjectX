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

import model.dao.TaskUserCommentDAO;
import model.entity.TaskUserCommentBean;

/**
 * コメントをデータベースから削除するためのサーブレット
 * @author 篠杏樹
 */
/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/CommentDeleteServlet")
public class CommentDeleteServlet extends HttpServlet {

	/**
	 * コメント削除確認画面に表示するコメント情報をリストに詰めて送るメソッド
	 * @throws ClassNotFoundException,SQLException
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//strCommentIdを格納するString型の配列を宣言
		String[] strCommentId = null;

		//リクエストパラメータの取得
		strCommentId = request.getParameterValues("commentId");
		
		//commentIdの空文字チェック
		if(strCommentId == null) {
			//転送先のパスを指定して転送処理用オブジェクトを取得
			RequestDispatcher rd = request.getRequestDispatcher("comment-delete-failure.jsp");

			//リクエストの転送
			rd.forward(request, response);
			
			//メソッドを強制的に終了する
			return;
		}
		
		//TaskUserCommentDAOクラスのインスタンスを作成
		TaskUserCommentDAO dao = new TaskUserCommentDAO();

		//コメント情報を格納するTaskUserCommentBean型のリストを宣言
		List<TaskUserCommentBean> commentList = new ArrayList<>();

		//TaskUserCommentDAOクラスのcommentListメソッドを呼び出す
		try {
			commentList = dao.deleteCommentList(strCommentId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//セッションスコープにcommentIdとコメント情報が格納されたリストを設定
		HttpSession session = request.getSession();
		session.setAttribute("strCommentId", strCommentId);
		session.setAttribute("commentDeleteList", commentList);

		//転送先のパスを指定して転送処理用オブジェクトを取得
		RequestDispatcher rd = request.getRequestDispatcher("comment-delete.jsp");

		//リクエストの転送
		rd.forward(request, response);

	}

	/**
	 * コメントをデータベースから削除するメソッド
	 * @throws ClassNotFoundException,SQLException
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doPostメソッドでセッションスコープに設定したcommentIdを取得
		HttpSession session = request.getSession();
		String[] strCommentId = (String[]) session.getAttribute("strCommentId");

		//遷移先のURLマッピングを格納するString型の変数urlを宣言
		String url = null;
		
		//妥当性チェック
		if (strCommentId == null) {
			//urlにコメント削除失敗画面に遷移する
			url = "comment-delete-failure.jsp";
			
			//転送先のパスを指定して転送処理用オブジェクトを取得
			RequestDispatcher rd = request.getRequestDispatcher(url);

			//リクエストの転送
			rd.forward(request, response);

			//メソッドを強制的に終了する
			return;
		}

		//TaskUserCommentDAOクラスのインスタンスを作成
		TaskUserCommentDAO dao = new TaskUserCommentDAO();

		//削除件数を格納するint型の変数resを宣言
		int res = 0;

		//TaskCategoryUserStatusDAOクラスのdeleteメソッドを呼び出す
		try {
			res = dao.delete(strCommentId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			url = "comment-delete-failure.jsp";
		}

		//resの値に応じて遷移する画面を変更する
		if (res == 0) {
			url = "comment-delete-failure.jsp";
		} else {
			url = "comment-delete-success.jsp";
		}

		//転送先のパスを指定して転送処理用オブジェクトを取得
		RequestDispatcher rd = request.getRequestDispatcher(url);

		//リクエストの転送
		rd.forward(request, response);
	}

}
