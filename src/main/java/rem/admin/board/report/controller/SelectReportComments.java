package rem.admin.board.report.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.report.dao.ReportCommentsDaoImpl;
import rem.admin.board.report.service.IReportCommentsService;
import rem.admin.board.report.service.ReportCommnetsServiceImpl;
import rem.admin.board.report.vo.ReportCommentsVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

@WebServlet("/admin/selectReportComments.do")
public class SelectReportComments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		int mem_no = Integer.parseInt(request.getParameter("no"));
		
		IReportCommentsService service = ReportCommnetsServiceImpl.getInstance(ReportCommentsDaoImpl.getInstance());
		ReportCommentsVO vo = service.selectReportComments(mem_no);
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(vo));
		out.flush();
		
	}

}
