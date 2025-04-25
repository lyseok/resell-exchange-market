package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.admin.board.faq.vo.FAQBoardVO;
import rem.login.vo.MemberVO;
import rem.search.dao.SearchDaoImpl;
import rem.search.service.ISearchService;
import rem.search.service.SearchServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/main/faq.do")
public class MainFAQBoardPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		String tabName = request.getParameter("tabName");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" + tabName);
		request.setAttribute("tabName", tabName);
		
		ISearchService service = SearchServiceImpl.getInstance(SearchDaoImpl.getInstance());
		List<FAQBoardVO> list = service.getFAQBoardList();
		request.setAttribute("board", "faq");
		request.setAttribute("FAQList", list);
		request.setAttribute("tabName", tabName);
		
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainFAQ.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
