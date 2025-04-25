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


@WebServlet("/admin/noticeViewPage.do")
public class NoticeViewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		int bono = Integer.parseInt(request.getParameter("bono"));
		System.out.println(bono);
		INoticeBoardService service = NoticeBoardServiceImpl.getInstance(NoticeBoardDaoImpl.getInstance());
		
		NoticeBoardVO vo = service.selectNoticeBoard(bono);
		if(vo == null) {
			response.sendRedirect(request.getContextPath() + "/admin/noticePage.do");
		}
		
		request.setAttribute("board", vo);
		request.getRequestDispatcher("/WEB-INF/admin/board/notice/noticeView.jsp").forward(request, response);
	}

}
