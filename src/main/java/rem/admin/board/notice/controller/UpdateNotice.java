package rem.admin.board.notice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.notice.dao.NoticeBoardDaoImpl;
import rem.admin.board.notice.service.INoticeBoardService;
import rem.admin.board.notice.service.NoticeBoardServiceImpl;
import rem.admin.board.notice.vo.NoticeBoardVO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/updateNotice.do")
public class UpdateNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		NoticeBoardVO vo = new NoticeBoardVO();
		
		vo.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
		vo.setNotice_cont(request.getParameter("notice_cont"));
		
		INoticeBoardService service = NoticeBoardServiceImpl.getInstance(NoticeBoardDaoImpl.getInstance());
		
		int res = service.updateNoticeBoard(vo);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}

}
