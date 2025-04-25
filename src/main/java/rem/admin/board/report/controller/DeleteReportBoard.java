package rem.admin.board.report.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.report.dao.ReportBoardDaoImpl;
import rem.admin.board.report.service.IReportBoardService;
import rem.admin.board.report.service.ReportBoardServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/deleteReportBoard.do")
public class DeleteReportBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("rpt_no"));
		
		IReportBoardService service = ReportBoardServiceImpl.getInstance(ReportBoardDaoImpl.getInstance());
		int res = service.deleteReportBoard(no);
		
		PrintWriter out = response.getWriter();
		if (res > 0) {
			out.print("{\"result\": \"success\"}");
		} else {
			out.print("{\"result\": \"fail\"}");
		}
		out.flush();
	}

}
