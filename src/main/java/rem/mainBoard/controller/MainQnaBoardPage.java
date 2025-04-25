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

@WebServlet("/main/qna.do")
public class MainQnaBoardPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		///로그인 세션정보 파악
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		///'내'가 작성한 글만 보여야하기 때문에 mem_no 필요
		int mem_no = loginInfo.getMem_no();
		String searchText = request.getParameter("sch");
		if(searchText!=null)
			request.setAttribute("searchText", searchText);
		
		///mapper Parameter용 Map으로 서비스 호출
		Map<String, Object> map = new HashMap<>();
		map.put("searchText", searchText);
		map.put("mem_no", mem_no);
		System.out.println(searchText+" , "+mem_no+"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		ISearchService service = SearchServiceImpl.getInstance(SearchDaoImpl.getInstance());
		List<QnaBoardVO> list = service.searchQnaBoard(map);
		/* 디버깅용 반복문
		for(QnaBoardVO vo : list) {
			String qnaTitle = vo.getQna_title();
			System.out.println(qnaTitle+"■■■■");
		}
		*/
		
		//request에 attribute 할당
		request.setAttribute("board", "qna");
		request.setAttribute("searchedList", list);
		request.setAttribute("searchText", searchText);
		
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainQna.jsp").forward(request, response);
	}
}