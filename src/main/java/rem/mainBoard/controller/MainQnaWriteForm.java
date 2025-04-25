package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.login.vo.MemberVO;

import java.io.IOException;

/**
 * Servlet implementation class MainQnaWriteForm
 */
@WebServlet("/main/qna/wr.do")
public class MainQnaWriteForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///로그인 세션 확인
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			response.sendRedirect("/WEB-INF/login/login.jsp");
			return;
		}
		
		///그냥 전송
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainQnaForm.jsp").forward(request, response);
	}


}
