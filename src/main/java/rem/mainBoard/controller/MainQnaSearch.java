package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.admin.board.qna.vo.QnaBoardVO;
import rem.login.vo.MemberVO;
import rem.search.dao.SearchDaoImpl;
import rem.search.service.ISearchService;
import rem.search.service.SearchServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/main/qna/search.do")
public class MainQnaSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		///로그인 세션정보 파악
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		
		int mem_no = loginInfo.getMem_no();
		String searchText = request.getParameter("sch");
		///검색용 서블릿에 검색어가 안 올리 없음.

		
		Map<String, Object> map = new HashMap<>();
		map.put("searchText", searchText);
		map.put("mem_no", mem_no);
		
		ISearchService service = SearchServiceImpl.getInstance(SearchDaoImpl.getInstance());
		List<QnaBoardVO> list = service.searchQnaBoard(map);
		for(QnaBoardVO vo : list) {
			String noticeTitle = vo.getQna_title();
			System.out.println(noticeTitle+"■■■■");
		}
		
		
		request.setAttribute("board", "qna");
		request.setAttribute("searchedList", list);
		System.out.println(list);
		request.setAttribute("searchText", searchText);
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■   "+ searchText);
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainQnaSearch.jsp").forward(request, response);
	}

}
