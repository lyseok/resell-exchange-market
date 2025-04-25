package rem.admin.board.notice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.notice.dao.NoticeBoardDaoImpl;
import rem.admin.board.notice.service.INoticeBoardService;
import rem.admin.board.notice.service.NoticeBoardServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/deleteNoticeBoard.do")
public class DeleteNoticeBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bono = Integer.parseInt(request.getParameter("notice_no"));
		
		INoticeBoardService service = NoticeBoardServiceImpl.getInstance(NoticeBoardDaoImpl.getInstance());
		
		int res = service.deleteNoticeBoard(bono);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}

}
