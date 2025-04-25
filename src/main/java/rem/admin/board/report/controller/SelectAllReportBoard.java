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
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/admin/selectAllReportBoard.do")
public class SelectAllReportBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("test/json;charset=utf-8");
		
		IReportBoardService service = ReportBoardServiceImpl.getInstance(ReportBoardDaoImpl.getInstance());
		
		List<ReportBoardVO> list = service.selectAllReportBoard();
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
	}

}
