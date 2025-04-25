package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.admin.board.report.vo.ReportSetVO;
import rem.login.vo.MemberVO;
import rem.search.dao.SearchDaoImpl;
import rem.search.service.ISearchService;
import rem.search.service.SearchServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/main/report/view.do")
public class MainReportView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		int mem_no = loginInfo.getMem_no();
		int rpt_no = Integer.parseInt(request.getParameter("reportNo"));
		System.out.println("reportNo" + rpt_no);
		
		
		
		ISearchService serviceS = SearchServiceImpl.getInstance(SearchDaoImpl.getInstance());
		ReportSetVO vo = serviceS.getReportBoardOne(rpt_no);
		ReportSetVO vo2 = null;
		
		
		
		if (vo.getRpt_com_status() == 1) {
			vo2 = serviceS.getReportComments(rpt_no);
			vo.setCmt_cont(vo2.getCmt_cont());
			vo.setCmt_at(vo2.getCmt_at());
		}
	
		
		request.setAttribute("reportViewSet", vo);
		request.setAttribute("board", "report");
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainReportView.jsp").forward(request, response);
		
	}


}
