package rem.store.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.login.vo.MemberVO;

import java.io.IOException;

/**
 * Servlet implementation class mypagePage
 */
@WebServlet("/store/page.do")
public class mypagePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		int index = vo.getMem_no();
		response.sendRedirect("/store/storePage.do?param=mem_no&value="+index);
	}

}
