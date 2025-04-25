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

@WebServlet("/admin/insertReportComments.do")
public class InsertReportComments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		ReportCommentsVO vo = new ReportCommentsVO();
		int num = Integer.parseInt(request.getParameter("rpt_mem_no"));
		vo.setRpt_mem_no(num);
		vo.setCmt_cont(request.getParameter("commentsText"));
		
		IReportCommentsService service = ReportCommnetsServiceImpl.getInstance(ReportCommentsDaoImpl.getInstance());
		int res = service.insertReportComments(vo);
		
		
		PrintWriter out = response.getWriter();
		if (res > 0) {
			vo = service.selectReportComments(num);
			out.print(new Gson().toJson(vo));
		} else {
			out.print("{\"result\": \"fail\"}");
		}
		out.flush();
	}

}
