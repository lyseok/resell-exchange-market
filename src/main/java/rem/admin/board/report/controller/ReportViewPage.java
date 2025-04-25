package rem.admin.board.report.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.report.dao.ReportBoardDaoImpl;
import rem.admin.board.report.service.IReportBoardService;
import rem.admin.board.report.service.ReportBoardServiceImpl;
import rem.admin.board.report.vo.ReportBoardVO;

import java.io.IOException;


@WebServlet("/admin/reportViewPage.do")
public class ReportViewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bono = Integer.parseInt(request.getParameter("bono"));
		System.out.println(bono);
		IReportBoardService service = ReportBoardServiceImpl.getInstance(ReportBoardDaoImpl.getInstance());
		ReportBoardVO vo = service.selectReportBoard(bono);

		request.setAttribute("board", vo);
		request.getRequestDispatcher("/WEB-INF/admin/board/report/reportView.jsp").forward(request, response);	
	}

}
