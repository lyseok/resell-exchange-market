package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.admin.board.report.vo.ReportBoardVO;
import rem.admin.board.report.vo.ReportSetVO;
import rem.login.vo.MemberVO;
import rem.search.dao.SearchDaoImpl;
import rem.search.service.ISearchService;
import rem.search.service.SearchServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/main/report/search.do")
public class MainReportSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
        
        
        int mem_no = loginInfo.getMem_no();
	
		String searchText = request.getParameter("sch");
		if(searchText!=null)
			request.setAttribute("searchText", searchText);

		
		ISearchService service = SearchServiceImpl.getInstance(SearchDaoImpl.getInstance());
		
		List<ReportSetVO> list = service.getReportBoardList();
		request.setAttribute("board", "report");
		request.setAttribute("searchedList", list);
		request.setAttribute("searchText", searchText);
		

		request.getRequestDispatcher("/WEB-INF/mainBoard/mainReport.jsp").forward(request, response);
	}

	

}
